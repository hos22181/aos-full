package at.spg.diplomprojektbackend.product;

import at.spg.diplomprojektbackend.productperreservation.ProductPerReservation;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservationDTO;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservationRepository;
import at.spg.diplomprojektbackend.reservation.Reservation;
import at.spg.diplomprojektbackend.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final ProductPerReservationRepository productPerReservationRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ReservationRepository reservationRepository,
                          ProductPerReservationRepository productPerReservationRepository) {
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
        this.productPerReservationRepository = productPerReservationRepository;
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public void addNewProduct(ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findProductByName(productDTO.name());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product " + productDTO.name() + " already exists");
        }
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("Product with id " + productId + " does not exist");
        }
        productRepository.deleteById(productId);
    }

    public void editProduct(ProductDTO productDTO) {
        Optional<Product> productOpt = productRepository.findById(Long.valueOf(productDTO.id()));
        if (productOpt.isPresent()) {
            Product existingProduct = productOpt.get();

            if (productDTO.name() != null) {
                existingProduct.setName(productDTO.name());
            }
            if (productDTO.category() != null) {
                existingProduct.setCategory(productDTO.category());
            }
            if(productDTO.unit() != null) {
                existingProduct.setUnit(productDTO.unit());
            }
            if (productDTO.price() != null) {
                existingProduct.setPrice(productDTO.price());
            }
            if (productDTO.weight() != null) {
                existingProduct.setWeight(productDTO.weight());
            }
            if (productDTO.pic_url_front() != null) {
                existingProduct.setPic_url_front(productDTO.pic_url_front());
            }
            if (productDTO.pic_url_back() != null) {
                existingProduct.setPic_url_back(productDTO.pic_url_back());
            }
            if (productDTO.description() != null) {
                existingProduct.setDescription(productDTO.description());
            }

            productRepository.save(existingProduct);
        } else {
            throw new IllegalArgumentException("Product not found with id: " + productDTO.id());
        }
    }

    public void addProductToReservation(ProductPerReservationDTO dto) {
        Optional<Product> product = productRepository.findById(dto.productId());
        Optional<Reservation> reservation = reservationRepository.findById(dto.reservationId());

        if (product.isPresent() && reservation.isPresent()) {
            ProductPerReservation ppr = new ProductPerReservation(
                    product.get(),
                    reservation.get(),
                    dto.quantity()
            );
            productPerReservationRepository.save(ppr);
        } else {
            throw new IllegalArgumentException("Product or Reservation not found.");
        }
    }

    public void removeProductFromReservation(ProductPerReservationDTO dto) {
        productPerReservationRepository.deleteByProductIdAndReservationId(
                dto.productId(), dto.reservationId());
    }

    // Konvertierung  ProductDTO --> Product
    private Product convertToEntity(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.name())
                .category(productDTO.category())
                .unit(productDTO.unit())
                .price(productDTO.price())
                .weight(productDTO.weight())
                .pic_url_front(productDTO.pic_url_front())
                .pic_url_back(productDTO.pic_url_back())
                .description(productDTO.description())
                .build();
    }

    // Konvertierung  Product --> ProductDTO
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                Math.toIntExact(product.getId()),
                product.getName(),
                product.getCategory(),

                product.getPrice(),
                product.getWeight(),

                product.getUnit(),
                product.getPic_url_front(),
                product.getPic_url_back(),
                product.getDescription()
        );
    }
}