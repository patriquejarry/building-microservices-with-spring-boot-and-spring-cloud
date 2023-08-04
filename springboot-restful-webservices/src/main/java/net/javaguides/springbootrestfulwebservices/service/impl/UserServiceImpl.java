package net.javaguides.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;
import net.javaguides.springbootrestfulwebservices.exception.EmailAlreadyExistsException;
import net.javaguides.springbootrestfulwebservices.exception.ResourceNotFoundException;
import net.javaguides.springbootrestfulwebservices.mapper.UserMapperAuto;
import net.javaguides.springbootrestfulwebservices.repository.UserRepository;
import net.javaguides.springbootrestfulwebservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email '" + userDto.getEmail() + "' already exists");
        }

        //User savedUser = userRepository.save(UserMapper.mapToUser(userDto));
        //return UserMapper.mapToUserDto(savedUser);
        //User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
        //return modelMapper.map(savedUser, UserDto.class);
        User savedUser = userRepository.save(UserMapperAuto.INSTANCE.mapToUser(userDto));
        return UserMapperAuto.INSTANCE.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).toList();
        //return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        return users.stream().map(UserMapperAuto.INSTANCE::mapToUserDto).toList();
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user, UserDto.class);
        return UserMapperAuto.INSTANCE.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );

        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setEmail(user.getEmail());
        //return UserMapper.mapToUserDto(userRepository.save(existUser));
        //return modelMapper.map(userRepository.save(existUser), UserDto.class);
        return UserMapperAuto.INSTANCE.mapToUserDto(userRepository.save(existUser));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
