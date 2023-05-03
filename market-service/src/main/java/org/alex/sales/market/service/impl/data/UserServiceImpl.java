package org.alex.sales.market.service.impl.data;

import lombok.RequiredArgsConstructor;
import org.alex.sales.market.service.data.UserService;
import org.alex.sales.market.data.UserRepository;
import org.alex.sales.market.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userDao;

    @Override
    public Optional<User> findById(@Nonnull Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<User> findByLogin(@Nonnull String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public Optional<User> findByEmail(@Nonnull String email) {
        return userDao.findByEmail(email);
    }

    @Nonnull
    @Override
    public Page<User> findAll(@Nonnull Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Nonnull
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(@Nonnull User user) {
        userDao.save(user);
    }

    @Override
    public void saveAll(@Nonnull Iterable<User> users) {
        userDao.saveAll(users);
    }

    @Override
    public void deleteById(@Nonnull Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void deleteAll(@Nonnull List<Long> userIds) {
        userDao.deleteAll(userIds);
    }

}
