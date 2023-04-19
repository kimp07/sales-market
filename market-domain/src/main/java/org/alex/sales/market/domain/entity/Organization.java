package org.alex.sales.market.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity(name = Organization.ENTITY_NAME)
@Table(name = Organization.TABLE_NAME)
@Getter
@Setter
public class Organization extends AbstractEntity<Long> {

    public static final String ENTITY_NAME = "Organization";
    public static final String TABLE_NAME = "t_organizations";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "legal_address")
    private String legalAddress;
    @Column(name = "mail_address")
    private String mailAddress;
    @Column(name = "vat", nullable = false)
    private String vat;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "chief_accountant")
    private String chiefAccountant;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "organization")
    @BatchSize(size = EntityProperties.BATCH_SIZE)
    private List<Market> markets;

}
