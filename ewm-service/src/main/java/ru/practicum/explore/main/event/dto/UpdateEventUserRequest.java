package ru.practicum.explore.main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.practicum.explore.main.event.model.Location;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import ru.practicum.explore.main.event.model.UpdateEventUserState;

import java.time.LocalDateTime;

@Data
public class UpdateEventUserRequest {
    @Size(max = 2000, min = 20, message = "Количество символов в кратком описании должно быть в пределах 20-2000")
    private String annotation;
    private Long category;
    @Size(max = 7000, min = 20, message = "Количество символов в полном описании должно быть в пределах 20-7000")
    private String description;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    @PositiveOrZero
    private Integer participantLimit;
    private Boolean requestModeration;
    private UpdateEventUserState stateAction;
    @Size(max = 120, min = 3, message = "Количество символов в заголовке события должно быть в пределах 3-120")
    private String title;
}
