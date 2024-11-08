package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.Booking;
import marcowidesott.BackPJM2W3.entities.Event;
import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.NotFoundException;
import marcowidesott.BackPJM2W3.repositories.BookingRepository;
import marcowidesott.BackPJM2W3.repositories.EventRepository;
import marcowidesott.BackPJM2W3.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Prenota un evento per un utente.
     * 1. Verifica che l'evento esista.
     * 2. Verifica che l'utente esista.
     * 3. Crea una nuova prenotazione.
     */
    public Booking bookEvent(Long eventId, String username) {
        // 1. Verifico che l'evento esista
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        // 2. Verifico che l'utente esista
        User user = this.usersRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        // 3. Crea la prenotazione
        Booking booking = new Booking(user, event);
        return this.bookingRepository.save(booking);
    }
}
