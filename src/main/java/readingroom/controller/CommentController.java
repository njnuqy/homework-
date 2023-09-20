package readingroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readingroom.common.Result;
import readingroom.entity.Comment;
import readingroom.service.CommentService;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComment")
    public Result getComment(@RequestParam(name = "blogId") int blogId){
        return Result.succ(commentService.getComments(blogId));
    }

    @PostMapping("/replyComment")
    public Result replyComment(@RequestBody Map<String, Object> requestData){
        Integer parentId;
        if(requestData.get("parentId") == null){
            parentId = null;
        }else {
            parentId = (Integer) requestData.get("parentId");
        }
       return Result.succ(commentService.addComment(requestData.get("content").toString(),(Integer) requestData.get("userId"),
               requestData.get("userName").toString(),(Integer) requestData.get("blogId"),parentId));
    }
}
