package dev.earl.security.config.ch11_callAuthorization.preAuth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private Map<String, List<String>> secretNames = Map.of("natalie", List.of("Energico", "Perfecto"),
                                                           "emma", List.of("Fantastico"));
    @PreAuthorize("hasAuthority('write')")
    public String getName(){
        return "Fantastico";
    }


    /**
     * In this expression, we use #name to refer to the value of the
     * getSecretNames() method parameter called name, and we have access
     * directly to the authentication object that we can use to refer to the currently
     * authenticated user. The expression we use indicates that the method can be
     * called only if the authenticated user’s username is the same as the value sent
     * through the method’s parameter. In other words, a user can only retrieve its
     * own secret names.
     */
    @PreAuthorize("#name == authentication.principal.username")

    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
