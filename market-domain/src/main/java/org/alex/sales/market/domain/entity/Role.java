package org.alex.sales.market.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = Role.ENTITY_NAME)
@Table(name = Role.TABLE_NAME)
@Getter
@Setter
public class Role extends AbstractEntity<Long> {

    public static final String ENTITY_NAME = "Role";
    public static final String TABLE_NAME = "t_roles";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_disabled")
    private Boolean roleDisabled;
    @Column(name = "is_root_role")
    private Boolean isRootRole;
    @Column(name = "description")
    private String description;

}
