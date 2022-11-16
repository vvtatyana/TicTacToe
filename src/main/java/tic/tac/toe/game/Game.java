package tic.tac.toe.game;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tic.tac.toe.model.PlayingField;
import tic.tac.toe.model.Type;

import java.util.List;

@Component
public class Game {
    @Getter
    private final PlayingField playingField;

    @Getter private boolean win = false;
    @Getter private boolean end = false;

    @Autowired
    public Game(PlayingField playingField) {
        this.playingField = playingField;
    }

    public void newGame(){
        playingField.newGame();
    }

    public void setValue(int line, int column, char value) {
        if(playingField.setValue(line, column, value)) {
            win = checkWin(value);
            end = checkEnd();
            if (win) end = true;
        }
    }

    private boolean checkEnd() {
        return playingField.checkEnd();
    }

    public int fieldMatch(int line, int column, char value) {
        return playingField.getField().get(line).get(column).getType() == value ? 1 : 0;
    }

    private boolean checkWin(char value) {
        for (List<Type> line : playingField.getField()) {
            if (line.stream().filter(it -> it.getType() == value).toArray().length == 3)
                return true;
        }

        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                count += fieldMatch(j, i, value);
            }
            if (count == 3) return true;
        }

        int count = 0;
        for (int i = 0; i < 3; i++) {
            count += fieldMatch(i, i, value);

            if (count == 3) return true;
        }

        count = 0;
        for (int i = 2, j = 0; i >= 0 && j < 3; i--, j++) {
            count += fieldMatch(i, j, value);

            if (count == 3) return true;
        }

        return false;
    }

    public void nextMove() {
        int i, j;
        do{
            i = (int) (Math.random() * 3);
            j = (int) (Math.random() * 3);
        } while (playingField.getField().get(i).get(j).getType() != '-');
        playingField.setValue(i, j, 'o');
    }
}
