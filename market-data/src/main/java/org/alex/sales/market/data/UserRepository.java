package org.alex.sales.market.data;

import org.alex.sales.market.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Nonnull
    Page<User> findAll(@Nonnull Pageable pageable);

    @Nonnull
    List<User> findAll();

    @Query("select u from User u where u.login = :login")
    Optional<User> findByLogin(@Nonnull @Param(value = "login") String login);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Nonnull @Param(value = "email") String email);

    @Query("delete from User u where u.id in (:userIds)")
    void deleteAll(@Nonnull @Param(value = "userIds") List<Long> userIds);

}
