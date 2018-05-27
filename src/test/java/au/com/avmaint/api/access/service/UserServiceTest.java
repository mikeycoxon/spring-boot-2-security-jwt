package au.com.avmaint.api.access.service;

import au.com.avmaint.api.access.model.Role;
import au.com.avmaint.api.access.model.User;
import au.com.avmaint.api.access.repository.RoleRepository;
import au.com.avmaint.api.access.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Michael Coxon on 26/5/18.
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfig {

        @Bean
        public UserService userService() {
            return new UserService.UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        Role admin = Role.of("ROLE_ADMIN", "do adminy things");

        User mike = User.of("mike@gmail.com").addRole(admin);

        Mockito.when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(admin);
        Mockito.when(userRepository.save(mike)).thenReturn(mike);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "mike@gmail.com";
        User found = userService.createUserWithRole("mike@gmail.com", "ROLE_ADMIN");

        assertThat(found.getUsername())
                .isEqualTo(name);
    }


}
