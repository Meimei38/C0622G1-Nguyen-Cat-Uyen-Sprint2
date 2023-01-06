package yarnshop.model.employee;

import yarnshop.model.account.Account;

import javax.persistence.*;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String avatar;
    private String dateOfBirth;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
   private String idCard;
   private String phone;
   private String addressDetail;
   private String ward;
   private String district;
   private String city;
   private String country;

   @OneToOne
   @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;







}
