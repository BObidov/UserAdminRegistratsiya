package com.company.userandadminauthorities.service;

import com.company.userandadminauthorities.dto.ResponseDto;
import com.company.userandadminauthorities.dto.UserDto;
import com.company.userandadminauthorities.model.User;
import com.company.userandadminauthorities.repository.UserRepository;
import com.company.userandadminauthorities.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public ResponseDto<UserDto> createSaveUser(UserDto dto){
        User user = this.userMapper.toEntity(dto);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(user);
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("User successful created and save in database")
                .data(this.userMapper.toDto(user))
                .build();
    }

    public ResponseDto<UserDto> getUser(Integer id) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-2)
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(this.userMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<UserDto> updateSaveUser(UserDto dto, Integer id) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-2)
                    .build();
        }
        User user = optional.get();
        user.setUpdatedAt(LocalDateTime.now());
        user.setEnabled(true);
        this.userMapper.updateUserDto(dto, user);
        this.userRepository.save(user);
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("User successful update and save in database!")
                .data(this.userMapper.toDto(user))
                .build();
    }

    public ResponseDto<UserDto> deleteUser(Integer id) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-2)
                    .build();
        }
        optional.get().setDeletedAt(LocalDateTime.now());
        this.userRepository.delete(optional.get());
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("User deleted!")
                .data(this.userMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<List<UserDto>> getAllUser() {
        List<UserDto> userList = userRepository.findAll()

                .stream()
                .map(userMapper::toDto)
                .toList();
        if (userList.isEmpty()) {
            return ResponseDto.<List<UserDto>>builder()
                    .message("Users is not found!")
                    .code(-3)
                    .build();
        }
        return ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(userList)
                .build();
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameAndDeletedAtIsNull(username)
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username :: %s is not found",
                        username)));
    }


}
