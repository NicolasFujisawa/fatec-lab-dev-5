package fatec.labdev.projeto.pollingapp.poll.controller.v1;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.converter.PollConverter;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.request.PollRequest;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.service.PollService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

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
    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody PollRequest pollRequest) {
        Poll poll = PollConverter.convertFrom(pollRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pollService.save(poll));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") UUID id) {
        this.pollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @JsonView(PollView.CreationPoll.class)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePoll(@PathVariable("id") UUID id,
            @RequestBody Poll poll) {
        this.pollService.save(poll);
        return ResponseEntity.noContent().build();
    }

    @JsonView(PollView.FullPoll.class)
    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = this.pollService.findAll();
        return ResponseEntity.ok(polls);
    }
}
