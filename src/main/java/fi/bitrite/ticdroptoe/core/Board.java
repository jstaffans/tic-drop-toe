package fi.bitrite.ticdroptoe.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Board {

    public static final int SIZE = 3;

    public static Board createDefaultBoard(int id) {
        Character [] markers = new Character[SIZE * SIZE];
        Arrays.fill(markers, ' ');
        return new Board(id, markers);
    }

    private int id;
    private Character[] markers;

    public void setMarker(int x, int y, char marker) {
        markers[y*SIZE + x] = marker;
    }

}
