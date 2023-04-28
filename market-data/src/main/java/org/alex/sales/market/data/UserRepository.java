package org.alex.sales.market.data;

import org.alex.sales.market.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Nonnull
    Page<User> findAll(@Nonnull Pageable pageable);

    @Nonnull
    List<User> findAll();

}
