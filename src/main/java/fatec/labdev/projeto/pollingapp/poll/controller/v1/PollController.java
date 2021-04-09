package fatec.labdev.projeto.pollingapp.poll.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.converter.PollConverter;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.request.PollRequest;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.service.PollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @JsonView(PollView.FullPoll.class)
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pollService.findById(id));
    }

    @JsonView(PollView.FullPoll.class)
    @GetMapping("/{id}/most-voted")
    public ResponseEntity<List<Option>> getMostVotedOptions(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pollService.mostVotedOptions(id));
    }

    @JsonView(PollView.CreationPoll.class)
    @PostMapping("/create")
    public ResponseEntity<Poll> createPoll(@RequestBody PollRequest pollRequest) {
        Poll poll = PollConverter.convertFrom(pollRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pollService.save(poll));
    }

    @JsonView(PollView.ShortPoll.class)
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") UUID id) {
        this.pollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
