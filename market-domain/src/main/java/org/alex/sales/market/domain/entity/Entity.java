package org.alex.sales.market.domain.entity;

import javax.annotation.Nonnull;

public interface Entity<ID> {

    ID getId();

    void setId(ID id);

}
