package fatec.labdev.projeto.pollingapp.poll.controller.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.labdev.projeto.pollingapp.option.controller.v1.converter.OptionConverter;
import fatec.labdev.projeto.pollingapp.option.controller.v1.response.OptionResponse;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.converter.PollConverter;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.request.PollRequest;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.response.PollResponse;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.service.PollService;

@RestController
@RequestMapping("v1/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping("/{id}")
    public ResponseEntity<PollResponse> getPoll(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PollConverter.convertFrom(this.pollService.findById(id)));
    }

    @GetMapping("/{id}/most-voted")
    public ResponseEntity<List<OptionResponse>> getMostVotedOptions(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OptionConverter.convertManyFrom(this.pollService.mostVotedOptions(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<PollResponse> createPoll(@RequestBody PollRequest pollRequest) {
        Poll poll = PollConverter.convertFrom(pollRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PollConverter.convertFrom(this.pollService.save(poll)));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") UUID id) {
        this.pollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
