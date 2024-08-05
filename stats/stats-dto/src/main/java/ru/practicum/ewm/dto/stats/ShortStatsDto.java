package ru.practicum.ewm.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder(builderClassName = "ShortStatsDtoBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ShortStatsDto {
    private String app;
    private String uri;
    private Integer hits;

    public static class ShortStatsDtoBuilder {
        public ShortStatsDtoBuilder() {
            // Пустой конструктор
        }
    }
}