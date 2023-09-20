package readingroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readingroom.entity.Blog;
import readingroom.mapper.BlogMapper;
import readingroom.service.BlogService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public List<Blog> list() {
        return blogMapper.selectList(null);
    }

    @Override
    public Blog getBlogById(int blogId) {
        Blog blog = blogMapper.selectById(blogId);
        if(blog == null){
            return null;
        }
        return blog;
    }

    @Override
    public int like(Blog blog) {
        blog.setLikeNum(blog.getLikeNum() + 1);
        return blogMapper.updateById(blog);
    }

    @Override
    public int removeLike(Blog blog) {
        blog.setLikeNum(blog.getLikeNum() - 1);
        return blogMapper.updateById(blog);
    }

    @Override
    public List<String> searchInfo(String searchInfo) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like("title","%"+searchInfo+"%");
        List<String> searchInfoList = new ArrayList<>();
        blogMapper.selectList(wrapper).stream().limit(5).forEach(blog -> searchInfoList.add(blog.getTitle()));
        return searchInfoList;
    }

    @Override
    public List<Blog> searchByKeyword(String keyword) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like("title","%"+keyword+"%");
        List<Blog> searchInfoList = new ArrayList<>();
        return blogMapper.selectList(wrapper);
    }
}
