package marcowidesott.BackPJM2W3.controllers;

import jakarta.validation.Valid;
import marcowidesott.BackPJM2W3.entities.Event;
import marcowidesott.BackPJM2W3.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, @RequestParam String organizerUsername) {
        Event createdEvent = eventService.createEvent(event, organizerUsername);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event updatedEvent, @RequestParam String organizerUsername) {
        Event event = eventService.updateEvent(id, updatedEvent, organizerUsername);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id, @RequestParam String organizerUsername) {
        eventService.deleteEvent(id, organizerUsername);
        return ResponseEntity.ok("Evento eliminato con successo");
    }
}