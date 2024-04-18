package dev.earl.security.repository;

import dev.earl.security.model.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository; //use this to retrieve or save CSRF tokens in the database

    public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    //saves token for specific client
    //we assume client has unique identifier rather than default behavior which stores the unique session id to identify requests

    /**
     * In our case, we assume that the
     * client has a unique identifier. The client sends the value of its unique ID in
     * the request with the header named X-IDENTIFIER. In the method logic, we
     * check whether the value exists in the database. If it exists, we update the
     * database with the new value of the token. If not, we create a new record for
     * this ID with the new value of the CSRF token.
     * @param csrfToken the {@link CsrfToken} to save or null to delete
     * @param request the {@link HttpServletRequest} to use
     * @param response the {@link HttpServletResponse} to use
     */
    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken =
        jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) { //if repository finds this token, renew token
            Token token = existingToken.get();
            token.setToken(csrfToken.getToken());
        } else {  //no existing token found in the repository, create a new token
            Token token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(identifier);
            jpaTokenRepository.save(token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken =
                jpaTokenRepository
                        .findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            return new DefaultCsrfToken(
                    "X-CSRF-TOKEN",
                    "_csrf",
                    token.getToken());
        }
        return null;
    }
}
