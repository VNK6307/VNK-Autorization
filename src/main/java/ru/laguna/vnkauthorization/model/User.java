package ru.laguna.vnkauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.laguna.vnkauthorization.service.Authorities;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    private String name;

    private String password;

    private List<Authorities> rights;
}
