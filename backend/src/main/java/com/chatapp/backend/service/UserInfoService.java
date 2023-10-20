package com.chatapp.backend.service;

import com.chatapp.backend.dto.UserDTO;
import com.chatapp.backend.exception.UserAlreadyExistsException;
import com.chatapp.backend.model.UserInfo;
import com.chatapp.backend.model.UserInfoDetails;
import com.chatapp.backend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (repository.existsUserInfoByUsername(userDTO.getUsername())) throw new UserAlreadyExistsException("User with name " + userDTO.getUsername() + " already exists.");
        UserInfo userInfo = UserInfo.builder()
                ._id(UUID.randomUUID().toString())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles("ROLE_USER")
                .build();
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public boolean existsByUsername(String username) {
        return repository.existsUserInfoByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsUserInfoByEmail(email);
    }
}
