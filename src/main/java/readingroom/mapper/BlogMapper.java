package readingroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import readingroom.entity.Blog;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
