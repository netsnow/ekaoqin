package org.snow.service;

import com.github.pagehelper.PageInfo;
import org.snow.model.security.User;
import org.snow.security.JwtUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> getUserById(Long id);

    public List<User> getAllUsers();

    User addUser(User user);

    Boolean updateUserById(Long id, User user);

    void deleteUserById(Long id);

    PageInfo<User> searchUsers(User user, int pageNum, int pageSize);

    PageInfo<User> searchFuzzyUsers(String key, int pageNum, int pageSize);

    Boolean validatePassword(String username, String password);

    Boolean changePassword(Long userId, String password);

    User getUserFromJwtUser(JwtUser jwtUser);

}
