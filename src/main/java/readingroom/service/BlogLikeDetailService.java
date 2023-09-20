package readingroom.service;

import java.util.List;

public interface BlogLikeDetailService {
    int like(Integer userId,Integer blogId);

    List<Integer> blogIdList(Integer userId);

    int removeLike(Integer userId,Integer blogId);
}
