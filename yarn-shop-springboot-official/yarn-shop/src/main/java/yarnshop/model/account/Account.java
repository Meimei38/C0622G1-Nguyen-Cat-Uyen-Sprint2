package yarnshop.model.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import yarnshop.model.customer.Customer;
import yarnshop.model.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private Integer isDelete;
    private Integer lockStatus;
    @JsonBackReference
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<AccountRole> accountRoles;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private Employee employee;
    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private Customer customer;

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Set<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }
}
