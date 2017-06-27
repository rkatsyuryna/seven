package com.seven.models.domainobjects;

import com.seven.models.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final UserRole roleName;
}
