package tic.tac.toe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayingField {
    @Getter @Setter
    private List<List<Type>> field;

    public PlayingField(){
        newGame();
    }

    public void newGame() {
        field = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Type> line = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                line.add(new Type('-', i, j));
            }
            field.add(line);
        }
    }

    public Boolean setValue(int line, int column, char value) {
        if (field.get(line).get(column).getType() != '-') return false;
        field.get(line).set(column, new Type(value, line, column));
        return true;
    }

    public boolean checkEnd() {
        for (List<Type> f : field) {
            for (Type type : f){
                if (type.getType() == '-') return false;
            }
        }
        return true;
    }
}
