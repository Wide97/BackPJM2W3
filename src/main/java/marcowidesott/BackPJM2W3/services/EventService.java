package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.Event;
import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.NotFoundException;
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

    /**
     * Crea un nuovo evento.
     * 1. Verifica che l'organizzatore esista.
     * 2. Crea e salva l'evento.
     */
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
}
