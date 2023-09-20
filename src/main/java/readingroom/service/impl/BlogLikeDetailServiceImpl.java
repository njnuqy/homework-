package readingroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import readingroom.entity.BlogLikeDetail;
import readingroom.mapper.BlogLikeDetailMapper;
import readingroom.service.BlogLikeDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogLikeDetailServiceImpl implements BlogLikeDetailService {
    @Autowired
    private BlogLikeDetailMapper blogLikeDetailMapper;
    @Override
    public int like(Integer userId, Integer blogId) {
        BlogLikeDetail blogLikeDetail = new BlogLikeDetail();
        blogLikeDetail.setBlogId(blogId);
        blogLikeDetail.setUserId(userId);
        return blogLikeDetailMapper.insert(blogLikeDetail);
    }

    @Override
    public List<Integer> blogIdList(Integer userId) {
        QueryWrapper<BlogLikeDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Integer> blogIdList = new ArrayList<>();
        blogLikeDetailMapper.selectList(wrapper).stream().forEach(blogLikeDetail -> blogIdList.add(blogLikeDetail.getBlogId()));
        if(CollectionUtils.isEmpty(blogIdList)){
            return null;
        }
        return blogIdList;
    }

    @Override
    public int removeLike(Integer userId, Integer blogId) {
        QueryWrapper<BlogLikeDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("blog_id",blogId);
        return blogLikeDetailMapper.delete(wrapper);
    }
}
