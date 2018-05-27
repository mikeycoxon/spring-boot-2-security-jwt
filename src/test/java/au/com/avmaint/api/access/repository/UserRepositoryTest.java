package au.com.avmaint.api.access.repository;

import au.com.avmaint.api.access.model.Role;
import au.com.avmaint.api.access.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Michael Coxon on 26/5/18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Role admin = Role.of("ROLE_ADMIN", "do adminy things");
        Long id = entityManager.persist(admin).getId();
//        System.out.println("roleId " + id);
//        entityManager.flush();
        User mike = User.of("mike@gmail.com").addRole(admin);
//        System.out.println(mike);
        entityManager.persist(mike);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername("mike@gmail.com");

        // then
        assertThat(found.getUsername())
                .isEqualTo(mike.getUsername());
    }

}
