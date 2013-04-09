package fi.bitrite.ticdroptoe.core;

import org.junit.Test;

import java.util.Arrays;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void canSetMarker() {
        Board board = Board.createDefaultBoard(1);
        board.setMarker(1, 1, 'X');

        Character [] expected = {' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' '};
        assertTrue(Arrays.equals(expected, board.getMarkers()));
    }

    @Test
    public void canConstructFromJson() throws Exception {
        Board expected = new Board(1, new Character[] {'X', ' '});
        assertThat(fromJson(jsonFixture("fixtures/board.json"), Board.class), is(expected));
    }
}
