package au.com.avmaint.api.access.repository;

import au.com.avmaint.api.access.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michael Coxon on 17/4/18.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
    // commands - must not return a value
    // void create
    // void update
    // void delete

    // queries - must have no side effects
    // Object get
    // Object getAll

    // these really need to be two separate interfaces, not one as spring.j would have it
}