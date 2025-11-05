package com.todolist.todolist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(example = "Guilherme Arthur", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do Usuario")
    private String name;
    @Email(message = "O campo (email) deve conter um e-mail valido")
    @Schema(example = "Guilherme@hotmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Email do usuario")
    private String email;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] nao deve conter espaco")
    @Schema(example = "GuilhermeA", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do Usuario")
    private String username;
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    @Schema(example = "1234567890", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do Usuario")
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
