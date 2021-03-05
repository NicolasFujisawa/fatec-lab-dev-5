package fatec.labdev.projeto.pollingapp.poll.controller.v1.converter;

import fatec.labdev.projeto.pollingapp.poll.controller.v1.response.SimplePollResponse;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;

public class SimplePollConverter {
    public static SimplePollResponse convertFrom(Poll poll) {
        return SimplePollResponse.builder()
                                 .id(poll.getId())
                                 .build();
    }
}
