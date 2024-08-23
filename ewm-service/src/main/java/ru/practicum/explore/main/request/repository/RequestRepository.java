package ru.practicum.explore.main.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.main.request.model.Request;
import ru.practicum.explore.main.request.model.RequestStatus;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByEventId(Long eventId);

    List<Request> findAllByRequesterId(Long userId);

    List<Request> findAllByRequesterIdAndEventId(Long userId, Long eventId);

    List<Request> findAllByEventIdAndIdIsIn(Long eventId, List<Long> requestIds);

    List<Request> findAllByStatusAndIdIsIn(RequestStatus status, List<Long> requestIds);

    List<Request> findAllByStatusAndEventId(RequestStatus status, Long id);
}