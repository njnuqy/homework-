package readingroom.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import readingroom.entity.User;
import readingroom.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        String token = request.getHeader("token");   //header里传的参数
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token"); //url参数 ?token=...
        }
        // 如果不是映射到方法直接通过

        //执行认证
        if(StrUtil.isBlank(token)){
            System.out.println("没有token");
            return false;
        }
        // 获取token中的userId
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0); // JWT.decode(token) 解码
        }catch (JWTDecodeException e){
            System.out.println("无此用户");
            return false;
        }
        User user = userMapper.selectById(userId);

        if(user == null){
            System.out.println("无此用户");
            return false;
        }
        // 通过用户密码加密后形成一个验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        try{
            jwtVerifier.verify(token); // 验证token
        }catch (JWTVerificationException e){
            System.out.println("密码错误");
            return false;
        }
        return true;
    }
}
