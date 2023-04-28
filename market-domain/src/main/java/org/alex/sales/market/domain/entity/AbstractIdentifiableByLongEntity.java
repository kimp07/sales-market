package org.alex.sales.market.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIdentifiableByLongEntity<ID> {

    public abstract ID getId();

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<? extends AbstractIdentifiableByLongEntity> currentClass = getClass();
        if (o.getClass() != getClass()) return false;
        return currentClass.isInstance(o) && Objects.equals(currentClass.cast(o).getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
