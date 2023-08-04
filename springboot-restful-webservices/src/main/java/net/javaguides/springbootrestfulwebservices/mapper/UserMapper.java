package net.javaguides.springbootrestfulwebservices.mapper;

import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){

        if(user == null) {
            return null;
        }

        UserDto userDto = new UserDto(
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){

        if(userDto == null) {
            return null;
        }

        User user = new User(
                userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail()
        );
        return user;
    }
}
