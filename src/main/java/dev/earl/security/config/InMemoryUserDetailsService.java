//package dev.earl.security.config;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//
///**
// * Custom Implementation of UserDetailsService
// */
//public class InMemoryUserDetailsService implements UserDetailsService {
//
//    private final List<UserDetails> users;
//
//
//    public InMemoryUserDetailsService(List<UserDetails> users) {
//        this.users = users;
//    }
//
//    /**
//     * The loadUserByUsername(String username) method searches the list of
//     * users for the given username and returns the desired UserDetails instance. If
//     * there is no instance with that username, it throws a
//     * UsernameNotFoundException.
//     * @param username the username identifying the user whose data is required.
//     * @return desired UserDetails instance
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return users.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//}
