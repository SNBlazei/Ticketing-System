package service;

import model.Booking;
import org.springframework.stereotype.Service;
import repository.BookingRepository;

@Service
public class BookingService {
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}