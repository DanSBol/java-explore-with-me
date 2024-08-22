package ru.practicum.explore.main.rating.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
public class RatingId implements Serializable {
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Long userId;
}