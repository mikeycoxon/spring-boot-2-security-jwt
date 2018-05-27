package au.com.avmaint.api.access.service;

import au.com.avmaint.api.access.model.Role;
import au.com.avmaint.api.access.model.User;
import au.com.avmaint.api.access.repository.RoleRepository;
import au.com.avmaint.api.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Michael Coxon on 26/5/18.
 */
public interface UserService {

    User createUserWithRole(String username, String roleName);

    @Service
    final class UserServiceImpl implements UserService {

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Override
        public User createUserWithRole(String username, String roleName) {

            Role role = roleRepository.findByName(roleName);
            User user = User.of(username).addRole(role);

            return userRepository.save(user);
        }
    }

}
