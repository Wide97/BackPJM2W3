package marcowidesott.BackPJM2W3.repositories;

import marcowidesott.BackPJM2W3.entities.Booking;
import marcowidesott.BackPJM2W3.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    long countByEvent(Event event);
}
