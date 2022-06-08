package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

/**
 * @author chenchen
 * @date 2022-04-26 14:19
 */
public class LoginInterceptor implements HandlerInterceptor {

    // 前置拦截 preHandle
    // 控制层执行之后 postHandle
    // 视图渲染之后 业务执行完后销毁相关信息

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 前端配置axios拦截器,请求会加上token
        /**
        // 1.获取session
        HttpSession session = request.getSession();
        // 2.获取到用户
        UserDTO user = (UserDTO)session.getAttribute("user");
        // 3.判断用户是否存在
        if(user == null){
            // 4.不存在，拦截,401 未授权
            response.setStatus(401);
            return false;
        }
        // 5.存在，保存信息到ThreadLocal
        UserHolder.saveUser(BeanUtil.copyProperties(user, UserDTO.class));
         */
        // String token = request.getHeader("authorization");
        // if(StrUtil.isBlank(token)){
        //     response.setStatus(401);
        //     return false;
        // }
        // Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        // // 这个地方返回的时候会自动做？判断
        // if(userMap.isEmpty()){
        //     response.setStatus(401);
        //     return false;
        // }
        // UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        // // 存储到ThreadLocal
        // UserHolder.saveUser(userDTO);
        // // 刷新token有效期
        // stringRedisTemplate.expire(LOGIN_USER_KEY + token,LOGIN_USER_TTL, TimeUnit.MINUTES);
        // // 6.放行
        // 判断是否要拦截
        if(ObjectUtil.isNull(UserHolder.getUser())){
            // 拦截
            response.setStatus(401);
            return false;
        }
        // 有用户，放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
