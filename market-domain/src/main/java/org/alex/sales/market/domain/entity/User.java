package org.alex.sales.market.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.sql.Timestamp;

@Entity(name = User.ENTITY_NAME)
@Table(name = User.TABLE_NAME)
@Getter
@Setter
public class User extends AbstractEntity<Long> {

    public static final String ENTITY_NAME = "User";
    public static final String TABLE_NAME = "t_users";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @BatchSize(size = EntityProperties.BATCH_SIZE)
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "u_enabled")
    private Boolean enabled;
    @Column(name = "u_non_locked")
    private Boolean nonLocked;
    @Column(name = "u_non_expired")
    private Boolean nonExpired;
    @Column(name = "u_credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "token")
    private String authProviderId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    private Organization organization;

}
