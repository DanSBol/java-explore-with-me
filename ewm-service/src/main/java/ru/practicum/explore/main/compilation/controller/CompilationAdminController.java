package ru.practicum.explore.main.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.main.compilation.dto.CompilationDto;
import ru.practicum.explore.main.compilation.dto.NewCompilationDto;
import ru.practicum.explore.main.compilation.dto.UpdateCompilationRequest;
import ru.practicum.explore.main.compilation.service.CompilationService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {
    private final CompilationService compilationService;

    @PostMapping
    public ResponseEntity<CompilationDto> createCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("Добавление новой подборки compilation={}", newCompilationDto);
        return new ResponseEntity<>(compilationService.createCompilation(newCompilationDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{compilationId}")
    public ResponseEntity<CompilationDto> patchCompilation(@PathVariable Long compilationId,
                                                           @RequestBody @Valid UpdateCompilationRequest
                                                                   updateCompilationRequest) {
        log.info("Обновление информации о подборке id={}, compilation={}", compilationId, updateCompilationRequest);
        return new ResponseEntity<>(compilationService.updateCompilation(compilationId, updateCompilationRequest),
                HttpStatus.OK);
    }

    @DeleteMapping("/{compilationId}")
    public ResponseEntity<Boolean> deleteCompilation(@PathVariable Long compilationId) {
        log.info("Удаление подборки id={}", compilationId);
        compilationService.deleteCompilationById(compilationId);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }
}