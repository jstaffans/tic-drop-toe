package fi.bitrite.ticakkatoe;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import com.yammer.dropwizard.jdbi.DBIFactory;
import fi.bitrite.ticakkatoe.resources.BoardResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

public class TicService extends Service<TicConfiguration> {
    public static void main(String[] args) throws Exception {
        new TicService().run(args);
    }

    @Override
    public void initialize(Bootstrap<TicConfiguration> bootstrap) {
        bootstrap.setName("tic-akka-toe");
    }

    @Override
    public void run(TicConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new BoardResource());

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "h2");

        FilterBuilder filterBuilder= environment.addFilter(CrossOriginFilter.class, "/*");
        filterBuilder.setInitParam(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM, String.valueOf(60*60*24));
    }
}
