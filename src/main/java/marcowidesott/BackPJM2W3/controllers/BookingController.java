package marcowidesott.BackPJM2W3.controllers;


import marcowidesott.BackPJM2W3.entities.Booking;
import marcowidesott.BackPJM2W3.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> bookEvent(@RequestParam Long eventId, @RequestParam String username) {
        Booking booking = bookingService.bookEvent(eventId, username);
        return ResponseEntity.ok(booking);
    }
}
