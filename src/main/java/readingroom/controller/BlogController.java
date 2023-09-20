package readingroom.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import readingroom.common.Result;
import readingroom.entity.Blog;
import readingroom.service.BlogLikeDetailService;
import readingroom.service.BlogService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogLikeDetailService blogLikeDetailService;
    @GetMapping("/list")
    public Result list(){
        return Result.succ(blogService.list());
    }

    @GetMapping("/like")
    public Result like(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "blogId") Integer blogId){
        return Result.succ(blogLikeDetailService.like(userId,blogId));
    }

    @GetMapping("/getLikeDetail")
    public Result getLikeDetail(@RequestParam(name = "userId") Integer userId){
        List<Integer> integers = blogLikeDetailService.blogIdList(userId);
        if(integers != null && !integers.isEmpty()){
            return Result.succ(integers);
        }
        return Result.succ(Collections.EMPTY_LIST);
    }
    @GetMapping("/getLikeNum")
    public Result getLikeNum(@RequestParam(name = "blogId")Integer blogId){
        return Result.succ(blogService.getBlogById(blogId).getLikeNum());
    }

    @GetMapping("/removeLike")
    public Result removeLike(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "blogId") Integer blogId){
        Blog blog = blogService.getBlogById(blogId);
        blogService.removeLike(blog);
        return Result.succ(blogLikeDetailService.removeLike(userId,blogId));
    }

    @GetMapping("/search")
    public Result removeLike(@RequestParam(name = "searchInfo") String searchInfo){
        return Result.succ(blogService.searchInfo(searchInfo));
    }

    @GetMapping("/searchByKeyword")
    public Result searchByKeyword(@RequestParam(name = "keyword")String keyword){
        return Result.succ(blogService.searchByKeyword(keyword));
    }
}
