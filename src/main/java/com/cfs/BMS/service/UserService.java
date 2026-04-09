package com.cfs.BMS.service;

import com.cfs.BMS.dto.LoginRequest;
import com.cfs.BMS.dto.UserRequest;
import com.cfs.BMS.entity.User;
import com.cfs.BMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // register

    public User register(UserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("email already exist" + request.getEmail());
        }

        User user = User.builder().
                name(request.getName()).
                email(request.getEmail()).
                password(request.getPassword()).
                phone(request.getPhone()).
                build();

        return userRepository.save(user);

    }

    //login

    public User login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email :" +request.getEmail()));

        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid Password" );
        }
        return user;
    }

    //List of all User

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User getUserdById(Long id){
        return  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID" +id));
    }
}
