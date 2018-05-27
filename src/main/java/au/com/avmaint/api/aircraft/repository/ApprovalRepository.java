package au.com.avmaint.api.aircraft.repository;

import au.com.avmaint.api.aircraft.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Michael Coxon on 26/5/18.
 */
public interface ApprovalRepository extends JpaRepository<Approval, Long> {


}
