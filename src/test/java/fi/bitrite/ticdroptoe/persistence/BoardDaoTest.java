package fi.bitrite.ticdroptoe.persistence;

import fi.bitrite.ticdroptoe.core.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BoardDaoTest {

    private BoardDao dao;
    private Handle h;

    @Before
    public void createTable() {
        DBI dbi = new DBI("jdbc:h2:mem:test");
        h = dbi.open();
        dao = h.attach(BoardDao.class);
        dao.createBoardTable();
    }

    @After
    public void destroyTable() {
        h.close();
    }

    @Test
    public void nullIsReturnedWhenNoBoardsExist() {
        Board board = dao.findLatest();
        assertNull(board);
    }

    @Test
    public void canGetLatestBoard() {
        dao.insert(Board.createDefaultBoard(1));
        dao.insert(Board.createDefaultBoard(2));
        Board board = dao.findLatest();
        assertEquals(2, board.getId());
    }

    @Test
    public void canCreateNewBoard() {
        dao.insert(Board.createDefaultBoard(1));
        Board board = dao.findById(1);
        assertEquals(9, board.getMarkers().length);
        for (char c : board.getMarkers()) {
            assertEquals(' ', c);
        }
    }

    @Test
    public void canUpdateBoard() {
        Board board = Board.createDefaultBoard(1);
        dao.insert(board);

        board.setMarker(1, 1, 'X');
        dao.update(board);

        board = dao.findById(1);
        Character [] expected = {' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' '};
        assertTrue(Arrays.equals(expected, board.getMarkers()));
    }


}
