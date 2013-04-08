package fi.bitrite.ticdroptoe.persistence;

import fi.bitrite.ticdroptoe.core.Board;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.skife.jdbi.v2.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class BoardMapperTest {

    @Test
    public void canCreateBoardFromJson() throws SQLException {
        ResultSet r = Mockito.mock(ResultSet.class);
        Mockito.when(r.getInt(Matchers.anyString())).thenReturn(1);
        Mockito.when(r.getString(Matchers.anyString())).thenReturn(
                "{\"markers\" : [\"X\", \" \", \"O\", \" \", \" \"]}"
        );

        BoardMapper mapper = new BoardMapper();
        Board board = mapper.map(1, r, Mockito.mock(StatementContext.class));

        assertEquals(1, board.getId());

        Character[] markers = board.getMarkers();
        assertEquals('X', markers[0].charValue());
        assertEquals(' ', markers[1].charValue());
        assertEquals('O', markers[2].charValue());
    }
}
