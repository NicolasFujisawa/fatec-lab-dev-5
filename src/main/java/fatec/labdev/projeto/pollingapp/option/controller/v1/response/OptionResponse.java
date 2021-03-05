package fatec.labdev.projeto.pollingapp.option.controller.v1.response;

import java.util.Set;
import java.util.UUID;

import fatec.labdev.projeto.pollingapp.poll.controller.v1.response.SimplePollResponse;
import fatec.labdev.projeto.pollingapp.user.controller.v1.response.SimpleUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
    private UUID id;

    private String title;

    private Set<SimpleUserResponse> votes;

    private SimplePollResponse poll;
}
