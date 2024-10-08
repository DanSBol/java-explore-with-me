package ru.practicum.explore.main.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.main.event.model.Event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.practicum.explore.main.event.model.EventState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EventCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Event> findAllByInitiatorIdInAndStateInAndCategoryIdInAndEventDateBetween(
            List<Long> users,
            List<EventState> states,
            List<Long> categories,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            Pageable pageable) {
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Predicate predicate = createAdminPredicate(users, states, categories, rangeStart, rangeEnd, eventRoot);
        criteriaQuery.where(predicate);

        return getEvents(pageable, criteriaQuery);
    }

    private Page<Event> getEvents(Pageable pageable, CriteriaQuery<Event> criteriaQuery) {
        TypedQuery<Event> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Event> events = typedQuery.getResultList();

        return new PageImpl<>(events);
    }

    public Page<Event> findAllByTextAndCategoryIdInAndPaidAndEventDateBetweenAndAvailable(
            String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
            LocalDateTime rangeEnd, Boolean available, Pageable pageable) {

        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Predicate predicate = createUserPredicate(text, categories, paid, rangeStart, rangeEnd, available, eventRoot);
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), eventRoot, criteriaBuilder));
        return getEvents(pageable, criteriaQuery);
    }

    private Predicate createAdminPredicate(List<Long> users, List<EventState> states,
                                           List<Long> categories, LocalDateTime rangeStart,
                                           LocalDateTime rangeEnd, Root<Event> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (users != null) {
            predicates.add(root.get("initiator").get("id").in(users));
        }
        if (states != null) {
            predicates.add(root.get("state").in(states));
        }
        if (categories != null) {
            predicates.add(root.get("category").get("id").in(categories));
        }
        predicates.addAll(getEventDatePredicates(rangeStart, rangeEnd, root));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate createUserPredicate(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd, Boolean available, Root<Event> root) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("state"), EventState.PUBLISHED));
        if (text != null) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("annotation")), "%" +
                            text.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" +
                            text.toLowerCase() + "%")
            ));
        }
        if (categories != null) {
            predicates.add(root.get("category").get("id").in(categories));
        }
        if (paid != null) {
            predicates.add(criteriaBuilder.equal(root.get("paid"), paid));
        }
        if (available != null) {
            predicates.add(criteriaBuilder.equal(root.get("available"), true));
        }
        predicates.addAll(getEventDatePredicates(rangeStart, rangeEnd, root));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private List<Predicate> getEventDatePredicates(LocalDateTime rangeStart, LocalDateTime rangeEnd, Root<Event> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (rangeStart != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("eventDate"), rangeStart));
        }
        if (rangeEnd != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("eventDate"), rangeEnd));
        }
        if (rangeStart == null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("eventDate"), LocalDateTime.now()));
        }
        return predicates;
    }
}