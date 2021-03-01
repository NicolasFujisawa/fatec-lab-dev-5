package fatec.labdev.projeto.pollingapp.option.controller.v1.converter;

import java.util.LinkedList;
import java.util.List;

import fatec.labdev.projeto.pollingapp.option.controller.v1.response.OptionResponse;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.UserConverter;

public class OptionConverter {
    public static OptionResponse convertFrom(Option option) {
        return OptionResponse.builder()
                             .id(option.getId())
                             .title(option.getTitle())
                             .votes(UserConverter.convertManyFrom(option.getVotes()))
                             .build();
    }

    public static List<OptionResponse> convertManyFrom(List<Option> options) {
        List<OptionResponse> optionResponses = new LinkedList<>();
        for (Option option : options){
            optionResponses.add(OptionConverter.convertFrom(option));
        }
    
        return optionResponses;
    }
}
