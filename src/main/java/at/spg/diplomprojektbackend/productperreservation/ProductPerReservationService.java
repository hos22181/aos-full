package at.spg.diplomprojektbackend.productperreservation;

import at.spg.diplomprojektbackend.product.Product;
import at.spg.diplomprojektbackend.product.ProductRepository;
import at.spg.diplomprojektbackend.reservation.Reservation;
import at.spg.diplomprojektbackend.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPerReservationService {

    private final ProductPerReservationRepository productPerReservationRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ProductPerReservationService(ProductPerReservationRepository productPerReservationRepository,
                                        ProductRepository productRepository,
                                        ReservationRepository reservationRepository) {
        this.productPerReservationRepository = productPerReservationRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ProductPerReservation> getAllProductPerReservations() {
        return productPerReservationRepository.findAll();
    }

    public ProductPerReservation createProductPerReservation(ProductPerReservationDTO productPerReservationDTO) {
        Product product = productRepository.findById(productPerReservationDTO.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Reservation reservation = reservationRepository.findById(productPerReservationDTO.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        ProductPerReservation productPerReservation = ProductPerReservation.builder()
                .product(product)
                .reservation(reservation)
                .quantity(productPerReservationDTO.quantity())
                .build();

        return productPerReservationRepository.save(productPerReservation);
    }

    public void deleteProductPerReservation(Long id) {
        productPerReservationRepository.deleteById(id);
    }
}
