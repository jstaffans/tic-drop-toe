package fi.bitrite.ticdroptoe.resources;

import com.yammer.metrics.annotation.Timed;
import fi.bitrite.ticdroptoe.core.Board;
import fi.bitrite.ticdroptoe.core.Turn;
import fi.bitrite.ticdroptoe.persistence.BoardDao;
import lombok.Getter;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/turn")
@Produces(MediaType.APPLICATION_JSON)
public class TurnResource {
    private final BoardDao dao;

    @Getter
    private final AtomicLong turnCounter;

    public TurnResource(BoardDao dao) {
        this.dao = dao;
        turnCounter = new AtomicLong(1);
    }

    @GET
    @Timed
    public Turn getTurn() {
        Board board = dao.findLatest();
        if (board == null) {
            board = Board.createDefaultBoard(1);
            dao.insert(board);
        }
        return new Turn(turnCounter.get(), board);
    }

    @PUT
    @Timed
    public Turn postNextTurn(Turn turn) {
        dao.update(turn.getBoard());
        return new Turn(turnCounter.incrementAndGet(), turn.getBoard());
    }
}
