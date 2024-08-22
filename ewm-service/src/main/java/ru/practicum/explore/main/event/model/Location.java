package ru.practicum.explore.main.event.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Embeddable;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Location {
    private Float lat;
    private Float lon;
}