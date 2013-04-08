package fi.bitrite.ticakkatoe.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.bitrite.ticakkatoe.core.Board;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(BindBoard.BoardBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})public @interface BindBoard {
    public static class BoardBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation)
        {
            return new Binder<BindBoard, Board>()
            {
                public void bind(SQLStatement q, BindBoard bind, Board arg)
                {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        q.bind("id", arg.getId());
                        q.bind("markers", "{\"markers\": " + mapper.writeValueAsString(arg.getMarkers()) + "}");
                    } catch (JsonProcessingException e) {
                        throw new IllegalArgumentException("Could not write JSON");
                    }
                }
            };
        }
    }
}
