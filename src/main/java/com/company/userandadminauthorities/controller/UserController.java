package com.company.userandadminauthorities.controller;

import com.company.userandadminauthorities.dto.ResponseDto;
import com.company.userandadminauthorities.dto.UserDto;
import com.company.userandadminauthorities.service.UserService;
import com.company.userandadminauthorities.util.CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UserController extends CRUD<UserDto, Integer> {

    private final UserService userService;


    @PostMapping(value = "/create")
    @Override
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.createSaveUser(dto);
    }


    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<UserDto> get(@PathVariable(value = "id") Integer id) {
        return this.userService.getUser(id);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<UserDto> update(@RequestBody UserDto dto,
                                       @PathVariable(value = "id") Integer id) {
        return this.userService.updateSaveUser(dto, id);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<UserDto> delete(@PathVariable(value = "id") Integer id) {
        return this.userService.deleteUser(id);
    }
}
