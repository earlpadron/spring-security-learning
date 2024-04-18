package dev.earl.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    private String identifier;
    private String token;

    public Token(){}
    public Token(Integer id, String identifier, String token) {
        this.id = id;
        this.identifier = identifier;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(id, token1.id) && Objects.equals(identifier, token1.identifier) && Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier, token);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
