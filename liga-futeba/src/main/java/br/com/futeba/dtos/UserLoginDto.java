package br.com.futeba.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password required")
    private String password;
}
