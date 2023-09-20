package readingroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import readingroom.entity.User;
import readingroom.exception.ServiceException;
import readingroom.mapper.UserMapper;
import readingroom.service.UserService;
import readingroom.utils.TokenUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //@Autowired
    //private RedisTemplate<String,User> redisTemplate;
    //@Autowired
    //private RedisTemplate<String,String> stringRedisTemplate;

    private static final String USER_PHONE = "user:phone:";
    @Override
    public User seleteByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        return userMapper.selectOne(wrapper);
    }
    @Override
    public User login(User user) {
        String phone = user.getPhone();
        User dbUser = seleteByPhone(phone);
        //if(dbUser == null){
        //    dbUser = seleteByPhone(user.getPhone());
        //}
//        if (dbUser == null) {
//            throw new ServiceException("账号不存在");
//        }
//        }else{
//            redisTemplate.opsForValue().set(phone,dbUser);
//            redisTemplate.expire(phone,30, TimeUnit.MINUTES);
//        }
//            if (!user.getPassword().equals(dbUser.getPassword())) {
//                throw new ServiceException("密码错误");
//            }
            //生成token
            String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
            dbUser.setToken(token);
            return dbUser;
        }

    @Override
    public User register(User user) {
        String phone = user.getPhone();
        //User cacheUser = redisTemplate.opsForValue().get(phone);
//        if(cacheUser != null){
//            throw new ServiceException("该手机号已被注册");
//        }
        User dbUser = seleteByPhone(phone);
        if(dbUser != null){
//            String key = USER_PHONE + phone;
//            stringRedisTemplate.opsForValue().set(key,phone);
//            stringRedisTemplate.expire(key,30,TimeUnit.MINUTES);
            throw new ServiceException("该手机号已被注册");
        }
        userMapper.insert(user);
        return user;
    }
}
