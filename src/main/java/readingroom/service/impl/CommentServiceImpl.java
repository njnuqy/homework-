package readingroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readingroom.entity.Comment;
import readingroom.mapper.CommentMapper;
import readingroom.service.CommentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getComments(int blogId) {
        List<Comment> parentComments = getParentComments(blogId);
        Map<Integer, Comment> map = new HashMap<>();   // (id, Comment)
        List<Comment> result = new ArrayList<>();
        // 将所有根评论加入 map
        for(Comment comment : parentComments) {
            if(comment.getParentId() == null)
                result.add(comment);
            map.put(comment.getId(), comment);
        }
        // 子评论加入到父评论的 child 中
        for(Comment comment : parentComments) {
            Integer id = comment.getParentId();
            if(id != null) {   // 当前评论为子评论
                Comment p = map.get(id);
                if(p.getChild() == null)    // child 为空，则创建
                    p.setChild(new ArrayList<>());
                p.getChild().add(comment);
            }
        }
        return result;
    }

    @Override
    public List<Comment> getParentComments(int blogId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id",blogId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public int addComment(String content, Integer userId, String userName, Integer blogId, Integer parentId) {
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setParentId(parentId);
        return commentMapper.insert(comment);
    }

}
