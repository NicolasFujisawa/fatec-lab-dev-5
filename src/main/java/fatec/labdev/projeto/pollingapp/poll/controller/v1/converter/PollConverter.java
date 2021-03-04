package fatec.labdev.projeto.pollingapp.poll.controller.v1.converter;

import fatec.labdev.projeto.pollingapp.poll.controller.v1.request.PollRequest;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.response.PollResponse;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.SimpleUserConverter;

public class PollConverter {
    public static PollResponse convertFrom(Poll poll) {
        return PollResponse.builder()
                           .id(poll.getId())
                           .title(poll.getTitle())
                           .description(poll.getDescription())
                           .isEnabled(poll.isEnabled())
                           .owner(SimpleUserConverter.convertFrom(poll.getOwner()))
                           .build();
    }

    public static Poll convertFrom(PollRequest pollRequest) {
        return Poll.builder()
                   .description(pollRequest.getDescription())
                   .title(pollRequest.getTitle())
                   .isEnabled(pollRequest.isEnabled())
                   .owner(pollRequest.getOwner())
                   .build();
    }
}
