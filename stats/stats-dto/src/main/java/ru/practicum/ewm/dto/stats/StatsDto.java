package ru.practicum.ewm.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(builderClassName = "StatsDtoBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private String timestamp;

    public static class StatsDtoBuilder {
        public StatsDtoBuilder() {
            // Пустой конструктор
        }
    }
}