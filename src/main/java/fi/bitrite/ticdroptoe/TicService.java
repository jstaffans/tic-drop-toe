package fi.bitrite.ticdroptoe;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import com.yammer.dropwizard.jdbi.DBIFactory;
import fi.bitrite.ticdroptoe.persistence.BoardDao;
import fi.bitrite.ticdroptoe.resources.TurnResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class TicService extends Service<TicConfiguration> {
    public static void main(String[] args) throws Exception {
        new TicService().run(args);
    }

    @Override
    public void initialize(Bootstrap<TicConfiguration> bootstrap) {
        bootstrap.setName("tic-drop-toe");
    }

    @Override
    public void run(TicConfiguration configuration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "h2");
        environment.addResource(new TurnResource(jdbi.onDemand(BoardDao.class)));
        createDatabase(jdbi);

        FilterBuilder filterBuilder= environment.addFilter(CrossOriginFilter.class, "/*");
        filterBuilder.setInitParam(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,PUT");
        filterBuilder.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, String.valueOf(60*60*24));
    }

    private void createDatabase(DBI jdbi) {
        Handle h = jdbi.open();
        BoardDao dao = h.attach(BoardDao.class);
        dao.createBoardTable();
        h.close();
    }
}
