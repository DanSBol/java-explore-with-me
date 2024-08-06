package ru.practicum.ewm.stats;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dto.stats.ShortStatsDto;
import ru.practicum.ewm.dto.stats.StatsDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Transactional
    @Override
    public StatsDto endpointHit(StatsDto statsDto) {
        return StatsMapper.mapToStatsDto(statsRepository.save(StatsMapper.mapToStats(statsDto)));
    }

    @Override
    public List<ShortStatsDto> viewStats(String start, String end, List<String> uris, Boolean unique,
                                         HttpServletRequest request) {
        final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        List<ShortStatsDto> result = new ArrayList<>();
        if (uris != null) {
            List<String> deduped = uris.stream().distinct().collect(Collectors.toList());
            if (unique) {
                result.addAll(statsRepository.viewStatsUrisUnique(startDate, endDate, uris));
            } else {
                result.addAll(statsRepository.viewStatsUris(startDate, endDate, uris));
            }
        } else {
            if (unique) {
                result.addAll(statsRepository.viewStatsUnique(startDate, endDate));
            } else {
                result.addAll(statsRepository.viewStats(startDate, endDate));
            }
        }
        return result;
    }
}