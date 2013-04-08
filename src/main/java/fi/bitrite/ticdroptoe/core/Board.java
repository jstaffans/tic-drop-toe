package fi.bitrite.ticdroptoe.core;

import lombok.Getter;

import java.util.Arrays;

public class Board {

    public static final int SIZE = 3;

    public static Board createDefaultBoard(int id) {
        Character [] markers = new Character[SIZE * SIZE];
        Arrays.fill(markers, ' ');
        return new Board(id, markers);
    }

    @Getter
    private final int id;

    @Getter
    private final Character[] markers;

    public Board(int id, Character[] markers) {
        this.markers = markers;
        this.id = id;
    }

    public void setMarker(int x, int y, char marker) {
        markers[y*SIZE + x] = marker;
    }
}
