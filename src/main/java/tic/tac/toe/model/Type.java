package tic.tac.toe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Type {
    private char type;
    private int line;
    private int column;

    public Type(char type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }
}
