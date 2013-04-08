package fi.bitrite.ticdroptoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TicConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    @Getter
    private DatabaseConfiguration database = new DatabaseConfiguration();

}
