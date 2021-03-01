package fatec.labdev.projeto.pollingapp.option.controller.v1.response;

import java.util.Set;
import java.util.UUID;

import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionResponse {
    private UUID id;

    private String title;

    private Set<UserResponse> votes;
}
