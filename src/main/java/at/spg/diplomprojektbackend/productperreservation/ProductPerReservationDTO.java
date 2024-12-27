package at.spg.diplomprojektbackend.productperreservation;

public record ProductPerReservationDTO(
        Long productPerReservationId,
        Long productId,
        Long reservationId,
        Integer quantity
) {
}
