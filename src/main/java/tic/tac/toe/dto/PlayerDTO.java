package tic.tac.toe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PlayerDTO {
    private long id;
    private int sumGames;
    private int sumWin;

    public PlayerDTO() {
    }

    public PlayerDTO(long id, int sumGames, int sumWin) {
        this.id = id;
        this.sumGames = sumGames;
        this.sumWin = sumWin;
    }
}
