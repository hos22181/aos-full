package at.spg.diplomprojektbackend;

import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.customer.CustomerRepository;
import at.spg.diplomprojektbackend.product.Category;
import at.spg.diplomprojektbackend.product.Unit;
import at.spg.diplomprojektbackend.product.Product;
import at.spg.diplomprojektbackend.product.ProductRepository;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservation;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservationRepository;
import at.spg.diplomprojektbackend.reservation.Reservation;
import at.spg.diplomprojektbackend.reservation.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config
{
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                        ReservationRepository reservationRepository,
                                        ProductPerReservationRepository productPerReservationRepository,
                                        CustomerRepository customerRepository) {
        return args -> {

            Product castorOil = Product.builder()
                    .pic_url_front("https://i.ibb.co/mz4ZS0v/castor-back.jpg")
                    .pic_url_back("https://i.ibb.co/QbnyNW8/castor-front.jpg")
                    .name("Castor Oil")
                    .price(2.9)
                    .weight(200)
                    .description("200 grams")
                    .category(Category.OIL)
                    .unit(Unit.G)
                    .build();

            Product garlicPaste = Product.builder()
                    .pic_url_front("https://i.ibb.co/4ZR42sW/garlic-1kg-front.jpg")
                    .pic_url_back("https://i.ibb.co/c6xHnT2/garlic-1kg-back.jpg")
                    .name("Garlic Paste")
                    .price(13.99)
                    .weight(1000)
                    .description("1000 grams")
                    .category(Category.SPICES)
                    .unit(Unit.G)
                    .build();

            Product kleineGarlicDings = Product.builder()
                    .pic_url_front("https://i.ibb.co/LCdtCcM/garlic-mini-front.jpg")
                    .pic_url_back("https://i.ibb.co/y5N8jsY/garlic-mini-back.jpg")
                    .name("Kleine Garlic Dings")
                    .price(3.2)
                    .weight(500)
                    .description("500 grams")
                    .category(Category.SPICES)
                    .unit(Unit.G)
                    .build();

            Product iwasGelbes = Product.builder()
                    .pic_url_front("https://i.ibb.co/p2z2gsJ/gelb-front.jpg")
                    .pic_url_back("https://i.ibb.co/gP6192z/gelb-back.jpg")
                    .name("Iwas Gelbes")
                    .price(15.2)
                    .weight(200)
                    .description("200 grams")
                    .category(Category.OTHER)
                    .unit(Unit.G)
                    .build();

            Product haribo = Product.builder()
                    .pic_url_front("https://i.ibb.co/qRNS8bz/haribo-front.jpg")
                    .pic_url_back("https://i.ibb.co/rFxxjrL/haribo-back.jpg")
                    .name("Haribo")
                    .price(1.0)
                    .weight(90)
                    .description("90 grams")
                    .category(Category.SWEETS)
                    .unit(Unit.G)
                    .build();

            Product ka = Product.builder()
                    .pic_url_front("https://i.ibb.co/gzRVy40/ka-was-front.jpg")
                    .pic_url_back("https://i.ibb.co/4PD1kk2/ka-was-back.jpg")
                    .name("Ka")
                    .price(5.9)
                    .weight(1000)
                    .description("1 kilo")
                    .category(Category.PRE_PREPARED_FOOD)
                    .unit(Unit.KG)
                    .build();

            Product maggiNoodles = Product.builder()
                    .pic_url_front("https://i.ibb.co/c1SV403/maggi-front.jpg")
                    .pic_url_back("https://i.ibb.co/xq37Sck/maggi-back.jpg")
                    .name("Maggi Noodles")
                    .price(5.9)
                    .weight(2000)
                    .description("2 kilos")
                    .category(Category.PRE_PREPARED_FOOD)
                    .unit(Unit.KG)
                    .build();

            Product nidoPowderMilk = Product.builder()
                    .pic_url_front("https://i.ibb.co/g7dVMWQ/nido-front.jpg")
                    .pic_url_back("https://i.ibb.co/MG4Rmns/nido-back.jpg")
                    .name("Nido Powder Milk")
                    .price(5.9)
                    .weight(1000)
                    .description("1 kilo")
                    .category(Category.OTHER)
                    .unit(Unit.KG)
                    .build();

            Product srirachaSauce = Product.builder()
                    .pic_url_front("https://i.ibb.co/1n7N9YJ/sriracha-front.jpg")
                    .pic_url_back("https://i.ibb.co/8PHVV7D/sriracha-back.jpg")
                    .name("Sriracha Sauce")
                    .price(5.9)
                    .weight(1000)
                    .description("1 kilo")
                    .category(Category.SAUCES)
                    .unit(Unit.KG)
                    .build();

            Product vodka = Product.builder()
                    .pic_url_front("https://i.ibb.co/S0CQRPC/vodka-front.jpg")
                    .pic_url_back("https://i.ibb.co/3vJhhzQ/vodka-back.jpg")
                    .name("Vodka")
                    .price(35.9)
                    .weight(1000)
                    .description("1 liter")
                    .category(Category.ALCOHOLIC_DRINKS)
                    .unit(Unit.L)
                    .build();

            Product spicySauce = Product.builder()
                    .pic_url_front("https://i.ibb.co/LPHPHZF/sause.jpg")
                    .pic_url_back("https://i.ibb.co/MG4Rmns/nido-back.jpg")
                    .name("Spicy Sauce")
                    .price(2.5)
                    .weight(300)
                    .description("300 grams")
                    .category(Category.SAUCES)
                    .unit(Unit.G)
                    .build();

            Product ghee = Product.builder()
                    .pic_url_front("https://i.ibb.co/6FxtMRf/ghee.jpg")
                    .pic_url_back("https://i.ibb.co/MG4Rmns/nido-back.jpg")
                    .name("Ghee")
                    .price(2.9)
                    .weight(200)
                    .description("200 grams")
                    .category(Category.OIL)
                    .unit(Unit.G)
                    .build();

            Product ingwerPaste = Product.builder()
                    .pic_url_front("https://i.ibb.co/7WCrgzn/ingwer.jpg")
                    .pic_url_back("https://i.ibb.co/6FxtMRf/ghee.jpg")
                    .name("Ingwer Paste")
                    .price(3.99)
                    .weight(500)
                    .description("500 grams")
                    .category(Category.SPICES)
                    .unit(Unit.G)
                    .build();

            Product rohAfza = Product.builder()
                    .pic_url_front("https://i.ibb.co/DpqzJPs/roh-afza.jpg")
                    .pic_url_back("https://i.ibb.co/DpqzJPs/roh-afza.jpg")
                    .name("Roh Afza")
                    .price(5.2)
                    .weight(200)
                    .description("200 grams")
                    .category(Category.NON_ALCOHOLIC_DRINKS)
                    .unit(Unit.ML)
                    .build();

            Product cocoMilk = Product.builder()
                    .pic_url_front("https://i.ibb.co/R6MNwSZ/coco-milk.jpg")
                    .pic_url_back("https://i.ibb.co/DpqzJPs/roh-afza.jpg")
                    .name("Coco Milk")
                    .price(10.0)
                    .weight(1000)
                    .description("1 kilo")
                    .category(Category.NON_ALCOHOLIC_DRINKS)
                    .unit(Unit.L)
                    .build();

            Product plumSauce = Product.builder()
                    .pic_url_front("https://i.ibb.co/LPHPHZF/sause.jpg")
                    .pic_url_back("https://i.ibb.co/DpqzJPs/roh-afza.jpg")
                    .name("Plum Sauce")
                    .price(5.9)
                    .weight(1000)
                    .description("1 kilo")
                    .category(Category.SAUCES)
                    .unit(Unit.KG)
                    .build();







            Customer customer = Customer.builder()
                    .email("dababy@gmail.com")
                    .password("123")
                    .build();

            ProductPerReservation productPerReservationMango=ProductPerReservation.builder()
                    .product(castorOil)
                    .quantity(2)
                    .build();
            ProductPerReservation productPerReservationReis=ProductPerReservation.builder()
                    .product(castorOil)
                    .quantity(10)
                    .build();

            Reservation reservation=Reservation.builder()
                    .customer(customer)
                    .productPerReservations(List.of(productPerReservationMango,productPerReservationReis))
                    .build();


            productRepository.saveAll(List.of(castorOil,garlicPaste,kleineGarlicDings,iwasGelbes,haribo,ka,maggiNoodles,nidoPowderMilk,srirachaSauce,vodka,spicySauce,ghee,ingwerPaste,rohAfza,cocoMilk,plumSauce));
            customerRepository.save(customer);
            reservationRepository.save(reservation);
            productPerReservationRepository.saveAll(List.of(productPerReservationMango,productPerReservationReis));

        };
    }
}
