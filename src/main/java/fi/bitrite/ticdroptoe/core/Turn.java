package fi.bitrite.ticdroptoe.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turn {

    private Long turn;
    private Board board;

}
