package fi.bitrite.ticakkatoe.resources;

import com.yammer.metrics.annotation.Timed;
import fi.bitrite.ticakkatoe.core.Board;
import fi.bitrite.ticakkatoe.core.Turn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/board")
@Produces(MediaType.APPLICATION_JSON)
public class BoardResource {
    private final AtomicLong turnCounter;
    private final Board board;

    public BoardResource() {
        turnCounter = new AtomicLong();
        board = Board.createDefaultBoard(1);
    }

    @GET
    @Timed
    public Turn getBoard() {
        return new Turn(turnCounter.incrementAndGet(), board);
    }
}
