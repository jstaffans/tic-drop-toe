package fi.bitrite.ticdroptoe.resources;

import fi.bitrite.ticdroptoe.core.Board;
import fi.bitrite.ticdroptoe.core.Turn;
import fi.bitrite.ticdroptoe.persistence.BoardDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TurnResourceTest {
    private final BoardDao dao = mock(BoardDao.class);
    private final Board board = Board.createDefaultBoard(1);

    @Test
    public void shouldCreateNewBoardIfNoBoardExists() {
        TurnResource resource = new TurnResource(dao);
        resource.getTurn();
        verify(dao).insert(eq(board));
    }

    @Test
    public void shouldUpdateBoardOnPost() {
        TurnResource resource = new TurnResource(dao);
        Turn turn = resource.getTurn();

        turn.getBoard().setMarker(1, 1, 'X');
        resource.postNextTurn(turn);

        board.setMarker(1, 1, 'X');
        verify(dao).update(eq(board));

        assertEquals(2, resource.getTurnCounter().get());
    }
}
