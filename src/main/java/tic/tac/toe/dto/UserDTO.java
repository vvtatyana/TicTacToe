package tic.tac.toe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserDTO {
    private String login;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
