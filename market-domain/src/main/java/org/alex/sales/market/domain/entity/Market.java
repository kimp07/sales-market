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

@Entity(name = Market.ENTITY_NAME)
@Table(name = Market.TABLE_NAME)
@Getter
@Setter
public class Market extends AbstractIdentifiableByLongEntity<Long> {

    public static final String ENTITY_NAME = "Market";
    public static final String TABLE_NAME = "t_markets";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    private Organization organization;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address")
    private String address;

}
