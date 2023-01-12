package yarnshop.model.shipping;

import com.fasterxml.jackson.annotation.JsonBackReference;
import yarnshop.model.payment.Payment;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ShippingFee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double fee;
    @OneToMany (mappedBy = "shippingFee")
    private Set<Payment> payments;

    public ShippingFee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
