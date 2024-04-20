package dev.earl.security.config.ch11_callAuthorization.permissions;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    /**
     * A service class defines a method that uses the repository to obtain a
     * document by its code.* The method in the service class is the one for which
     * we apply the authorization rules.
     *
     * The logic of the class is simple. It defines a
     * method that returns the Document by its unique code. We annotate this
     * method with @PostAuthorize and use a hasPermission() SpEL expression.
     * This method allows us to refer to an external authorization expression that we
     * implement further in this example.
     */

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    /** Meanwhile, observe that the parameters
     * we provide to the hasPermission() method are the returnObject, which
     * represents the value returned by the method, and the name of the role for
     * which we allow access, which is 'ROLE_admin'
     */
    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code){
        return documentRepository.findDocument(code);
    }
}
