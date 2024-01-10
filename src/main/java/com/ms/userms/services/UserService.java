package com.ms.userms.services;


import com.ms.userms.dtos.UserDTO;
import com.ms.userms.entities.UserEntity;
import com.ms.userms.producers.UserProducer;
import com.ms.userms.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserEntity saveUser(UserDTO userDTO){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
