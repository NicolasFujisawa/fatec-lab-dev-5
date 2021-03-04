package fatec.labdev.projeto.pollingapp.option.controller.v1;

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
import fatec.labdev.projeto.pollingapp.option.controller.v1.request.OptionRequest;
import fatec.labdev.projeto.pollingapp.option.controller.v1.response.OptionResponse;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.service.OptionService;

@RestController
@RequestMapping("/option")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/{id}")
    public ResponseEntity<OptionResponse> getPoll(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OptionConverter.convertFrom(this.optionService.findById(id)));
    }

    @PostMapping("/new")
    public ResponseEntity<OptionResponse> createPoll(@RequestBody OptionRequest optionRequest) {
        Option option = OptionConverter.convertFrom(optionRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OptionConverter.convertFrom(this.optionService.save(option)));
    }

    @PostMapping("/createMany")
    public ResponseEntity<List<OptionResponse>> createPoll(@RequestBody OptionRequest[] optionRequests) {
        List<Option> options = OptionConverter.convertManyFrom(optionRequests);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OptionConverter.convertManyFrom(this.optionService.saveAll(options)));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") UUID id) {
        this.optionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
