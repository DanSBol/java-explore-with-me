package ru.practicum.ewm.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.dto.stats.ShortStatsDto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(nativeQuery = true)
    List<ShortStatsDto> viewStats(LocalDateTime start, LocalDateTime end);

    @Query(nativeQuery = true)
    List<ShortStatsDto> viewStatsUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(nativeQuery = true)
    List<ShortStatsDto> viewStatsUnique(LocalDateTime start, LocalDateTime end);

    @Query(nativeQuery = true)
    List<ShortStatsDto> viewStatsUrisUnique(LocalDateTime start, LocalDateTime end, List<String> uris);
}