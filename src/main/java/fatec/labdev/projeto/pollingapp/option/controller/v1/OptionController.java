package fatec.labdev.projeto.pollingapp.option.controller.v1;

import fatec.labdev.projeto.pollingapp.option.controller.v1.converter.OptionConverter;
import fatec.labdev.projeto.pollingapp.option.controller.v1.request.OptionRequest;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.service.OptionService;

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
@RequestMapping("v1/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @JsonView({OptionView.FullOption.class})
    @GetMapping("/{id}")
    public ResponseEntity<Option> getPoll(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.optionService.findById(id));
    }

    @JsonView({OptionView.CreationOption.class})
    @PostMapping("/create")
    public ResponseEntity<Option> createPoll(@RequestBody OptionRequest optionRequest) {
        Option option = OptionConverter.convertFrom(optionRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.optionService.save(option));
    }

    @JsonView({OptionView.CreationOption.class})
    @PostMapping("/createMany")
    public ResponseEntity<List<Option>> createPoll(@RequestBody OptionRequest[] optionRequests) {
        List<Option> options = OptionConverter.convertManyFrom(optionRequests);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.optionService.saveAll(options));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") UUID id) {
        this.optionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @JsonView({OptionView.FullOption.class})
    @PutMapping("/{id}")
    public ResponseEntity<Option> update(
            @RequestBody OptionRequest optionRequest,
            @PathVariable("id") UUID id) {
        Option option = OptionConverter.convertFrom(optionRequest);
        option.setId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.optionService.save(option));
    }
}
