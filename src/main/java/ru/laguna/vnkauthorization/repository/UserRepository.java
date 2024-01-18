package ru.laguna.vnkauthorization.repository;

import org.springframework.stereotype.Repository;
import ru.laguna.vnkauthorization.model.User;
import ru.laguna.vnkauthorization.service.Authorities;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    {
        users.add(new User("root", "root", List.of(new Authorities[]{Authorities.DELETE, Authorities.READ, Authorities.WRITE})));
        users.add(new User("admin", "admin", List.of(new Authorities[]{Authorities.READ, Authorities.WRITE})));
        users.add(new User("Vova", "tokar", List.of(new Authorities[]{Authorities.READ})));
        users.add(new User("Dima", "engineer", List.of(new Authorities[]{Authorities.READ})));
        users.add(new User("Petya", "slesar", List.of(new Authorities[]{Authorities.READ})));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        for (User tUser : users) {
            if (tUser.getName().equals(user) && tUser.getPassword().equals(password)) {
                return tUser.getRights();
            }
        }
        return null;
    }
}
