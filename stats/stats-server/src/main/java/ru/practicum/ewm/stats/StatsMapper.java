package ru.practicum.ewm.stats;

import ru.practicum.ewm.dto.stats.StatsDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class StatsMapper {
    static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static StatsDto mapToStatsDto(ru.practicum.ewm.stats.Stats stats) {
        return new StatsDto.StatsDtoBuilder()
                .id(stats.getId())
                .app(stats.getApp())
                .uri(stats.getUri())
                .ip(stats.getIp())
                .timestamp(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(stats.getDatetime()))
                .build();
    }

    public static Stats mapToStats(StatsDto statsDto) {
        return new Stats.StatsBuilder()
                .app(statsDto.getApp())
                .uri(statsDto.getUri())
                .ip(statsDto.getIp())
                .datetime(LocalDateTime.parse(statsDto.getTimestamp(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))
                .build();
    }

    public static List<StatsDto> mapToStatsDto(List<Stats> stats) {
        return stats.stream()
                .map(StatsMapper::mapToStatsDto)
                .collect(Collectors.toList());
    }
}