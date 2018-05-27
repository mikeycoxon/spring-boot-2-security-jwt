package au.com.avmaint.api.aircraft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Michael Coxon on 24/4/18.
 */
@Entity
public class Approval {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    private String approvalNumber;

    private Date expires;

    public static Approval of(Long id, String description, String approvalNumber, Date expires) {

        Approval approval = new Approval();
        approval.id = id;
        approval.description = description;
        approval.approvalNumber = approvalNumber;
        approval.expires = expires;

        return approval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
