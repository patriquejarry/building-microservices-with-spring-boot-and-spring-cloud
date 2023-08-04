package net.javaguides.springbootrestfulwebservices.mapper;

import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapperAuto {

    UserMapperAuto INSTANCE = Mappers.getMapper(UserMapperAuto.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
