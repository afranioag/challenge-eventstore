package net.intelie.challenges.controller;

import net.intelie.challenges.entity.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/events")
public class EventController {

    @GetMapping
    public ResponseEntity<Event> get(){
        Event event = new Event("festa", 123L);
        return ResponseEntity.ok().body(event);
    }
}
