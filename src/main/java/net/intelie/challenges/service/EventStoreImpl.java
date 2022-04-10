package net.intelie.challenges.service;

import net.intelie.challenges.entity.Event;
import net.intelie.challenges.repository.EventRepository;
import net.intelie.challenges.util.EventIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void removeAll(String type) {
        eventRepository.deleteAllByType(type);
    }

    @Override
    public EventIterator query(String type, long startTime, long endTime) {
        return null;
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
