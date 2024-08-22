package ru.practicum.explore.stats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndpointHitDto {
    private Long id;

    @NotBlank(message = "Отсутствует идентификатор сервиса для которого записывается информация" +
            " или передана пустая строка (символ)")
    private String app;

    @NotBlank(message = "Отсутствует URI для которого был осуществлен запрос")
    private String uri;

    @NotBlank(message = "Отсутствует IP-адрес пользователя, осуществившего запрос")
    private String ip;

    @NotNull(message = "Отсутствует дата и время, когда был совершен запрос к эндпоинту (в формате yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}