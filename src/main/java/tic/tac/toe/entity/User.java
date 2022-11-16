package tic.tac.toe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable=false)
    private Set<Role> roles;

    public User() {
    }

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
