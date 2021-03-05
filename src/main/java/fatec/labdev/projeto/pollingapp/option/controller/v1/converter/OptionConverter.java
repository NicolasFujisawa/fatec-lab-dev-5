package fatec.labdev.projeto.pollingapp.option.controller.v1.converter;

import java.util.ArrayList;
import java.util.List;

import fatec.labdev.projeto.pollingapp.option.controller.v1.request.OptionRequest;
import fatec.labdev.projeto.pollingapp.option.controller.v1.response.OptionResponse;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.converter.SimplePollConverter;
import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.SimpleUserConverter;

public class OptionConverter {
    public static OptionResponse convertFrom(Option option) {
        return OptionResponse.builder()
                             .id(option.getId())
                             .title(option.getTitle())
                             .votes(SimpleUserConverter.convertManyFrom(option.getVotes()))
                             .poll(SimplePollConverter.convertFrom(option.getPoll()))
                             .build();
    }

    public static Option convertFrom(OptionRequest request) {
        return Option.builder()
                     .title(request.getTitle())
                     .poll(request.getPoll())
                     .build();
    }

    public static List<OptionResponse> convertManyFrom(List<Option> options) {
        List<OptionResponse> optionResponses = new ArrayList<OptionResponse>();
        for (Option option : options){
            optionResponses.add(OptionConverter.convertFrom(option));
        }
    
        return optionResponses;
    }

    public static List<Option> convertManyFrom(OptionRequest[] requests) {
        List<Option> options = new ArrayList<Option>();
        for (OptionRequest request : requests){
            options.add(OptionConverter.convertFrom(request));
        }
    
        return options;
    }
}
