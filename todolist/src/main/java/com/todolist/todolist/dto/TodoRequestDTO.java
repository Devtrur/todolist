package com.todolist.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TodoRequestDTO {

    @NotBlank(message = "O titulo nao pode estar vazio")
    @Size(min = 3, max = 100, message = "o titulo deve ter entre 3 e 100 caracteres")
    private String title;
    @Size(max = 500, message = "A descricao deve ter no maximo 500 caracteres")
    private String description;
    private boolean concluded;

}
