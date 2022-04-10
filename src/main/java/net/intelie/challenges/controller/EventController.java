package net.intelie.challenges.controller;

import net.intelie.challenges.entity.Event;
import net.intelie.challenges.service.EventStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
