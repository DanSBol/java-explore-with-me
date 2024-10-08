package ru.practicum.explore.main.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.main.event.dto.EventFullDto;
import ru.practicum.explore.main.event.model.FilterSort;
import ru.practicum.explore.main.event.service.EventService;
import ru.practicum.explore.main.exceptions.RequestValidationException;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventPublicController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventFullDto>> getAllEvents(@RequestParam(required = false) String text,
                                                           @RequestParam(required = false) List<Long> categories,
                                                           @RequestParam(required = false) Boolean paid,
                                                           @RequestParam(required = false)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                               LocalDateTime rangeStart,
                                                           @RequestParam(required = false)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                               LocalDateTime rangeEnd,
                                                           @RequestParam(required = false, defaultValue = "false")
                                                               Boolean onlyAvailable,
                                                           @RequestParam(required = false, defaultValue = "EVENT_DATE")
                                                               FilterSort sort,
                                                           @RequestParam(defaultValue = "0") Integer from,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           HttpServletRequest request) {
        log.info("Получение событий с параметрами text={}, categories={}, paid={}, rangeStart={}, rangeEnd={}, " +
                        "onlyAvailable={}, sort={}, from={}, size={}, uri={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request.getRequestURI());
        if (rangeStart != null && rangeEnd != null && rangeStart.isAfter(rangeEnd)) {
            throw new RequestValidationException("Error dates", "Дата начала диапозона должна быть меньше даты конца.");
        }
        return new ResponseEntity<>(
                eventService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size,
                        request),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable Long id, HttpServletRequest request) {
        log.info("Получение подробной информации об опубликованном событии по его id={}, uri={}", id,
                request.getRequestURI());
        return new ResponseEntity<>(eventService.getEventById(id, request), HttpStatus.OK);
    }
}