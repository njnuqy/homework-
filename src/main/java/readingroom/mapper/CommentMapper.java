package readingroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import readingroom.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
