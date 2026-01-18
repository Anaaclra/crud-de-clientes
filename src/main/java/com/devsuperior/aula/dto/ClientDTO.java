package com.devsuperior.aula.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Positive(message = "Renda deve ser positiva")
    private double income;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;

    @NotNull(message = "Número de filhos é obrigatório")
    @Min(value = 0, message = "Número de filhos não pode ser negativo")
    private Integer children;
}
