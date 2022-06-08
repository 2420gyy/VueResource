package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.entity.UserInfo;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 0.校验手机号是否正确 业务逻辑不对
        // 1.校验验证码是否一致
        // Object cacheCode = session.getAttribute("code");
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + loginForm.getPhone());
        // 这个地方注意细节
        if(cacheCode == null || !cacheCode.toString().equals(loginForm.getCode())){
            return Result.fail("验证码错误");
        }
        // 根据手机号查询用户
        // LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        // qw.eq(User::getPhone,loginForm.getPhone());
        // User one = iUserService.getOne(qw);
        User one = query().eq("phone", loginForm.getPhone()).one();
        if(ObjectUtil.isNull(one)){
            one = createUserWithPhone(loginForm.getPhone());
        }
        // 将用户信息保存到session
        // session.setAttribute("user", BeanUtil.copyProperties(one, UserDTO.class));
        // 这里使用随机的token作为key
        String token = UUID.randomUUID().toString();
        String tokenKey = LOGIN_USER_KEY + token;
        UserDTO userDTO = BeanUtil.copyProperties(one, UserDTO.class);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
        stringRedisTemplate.opsForHash().putAll(tokenKey,stringObjectMap);
        // 设置有效期 (这个地方只要用户在访问就不断更新)
        stringRedisTemplate.expire(tokenKey,LOGIN_USER_TTL,TimeUnit.MINUTES);
        //
        return Result.ok(token);
    }

    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(6));
        user.setCreateTime(LocalDateTime.now());
        baseMapper.insert(user);
        return user;
    }
}
