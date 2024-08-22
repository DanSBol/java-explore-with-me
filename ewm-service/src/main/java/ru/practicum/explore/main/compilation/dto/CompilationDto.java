package ru.practicum.explore.main.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.main.event.dto.EventShortDto;

import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CompilationDto {
    private List<EventShortDto> events = new ArrayList<>();
    private Long id;
    private boolean pinned;
    @Size(max = 50, message = "Количество символов в заголовке подборки должно быть не больше 50")
    private String title;
}