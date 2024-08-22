package ru.practicum.explore.main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.practicum.explore.main.event.model.Location;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class NewEventDto {
    @NotBlank(message = "Краткое описание события отсутствует")
    @Size(max = 2000, min = 20, message = "Количество символов в кратком описании должно быть в пределах 20-2000")
    private String annotation;

    @NotNull(message = "ID категории мероприятия отсутствует")
    @Positive
    private Long category;

    @NotBlank(message = "Полное описание события отсутствует")
    @Size(max = 7000, min = 20, message = "Количество символов в полном описании должно быть в пределах 20-7000")
    private String description;

    @NotNull(message = "Дата и время на которые намечено событие отсутствует")
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @NotNull(message = "Широта и долгота места проведения события отсутствуют")
    private Location location;

    private Boolean paid = false;

    @PositiveOrZero
    private Integer participantLimit = 0;

    private Boolean requestModeration = true;

    @NotBlank(message = "Заголовок события отсутствует")
    @Size(max = 120, min = 3, message = "Количество символов в заголовке события должно быть в пределах 3-120")
    private String title;
}