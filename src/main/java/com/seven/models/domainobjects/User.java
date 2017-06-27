package com.seven.models.domainobjects;

import com.seven.models.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private final String firstName;

    private final String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private final String email;

    private final String phone;

    @Column(name = "password", nullable = false)
    private final String password;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_role_id")
    private Role role;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_company_id")
    private Company company;

    public boolean isOwner() {
        return getRole().getRoleName().equals(UserRole.COMPANY_OWNER);
    }

    public boolean isAdmin() {
        return getRole().getRoleName().equals(UserRole.ADMIN);
    }
}
