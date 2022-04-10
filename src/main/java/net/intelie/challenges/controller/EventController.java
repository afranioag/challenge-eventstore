package net.intelie.challenges.controller;

import net.intelie.challenges.entity.Event;
import net.intelie.challenges.service.EventStoreImpl;
import net.intelie.challenges.util.EventIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventStoreImpl eventStore;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Event event){
        eventStore.insert(event);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String type){
        eventStore.removeAll(type);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event get(@PathVariable Long id){
        return eventStore.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Event> get(){
        return eventStore.query("fod-type", 16L, 17L);
    }
}
