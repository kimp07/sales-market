package org.alex.sales.market.domain.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity<ID> implements Entity<ID> {

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<? extends AbstractEntity> currentClass = getClass();
        if (o.getClass() != getClass()) return false;
        return currentClass.isInstance(o) && Objects.equals(currentClass.cast(o).getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
