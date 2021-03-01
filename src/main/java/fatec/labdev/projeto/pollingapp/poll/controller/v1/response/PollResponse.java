package fatec.labdev.projeto.pollingapp.poll.controller.v1.response;

import java.util.UUID;

import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PollResponse {
    private UUID id;

    private String title;

    private String description;

    private boolean isEnabled;

    private UserResponse owner;
}
