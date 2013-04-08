package fi.bitrite.ticdroptoe.persistence;

import fi.bitrite.ticdroptoe.core.Board;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface BoardDao {

    @SqlUpdate("create table board (id int primary key, markers varchar(255))")
    void createBoardTable();

    @SqlUpdate("insert into board (id, markers) values (:id, :markers)")
    void insert(@BindBoard Board b);

    @SqlQuery("select id, markers from board where id = :id")
    @Mapper(BoardMapper.class)
    Board findById(@Bind("id") int id);

    @SqlUpdate("update board set markers = :markers where id = :id")
    void update(@BindBoard Board b);
}
