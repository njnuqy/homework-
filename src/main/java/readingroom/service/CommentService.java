package readingroom.service;

import readingroom.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(int blogId);

    List<Comment> getParentComments(int blogId);

    int addComment(String content,Integer userId,String userName,Integer blogId,Integer parentId);
}
