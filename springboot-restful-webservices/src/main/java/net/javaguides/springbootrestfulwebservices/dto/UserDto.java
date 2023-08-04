package net.javaguides.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "User", description = "User Model Information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(description = "User first name")
    @NotEmpty(message = "The firstName should not be null or empty")
    private String firstName;

    @Schema(description = "User last name")
    @NotEmpty(message = "The lastName should not be null or empty")
    private String lastName;

    @Schema(description = "User address mail")
    @Email(message = "The email should be valid")
    @NotEmpty(message = "The email should not be null or empty")
    private String email;

}
