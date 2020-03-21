package com.qf.interceptor;

import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor1 implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1,获取cookie中的UUID
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieConstant.USER_LOGIN.equals(cookie.getName())) {
                    String uuid = cookie.getValue();
                    String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
                    Object o = redisTemplate.opsForValue().get(redisKey);
                    if (o != null) {
                        //用户登录
                        TUser tUser = (TUser) o;
                        //存到request域中
                        request.setAttribute("TUser", tUser);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
