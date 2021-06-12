package com.example.unsubscribe.models;

import com.example.unsubscribe.utils.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Roles name;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appuser_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns =  {@JoinColumn(name = "appuser_id")})
    private List<AppUser> appUsers;
}
