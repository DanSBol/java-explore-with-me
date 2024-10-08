package ru.practicum.explore.main.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.main.category.dto.CategoryDto;
import ru.practicum.explore.main.category.dto.NewCategoryDto;
import ru.practicum.explore.main.category.service.CategoryService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Добавление новой категории category={}", newCategoryDto);
        return new ResponseEntity<>(categoryService.createCategory(newCategoryDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long categoryId) {
        log.info("Удаление категории id={}", categoryId);
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> patchCategory(@PathVariable Long categoryId,
                                                     @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Изменение категории id={}, category={}", categoryId, categoryDto);
        return new ResponseEntity<>(categoryService.update(categoryId, categoryDto), HttpStatus.OK);
    }
}
