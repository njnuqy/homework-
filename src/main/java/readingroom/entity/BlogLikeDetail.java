package readingroom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("h_blog_like_detail")
public class BlogLikeDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer blogId;

}
