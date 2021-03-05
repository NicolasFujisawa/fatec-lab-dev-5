package fatec.labdev.projeto.pollingapp.option.controller.v1.request;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {
    private String title;

    private Poll poll;
}
