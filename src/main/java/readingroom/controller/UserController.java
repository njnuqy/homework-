package readingroom.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readingroom.common.Result;
import readingroom.entity.User;
import readingroom.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getPhone()) || StrUtil.isBlank(user.getPassword())){
            return Result.fail("数据输入不合法");
        }
        user = userService.login(user);
        return Result.succ(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(StrUtil.isBlank(user.getPhone()) || StrUtil.isBlank(user.getPassword())){
            return Result.fail("数据输入不合法");
        }
        return Result.succ(userService.register(user));
    }
}
