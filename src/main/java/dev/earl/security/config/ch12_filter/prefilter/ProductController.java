//package dev.earl.security.config.ch12_filter.prefilter;
//
//import dev.earl.security.config.ch12_filter.spring_data_filter.ProductEntity;
//import dev.earl.security.config.ch12_filter.spring_data_filter.ProductEntityRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import dev.earl.security.config.ch12_filter.postfilter.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class ProductController {
//
//    private final ProductServicePreFilter productServicePreFilter;
//    private final ProductServicePostFilter productServicePostFilter;
//    private final ProductEntityRepository productEntityRepository;
//
//    public ProductController(ProductServicePreFilter productServicePreFilter, ProductServicePostFilter productServicePostFilter, ProductEntityRepository productEntityRepository) {
//        this.productServicePreFilter = productServicePreFilter;
//        this.productServicePostFilter = productServicePostFilter;
//        this.productEntityRepository = productEntityRepository;
//    }
//
//    //note: this is a mutation operation called on a GET Method which is non-standard
//    //this is only for practice purposes to avoid disabling csrf protection
//    @GetMapping("/sell")
//    public List<Product> sellProduct(){
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("beer", "nikolai"));
//        products.add(new Product("candy", "nikolai"));
//        products.add(new Product("chocolate", "julien"));
//
//        return productServicePreFilter.sellProducts(products);
//    }
//
//    @GetMapping("/find")
//    public List<Product> findProducts(){
//        return productServicePostFilter.findProducts();
//    }
//
//    @GetMapping("/products/{text}")
//    public List<ProductEntity> findProductsContaining(@PathVariable String text){
//        return productEntityRepository.findProductEntityByNameContains(text);
//    }
//
//}
