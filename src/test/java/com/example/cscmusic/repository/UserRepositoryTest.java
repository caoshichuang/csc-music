package com.example.cscmusic.repository;

import com.example.cscmusic.entity.User;
import com.example.cscmusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("liming");
        user.setNickname("cc");
        user.setEnabled(true);
        user.setGender(Gender.FEMALE);
        user.setLocked(false);
        user.setPassword("123123124");
        user.setLastLoginIp("127.0.0.1");
        user.setLastLoginTime(new Date());
//
//        User saved = repository.save(user);
        User result = repository.getByUsername("liming");
        System.out.println(result.toString());

    }
}