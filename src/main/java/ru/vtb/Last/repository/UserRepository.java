package ru.vtb.Last.repository;

import org.springframework.stereotype.Repository;
import ru.vtb.Last.entity.Role;
import ru.vtb.Last.entity.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User("user", "1", "Иван", "Иванов", Role.RO_USER));
        users.add(new User("admin", "2", "Петр", "Петров", Role.RO_ADMIN));
    }

    public User getByLogin(String login) {
        return this.users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAll() {
        return this.users;
    }
}
