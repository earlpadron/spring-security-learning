//package dev.earl.security.config.ch12_filter.prefilter;
//
//import org.springframework.security.access.prepost.PreFilter;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductServicePreFilter {
//
//    /**
//     * The Spring Expression Language (SpEL) used with
//     * the annotation is filterObject.owner == authentication.name, which
//     * allows only values where the owner attribute of the Product equals the
//     * username of the logged-in user. On the left side of the equals operator in the
//     * SpEL expression; we use filterObject. With filterObject, we refer to
//     * objects in the list as parameters. Because we have a list of products, the
//     * filterObject in our case is of type Product. For this reason, we can refer to
//     * the product’s owner attribute. On the right side of the equals operator in the
//     * expression; we use the authentication object. For the @PreFilter and
//     */
//
//    //sell products and return the sold products list
//    @PreFilter("filterObject.owner == authentication.name")
//    public List<Product> sellProducts(List<Product> products){
//        //any products provided in the parameter that does NOT match the authenticated
//        //user is filtered OUT before they are retrieved
//
//        return products;
//    }
//}
