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
    public ResponseEntity<Option> get(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.optionService.findById(id));
    }

    @JsonView({OptionView.CreationOption.class})
    @PostMapping("/create-many")
    public ResponseEntity<List<Option>> createMany(@RequestBody OptionRequest[] optionRequests) {
        List<Option> options = OptionConverter.convertManyFrom(optionRequests);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.optionService.saveAll(options));
    }

    @JsonView({OptionView.CreationOption.class})
    @PostMapping
    public ResponseEntity<Option> create(@RequestBody OptionRequest optionRequest) {
        Option option = OptionConverter.convertFrom(optionRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.optionService.save(option));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
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
