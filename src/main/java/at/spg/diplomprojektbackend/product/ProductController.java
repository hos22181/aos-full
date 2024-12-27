package at.spg.diplomprojektbackend.product;

import at.spg.diplomprojektbackend.productperreservation.ProductPerReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all")
    public List<ProductDTO> returnAllProducts() {
        return productService.getProducts();
    }

    @PostMapping(path = "/add")
    public void addProduct(@RequestBody ProductDTO product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
    }

    @PatchMapping("/update")
    public void editProduct(@RequestBody ProductDTO productDTO){
        productService.editProduct(productDTO);
    }

    // UPDATE: Hinzufügung eines Produkts zu einer Reservation
    @PatchMapping("/add-to-reservation")
    public void addProductToReservation(@RequestBody ProductPerReservationDTO dto) {
        productService.addProductToReservation(dto);
    }

    // UPDATE: Entfernung eines Produkts aus einer Reservation
    @PatchMapping("/remove-from-reservation")
    public void removeProductFromReservation(@RequestBody ProductPerReservationDTO dto) {
        productService.removeProductFromReservation(dto);
    }
}