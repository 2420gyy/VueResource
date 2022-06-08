package com.hmdp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;
import com.hmdp.entity.UserInfo;
import com.hmdp.mapper.UserInfoMapper;
import com.hmdp.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_CODE_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_CODE_TTL;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-24
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        // 1.校验手机号是否合规
        if(RegexUtils.isPhoneInvalid(phone)){
            return Result.fail("手机号码格式不正确");
        }
        // 2.生成验证码
        String code = RandomUtil.randomNumbers(6);
        // int code = (int) Math.floor(Math.random() * 1000000);
        // 3.保存到session cookie中此时会有一个JSESSIONID

        // session.setAttribute("code",code);
        // 将验证码保存到redis 有效期
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone , code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 发送验证码 用别人的接口？？？
        log.debug("发送验证码成功，{}",code);
        // 4.返回验证码给前端
        return Result.ok();
    }


}
