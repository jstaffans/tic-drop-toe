package fi.bitrite.ticdroptoe.core;

import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TurnTest {

    @Test
    public void canConstructFromJson() throws Exception {
        Board board = new Board(1, new Character[] {'X', ' '});
        Turn expected = new Turn(1L, board);
        assertThat(fromJson(jsonFixture("fixtures/turn.json"), Turn.class), is(expected));
    }
}
