package au.com.avmaint.api.aircraft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Michael Coxon on 24/4/18.
 */
@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

}
