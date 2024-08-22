package ru.practicum.explore.main.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.main.rating.model.Rating;
import ru.practicum.explore.main.rating.model.RatingId;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
    List<Rating> findAllById_EventId(Long eventId);
}