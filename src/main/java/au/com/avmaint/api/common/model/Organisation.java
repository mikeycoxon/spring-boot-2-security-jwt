package au.com.avmaint.api.common.model;

import au.com.avmaint.api.aircraft.model.Aircraft;
import au.com.avmaint.api.aircraft.model.Approval;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Michael Coxon on 17/4/18.
 */
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String abn;

    private String name;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "principal_id")
    private Person principal;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrgRole role;

    @OneToMany(orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Approval> approvals;

    @OneToMany(orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Approval> approvedEquipments;

    @OneToMany(orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Organisation> subcontractors;

    @OneToMany(orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Aircraft> aircrafts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrgRole getRole() {
        return role;
    }

    public void setRole(OrgRole role) {
        this.role = role;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }

    public List<Approval> getApprovedEquipments() {
        return approvedEquipments;
    }

    public void setApprovedEquipments(List<Approval> approvedEquipments) {
        this.approvedEquipments = approvedEquipments;
    }

    public List<Organisation> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<Organisation> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }
}
