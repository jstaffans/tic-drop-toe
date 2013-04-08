package fi.bitrite.ticakkatoe.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.bitrite.ticakkatoe.core.Board;
import lombok.extern.slf4j.Slf4j;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class BoardMapper implements ResultSetMapper<Board> {

    @Override
    public Board map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, ArrayList<String>> markers = null;
        try {
            markers = mapper.readValue(resultSet.getString("markers"), Map.class);
        } catch (IOException e) {
            log.error("Could not parse json: " + resultSet.getString("markers"));
        }


        ArrayList<String> markerList = markers.get("markers");
        Character[] markerArray = new Character[markerList.size()];
        for (int j = 0; j < markerList.size(); j++) {
            markerArray[j] = markerList.get(j).charAt(0);
        }
        return new Board(resultSet.getInt("id"), markerArray);

    }

}
