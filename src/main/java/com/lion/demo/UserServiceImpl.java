package com.lion.demo;

import com.lion.demo.entity.User;
import com.lion.demo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUid(String uid) {
        return userRepository.findById(uid).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public int login(String uid, String pwd) {
        return 0;
    }

}