package fi.bitrite.ticakkatoe.core;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void canSetMarker() {
        Board board = Board.createDefaultBoard(1);
        board.setMarker(1, 1, 'X');

        Character [] expected = {' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' '};
        assertTrue(Arrays.equals(expected, board.getMarkers()));
    }
}
