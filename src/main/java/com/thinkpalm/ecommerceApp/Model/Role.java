package com.thinkpalm.ecommerceApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

@Column(name="roleName")
private String roleName;

}
