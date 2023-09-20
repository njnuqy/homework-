package readingroom.service;


import readingroom.entity.Blog;

import java.util.List;

public interface BlogService{
    List<Blog> list();

    Blog getBlogById(int blogId);

    int like(Blog blog);

    int removeLike(Blog blog);

    List<String> searchInfo(String searchInfo);

    List<Blog> searchByKeyword(String keyword);
}
