package at.spg.diplomprojektbackend.productperreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-per-reservations")
public class ProductPerReservationController {

    private final ProductPerReservationService productPerReservationService;

    @Autowired
    public ProductPerReservationController(ProductPerReservationService productPerReservationService) {
        this.productPerReservationService = productPerReservationService;
    }

    @GetMapping("/all")
    public List<ProductPerReservation> getAllProductPerReservations() {
        return productPerReservationService.getAllProductPerReservations();
    }

    @PostMapping("/add")
    public ProductPerReservation createProductPerReservation(@RequestBody ProductPerReservationDTO productPerReservationDTO) {
        return productPerReservationService.createProductPerReservation(productPerReservationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProductPerReservation(@PathVariable Long id) {
        productPerReservationService.deleteProductPerReservation(id);
    }
}
