//package dev.earl.security.config.ch12_filter.spring_data_filter;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//
//import java.util.Objects;
//
//@Entity
//public class ProductEntity {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//    private String name;
//    private String owner;
//
//
//    public ProductEntity(Integer id, String name, String owner) {
//        this.id = id;
//        this.name = name;
//        this.owner = owner;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductEntity that = (ProductEntity) o;
//        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(owner, that.owner);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, owner);
//    }
//
//    @Override
//    public String toString() {
//        return "ProductEntity{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", owner='" + owner + '\'' +
//                '}';
//    }
//}
