package fatec.labdev.projeto.pollingapp.poll.controller.v1.request;

import fatec.labdev.projeto.pollingapp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PollRequest {
    private String title;

    private String description;

    private boolean isEnabled;

    private User owner;

    public PollRequest(String title, String description, User owner) {
        this.setTitle(title);
        this.setDescription(description);
        this.setOwner(owner);
        this.setEnabled(true);
    }
}
