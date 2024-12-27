package at.spg.diplomprojektbackend.reservation;

import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.product.Product;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservation;

import java.util.List;

public record ReservationDTO(
        Long id,
        Customer customer,
        List<ProductPerReservation> productPerReservations
) {
}