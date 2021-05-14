package fatec.labdev.projeto.pollingapp.option.controller.v1.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OptionChangeRequest implements Serializable {

    private UUID lastOptionId;

    private UUID newOptionVoteId;

    private UUID userId;
}
