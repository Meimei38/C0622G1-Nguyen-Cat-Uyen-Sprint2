package yarnshop.model.shipping;

import com.fasterxml.jackson.annotation.JsonBackReference;
import yarnshop.model.customer.Customer;
import yarnshop.model.payment.Payment;

import javax.persistence.*;

@Entity
public class ShippingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shippingAddress;
    private String shippingReceiver;
    private String receiverPhone;
    @OneToOne(mappedBy = "shippingInformation")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    public ShippingInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingReceiver() {
        return shippingReceiver;
    }

    public void setShippingReceiver(String shippingReceiver) {
        this.shippingReceiver = shippingReceiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
