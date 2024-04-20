package dev.earl.security.config.ch11_callAuthorization.permissions;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {
    public DocumentPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;

        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(p));
        //if the authenticated object has "admin" authority, they may view the data


        //if the authenticated object OWNS the retrieved data, they may view it
        return admin || document.getOwner().equals(authentication.getName());
    }


    private final DocumentRepository documentRepository;

    /**
     * In a similar manner, you can use the second PermissionEvaluator method to
     * write your authorization expression. The second method refers to using an
     * identifier and subject type instead of the object itself. For example, say that
     * we want to change the current example to apply the authorization rules before
     * the method is executed, using @PreAuthorize. In this case, we don’t have the
     * returned object yet. But instead of having the object itself, we have the
     * document’s code, which is its unique identifier.
     *
     * INSTEAD use @PreAuthorize inside DocumentService for getDocument() method
     * @PreAuthorize ("hasPermission(#code, 'document', 'ROLE_admin')")
     * public Document getDocument(String code) {
     * return documentRepository.findDocument(code);
     * }
     */
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);
        String p = (String) permission;
        boolean admin =
        authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));
        return admin ||
        document.getOwner().equals(
                authentication.getName());
    }



}
