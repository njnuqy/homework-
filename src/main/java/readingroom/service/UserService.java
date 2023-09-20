package readingroom.service;

import readingroom.entity.User;

public interface UserService {
    User login(User user);

    User seleteByPhone(String phone);
    User register(User user);
}
