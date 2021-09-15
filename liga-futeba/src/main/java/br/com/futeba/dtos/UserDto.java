package br.com.futeba.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.futeba.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    @NotBlank(message = "Name required")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    private String cellPhone;
    @Size(min = 7, max = 99, message = "Password must be between 7 and 99")
    private String password;
    private Role role;
    private boolean changePassword;
    private TeamDto team;

}
