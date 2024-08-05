package ru.practicum.ewm.stats;

import ru.practicum.ewm.dto.stats.ShortStatsDto;
import ru.practicum.ewm.dto.stats.StatsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatsService {

    StatsDto endpointHit(StatsDto statsDto);

    List<ShortStatsDto> viewStats(String start, String end, List<String> uris, Boolean unique,
                                  HttpServletRequest request);
}