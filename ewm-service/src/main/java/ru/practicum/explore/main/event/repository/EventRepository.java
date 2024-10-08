package ru.practicum.explore.main.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.main.event.model.Event;
import ru.practicum.explore.main.event.model.EventState;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    Event findByIdAndState(Long id, EventState published);

    List<Event> findAllByIdIsIn(List<Long> eventIds);
}