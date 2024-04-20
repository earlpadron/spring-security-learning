package dev.earl.security.config.ch11_callAuthorization.permissions;

import dev.earl.security.config.ch11_callAuthorization.permissions.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {

    private Map<String, Document> documents =
    Map.of("abc123", new Document("natalie"),
    "qwe123", new Document("natalie"),
    "asd555", new Document("emma"));
    public Document findDocument(String code) {
        return documents.get(code);
    }
}
