package yarnshop.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import yarnshop.model.payment.OrderDetail;

import javax.persistence.*;

@Entity
public class ProductDetail {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String color;
    private Integer quantity;
    private Integer isDelete;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    @JsonBackReference
    @OneToOne(mappedBy = "productDetail")
    private OrderDetail orderDetail;

    public ProductDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
