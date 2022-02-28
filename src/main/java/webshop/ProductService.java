package webshop;

public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void saleProduct(long id, int amount) {
        Product product = productRepository.findProductById(id);
        validate(product, amount);
        productRepository.updateProductStock(id, amount);
    }

    private void validate(Product product, int amount) {
        if (product.getStock() < amount) {
            throw new IllegalArgumentException("Not enough product!");
        }
    }
}
