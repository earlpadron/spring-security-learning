package dev.earl.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Authorities {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String authority;
}
