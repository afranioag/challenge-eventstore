package net.intelie.challenges.service;

import net.intelie.challenges.entity.Event;
import net.intelie.challenges.util.EventIterator;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import java.util.List;

/**
 * An abstraction of an event store.
 * <p>
 * Events may be stored in memory, data files, a database, on a remote
 * server, etc. For this challenge, you should implement an in-memory
 * event event store.
 */
public interface EventStore {
    /**
     * Stores an event
     *
     * @param event
     */
    void insert(Event event);


    /**
     * Removes all events of specific type.
     *
     * @param type
     */
    void removeAll(String type);

    /**
     * Retrieves an iterator for events based on their type and timestamp.
     *
     * @param type      The type we are querying for.
     * @param startTime Start timestamp (inclusive).
     * @param endTime   End timestamp (exclusive).
     * @return An iterator where all its events have same type as
     * {@param type} and timestamp between {@param startTime}
     * (inclusive) and {@param endTime} (exclusive).
     */
    List<Event> query(String type, long startTime, long endTime);

    Event get(Long id);
}
