package org.alex.sales.market.service.data;

import org.alex.sales.market.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(@Nonnull Long userId);

    Optional<User> findByLogin(@Nonnull String login);

    Optional<User> findByEmail(@Nonnull String email);

    @Nonnull
    Page<User> findAll(@Nonnull Pageable pageable);

    @Nonnull
    List<User> findAll();

    void save(@Nonnull User user);

    void saveAll(@Nonnull Iterable<User> users);

    void deleteById(@Nonnull Long userId);

    void deleteAll(@Nonnull List<Long> userIds);

}
