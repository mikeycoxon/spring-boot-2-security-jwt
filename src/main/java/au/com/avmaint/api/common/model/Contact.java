package au.com.avmaint.api.common.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Michael Coxon on 17/4/18.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    private String phone;

    private String mobile;

    private String fax;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    private String email;

    public Contact() {
    }

    public static Contact of(String phone, String mobile, String email) {
        Contact contact = new Contact();
        contact.phone = phone;
        contact.mobile = mobile;
        contact.email = email;
        return contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
