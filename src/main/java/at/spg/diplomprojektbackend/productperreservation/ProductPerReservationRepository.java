package at.spg.diplomprojektbackend.productperreservation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPerReservationRepository extends JpaRepository<ProductPerReservation, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductPerReservation ppr WHERE ppr.product.id = :productId AND ppr.reservation.id = :reservationId")
    void deleteByProductIdAndReservationId(Long productId, Long reservationId);
}
