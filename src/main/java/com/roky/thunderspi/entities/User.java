package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String fname;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lname ;

    @NotBlank(message = "Email is required")
    @Column(name = "email")
    private String email ;

    @NotBlank(message = "Password is required")
    @Column(name = "password")
    private String password ;


    @NotBlank(message = "Phone number is required")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "adress is required")
    @Column(name = "adress")
    private String adress ;

    @Enumerated(EnumType.STRING)
    @Lazy
    @Column(name = "role")
    private Role role;

    @Transient
    private String token;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled =true;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public void add(Order order) {

        if (order != null) {

            if (orders == null) {
                orders = new HashSet<>();
            }

            orders.add(order);
            order.setCustomer(this);
        }
    }

    @ManyToMany(mappedBy = "whoWhishesThisProduct")
    public Set<Product> productsWished;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    Set<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="utilis")
    @JsonIgnore
    private Set<PostLike> Likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="utilis")
    @JsonIgnore
    private Set<PostDislike> dislikes;




}
