package net.intelie.challenges.service;

import net.intelie.challenges.entity.Event;
import net.intelie.challenges.repository.EventRepository;
import net.intelie.challenges.util.EventIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EventStoreImpl implements EventStore{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void insert(Event event) {
        if(event == null){
            throw new RuntimeException("Event not null");
        }

        Event event1 = new Event(event.getType(), event.getTimestamp());
        eventRepository.save(event1);
    }

    @Override
    @Transactional
    public void removeAll(String type) {
        eventRepository.deleteAllByType(type);
    }

    @Override
    public List<Event> query(String type, long startTime, long endTime) {
        List<Event> events = eventRepository.findAllByType(type);

        events.forEach(event -> {
            if(event.getTimestamp() < startTime || event.getTimestamp() > endTime){
                events.remove(event);
            }
        });

        return events;
    }

    @Override
    public Event get(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(!eventOptional.isPresent()){
            throw new RuntimeException("Event not found");
        }

        return eventOptional.get();
    }
}
