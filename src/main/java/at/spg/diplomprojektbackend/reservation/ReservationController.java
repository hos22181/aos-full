package at.spg.diplomprojektbackend.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/add")
    public void addReservation(@RequestBody ReservationDTO reservationDTO) {
        reservationService.addReservation(reservationDTO);
    }

    @DeleteMapping("/delete/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
    }

    @PatchMapping("/update")
    public void updateReservation(@RequestBody ReservationDTO reservationDTO) {
        reservationService.updateReservation(reservationDTO);
    }
}
