package ru.practicum.explore.main.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.main.compilation.dto.CompilationDto;
import ru.practicum.explore.main.compilation.service.CompilationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationPublicController {
    private final CompilationService compilationService;

    @GetMapping
    public ResponseEntity<List<CompilationDto>> getAllCompilations(@RequestParam(required = false) Boolean pinned,
                                                                   @RequestParam(defaultValue = "0") Integer from,
                                                                   @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение подборок событий pinned={}, from={}, size={}", pinned, from, size);
        return new ResponseEntity<>(compilationService.getAllCompilations(pinned, from, size), HttpStatus.OK);
    }

    @GetMapping("/{compilationId}")
    public ResponseEntity<CompilationDto> getCompilationById(@PathVariable Long compilationId) {
        log.info("Получение подборки событий по его id={}", compilationId);
        return new ResponseEntity<>(compilationService.getCompilationDtoById(compilationId), HttpStatus.OK);
    }
}