package ru.practicum.explore.main.category.dto;

import lombok.Data;

import jakarta.validation.constraints.Size;

@Data
public class CategoryDto {
    private Long id;
    @Size(max = 50, message = "Количество символов в имени категории должно быть не больше 50")
    private String name;
}
