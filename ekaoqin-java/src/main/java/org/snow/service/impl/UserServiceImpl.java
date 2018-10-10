package org.snow.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.snow.dao.jpa.UsersRepository;
import org.snow.dao.mybatis.mapper.UserMapper;
import org.snow.model.security.User;
import org.snow.security.JwtUser;
import org.snow.security.controller.AuthenticationException;
import org.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<User> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public List<User> getAllUsers() {
        Iterable<User> geted = usersRepository.findAll();
        List<User> list = new ArrayList<User>();
        geted.forEach(single -> {
            list.add(single);
        });
        return list;
    }

    @Transactional
    public User addUser(User user) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setPassword(encode.encode(user.getPassword()));

        return usersRepository.save(user);
    }

    @Transactional
    public Boolean updateUserById(Long id, User user) {
        Optional<User> newUser = usersRepository.findById(id);
        if (user.getUsername() != null) {
            newUser.get().setUsername(user.getUsername());
        }
        if (user.getFullname() != null) {
            newUser.get().setFullname(user.getFullname());
        }
        if (user.getEnabled() != null) {
            newUser.get().setEnabled(user.getEnabled());
        }
        if (user.getAuthorities() != null) {
            newUser.get().setAuthorities(user.getAuthorities());
        }
        if (user.getPassword() != null) {
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            newUser.get().setPassword(encode.encode(user.getPassword()));
        }

        usersRepository.save(newUser.get());
        return true;
    }

    @Transactional
    public void deleteUserById(Long id) {

        usersRepository.deleteById(id);
        User user = new User();
        user.setId(id);
    }

    public PageInfo<User> searchUsers(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> usersList = userMapper.searchUsers(user);
        PageInfo<User> usersPageInfo = new PageInfo<>(usersList);

        return usersPageInfo;
    }

    @Override
    public Boolean validatePassword(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
        return true;
    }

    @Override
    public Boolean changePassword(Long userId, String password) {
        Optional<User> user = usersRepository.findById(userId);
        user.get().setId(userId);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.get().setPassword(encode.encode(password));
        usersRepository.save(user.get());
        return true;
    }


    @Override
    public User getUserFromJwtUser(JwtUser jwtUser) {
        return this.getUserById(jwtUser.getId()).get();
    }

}
