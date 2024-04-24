//package dev.earl.security.config.ch12_filter.postfilter;
//import dev.earl.security.config.ch12_filter.prefilter.Product;
//import org.springframework.security.access.prepost.PostFilter;
//import org.springframework.security.access.prepost.PreFilter;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ProductServicePostFilter {
//
//    /**
//     * I annotate the findProducts() method, which returns the list of products,
//     * with the @PostFilter annotation. The condition I add as the value of the
//     * annotation, filterObject.owner == authentication.name, only allows
//     * products to be returned that have the owner equal to the authenticated user
//     * (figure 12.8). On the left side of the equals operator, we use filterObject to
//     * refer to elements inside the returned collection. On the right side of the
//     * operator, we use authentication to refer to the Authentication object stored
//     * in the SecurityContext.
//     */
//
//    //sell products and return the sold products list
//    @PostFilter("filterObject.owner == authentication.name")
//    public List<Product> findProducts(){
//        //typically this data is retrieved from the database
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("beer", "nikolai"));
//        products.add(new Product("candy", "nikolai"));
//        products.add(new Product("chocolate", "julien"));
//        return products;
//        //products returned from this method are first filtered out based on
//        //the criteria provided inside the @PostFilter annotation
//
//        //no exception is thrown if criteria fails, only filters out
//        //values that does not meet criteria
//    }
//}
