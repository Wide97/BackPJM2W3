package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.Booking;
import marcowidesott.BackPJM2W3.entities.Event;
import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.BadRequestException;
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

    public Booking bookEvent(Long eventId, String username) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        // Verifica se ci sono ancora posti disponibili
        long currentBookings = bookingRepository.countByEvent(event);
        if (currentBookings >= event.getMaxParticipants()) {
            throw new BadRequestException("L'evento è al completo");
        }

        Booking booking = new Booking(user, event);
        return bookingRepository.save(booking);
    }
}
