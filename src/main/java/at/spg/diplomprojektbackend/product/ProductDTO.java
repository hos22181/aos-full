package at.spg.diplomprojektbackend.product;

public record ProductDTO (
        Integer id,
        String name,
        Category category,
        Double price,
        Integer weight,
        Unit unit,
        String pic_url_front,
        String pic_url_back,
        String description
) {
}
