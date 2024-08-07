package ru.practicum.ewm.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.dto.stats.ShortStatsDto;
import ru.practicum.ewm.dto.stats.StatsDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @PostMapping("/hit")
    public ResponseEntity<StatsDto> endpointHit(@Valid @RequestBody StatsDto statsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statsService.endpointHit(statsDto));
    }

    @GetMapping("/stats")
    public List<ShortStatsDto> viewStats(@RequestParam String start,
                                         @RequestParam String end,
                                         @RequestParam(required = false) List<String> uris,
                                         @RequestParam(defaultValue  = "false") Boolean unique,
                                         HttpServletRequest request) {
        return statsService.viewStats(start, end, uris, unique, request);
    }
}