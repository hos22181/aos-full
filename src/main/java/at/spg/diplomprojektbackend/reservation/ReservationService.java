package at.spg.diplomprojektbackend.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = convertToEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    public void deleteReservationById(Long reservationId) {
        boolean exists = reservationRepository.existsById(reservationId);
        if (!exists) {
            throw new IllegalStateException("Reservation with id " + reservationId + " does not exist");
        }
        reservationRepository.deleteById(reservationId);
    }

    public void updateReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationDTO.id());
        if (reservationOpt.isPresent()) {
            Reservation existingReservation = reservationOpt.get();

            if (reservationDTO.customer() != null) {
                existingReservation.setCustomer(reservationDTO.customer());
            }
            if (reservationDTO.productPerReservations() != null) {
                existingReservation.setProductPerReservations(reservationDTO.productPerReservations());
            }

            reservationRepository.save(existingReservation);
        } else {
            throw new IllegalArgumentException("Reservation not found with id: " + reservationDTO.id());
        }
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        return Reservation.builder()
                .customer(reservationDTO.customer())
                .productPerReservations(reservationDTO.productPerReservations())
                .build();
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getCustomer(),
                reservation.getProductPerReservations()
        );
    }
}
