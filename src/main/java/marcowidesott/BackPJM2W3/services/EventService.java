package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.Event;
import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.NotFoundException;
import marcowidesott.BackPJM2W3.exceptions.UnauthorizedException;
import marcowidesott.BackPJM2W3.repositories.EventRepository;
import marcowidesott.BackPJM2W3.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Event createEvent(Event body, String organizerUsername) {
        // 1. Verifico che l'organizzatore esista
        User organizer = this.usersRepository.findByUsername(organizerUsername)
                .orElseThrow(() -> new NotFoundException("Organizzatore non trovato"));

        // 2. Creo un nuovo evento
        body.setOrganizer(organizer);
        return this.eventRepository.save(body);
    }

    /**
     * Ottiene tutti gli eventi disponibili.
     */
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    /**
     * Ottiene un evento per ID.
     */
    public Event getEventById(Long eventId) {
        return this.eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));
    }

    public Event updateEvent(Long eventId, Event updatedEvent, String organizerUsername) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        // Controllo se l'organizzatore corrisponde
        if (!existingEvent.getOrganizer().getUsername().equals(organizerUsername)) {
            throw new UnauthorizedException("Non sei autorizzato a modificare questo evento");
        }

        // Aggiorno i dettagli dell'evento
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setMaxParticipants(updatedEvent.getMaxParticipants());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long eventId, String organizerUsername) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        // Verifica che l'utente sia l'organizzatore
        if (!existingEvent.getOrganizer().getUsername().equals(organizerUsername)) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo evento");
        }

        eventRepository.delete(existingEvent);
    }
}
