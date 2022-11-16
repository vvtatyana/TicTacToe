package tic.tac.toe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {
    @Id
    @Column(name = "id_user")
    private long id;

    @Column(name = "sum_games")
    private int sumGames;

    @Column(name = "sum_win")
    private int sumWin;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Player() {
    }

    public Player(long id, int sumGames, int sumWin) {
        this.id = id;
        this.sumGames = sumGames;
        this.sumWin = sumWin;
    }


    public Player(int sumGames, int sumWin) {
        this.sumGames = sumGames;
        this.sumWin = sumWin;
    }
}
