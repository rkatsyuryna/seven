package com.seven.models.domainobjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Company {

     @Id
     @GeneratedValue(strategy= GenerationType.AUTO)
     @Column(name = "id", nullable = false, updatable = false)
     private Long id;

     private final String name;

     @Email
     private final String email;

     private final String address;
}
