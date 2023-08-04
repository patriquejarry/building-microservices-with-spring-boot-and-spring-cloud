package net.javaguides.springbootrestfulwebservices.service;

import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> findAllUsers();

    UserDto findUserById(Long userId);

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
