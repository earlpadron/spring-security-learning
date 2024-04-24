//package dev.earl.security.config.ch12_filter.spring_data_filter;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.security.access.prepost.PostFilter;
//
//import java.util.List;
//
//public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
//
//    //using @PostFilter can lead to memory issues
// //   @PostFilter("filterObject.owner == authentication.name")
//
//    //@Query should be more efficient that using @PostFilter since
//    //it only retrieves data is relevant to the query rather than
//    //retrieving all the data and filtering afterwards
//    @Query("SELECT p FROM Product p " +
//            "WHERE p.name LIKE %:text% " +
//            "AND p.owner = ?#{authentication.name}")
//    List<ProductEntity> findProductEntityByNameContains(String text);
//}
