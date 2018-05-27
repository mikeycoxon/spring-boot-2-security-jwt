package au.com.avmaint.api.access.repository;

import au.com.avmaint.api.access.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michael Coxon on 25/4/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
