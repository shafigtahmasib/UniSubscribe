package com.example.unsubscribe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity(name = "appusers")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @JsonManagedReference
    @OneToMany(mappedBy = "appUser")
    private List<Subscription> subscriptions;
    @JsonManagedReference
    @ManyToMany(mappedBy = "appUsers",fetch = FetchType.EAGER)
    private List<Role> roles;
}