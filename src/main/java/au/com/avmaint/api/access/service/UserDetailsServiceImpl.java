package au.com.avmaint.api.access.service;

import au.com.avmaint.api.access.model.User;
import au.com.avmaint.api.access.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security doesn't come with a concrete implementation of UserDetailsService that we could use
 * out of the box with our database.
 *
 * So this overrides it.
 *
 * The only method that we had to implement is loadUserByUsername. When a user tries to authenticate,
 * this method receives the username, searches the database for a record containing it, and (if found)
 * returns an instance of User. The properties of this instance (username and password) are then checked
 * against the credentials passed by the user in the login request. This last process is executed outside
 * this class, by the Spring Security framework.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository applicationUserRepository;

    public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }
}