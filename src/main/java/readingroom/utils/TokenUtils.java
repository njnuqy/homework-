package readingroom.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import readingroom.entity.User;
import readingroom.mapper.UserMapper;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {
    private static UserMapper staticUserMapper;
    @Autowired
    UserMapper userMapper;

    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }

    public static String createToken(String usertId,String sign){
        return JWT.create().withAudience(usertId) //将userId保存到token里面作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); //以password作为 token的密钥
    }

    public static User getCurrentUser(){
        try{
            String token = ((HttpServletRequest) RequestContextHolder.getRequestAttributes()).getHeader("token");
            if(StrUtil.isBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
