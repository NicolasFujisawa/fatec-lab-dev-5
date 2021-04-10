package fatec.labdev.projeto.pollingapp.option.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;

import fatec.labdev.projeto.pollingapp.option.controller.v1.converter.OptionConverter;
import fatec.labdev.projeto.pollingapp.option.controller.v1.request.OptionRequest;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.service.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
}
