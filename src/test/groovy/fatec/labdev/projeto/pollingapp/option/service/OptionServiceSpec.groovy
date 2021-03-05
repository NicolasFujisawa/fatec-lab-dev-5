package fatec.labdev.projeto.pollingapp.option.service

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import fatec.labdev.projeto.pollingapp.log.service.LogService
import fatec.labdev.projeto.pollingapp.option.model.Option
import fatec.labdev.projeto.pollingapp.poll.model.Poll
import fatec.labdev.projeto.pollingapp.poll.service.PollService
import fatec.labdev.projeto.pollingapp.user.enums.UserRole
import fatec.labdev.projeto.pollingapp.user.model.User
import fatec.labdev.projeto.pollingapp.user.service.UserService
import spock.lang.Specification

@SpringBootTest
@Transactional
class OptionServiceSpec extends Specification {
    @Autowired
    private OptionService optionService;

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService

    def 'a option to vote'() {
        given: 'a user'
        User user = User
                .builder()
                .username("kik12")
                .password("mysecret")
                .role(UserRole.ADMIN)
                .build()
        userService.save(user)

        and: 'a poll persisted'
        Poll poll = new Poll()
        poll.setTitle("Some options")
        user.addPoll(poll)
        pollService.save(poll)

        and: 'one option persisted'
        Option optionA = new Option()
        optionA.setTitle("A")
        poll.addOptions([optionA] as Set<Option>)
        optionService.saveAll([optionA])

        and: 'some user votes option A'
        optionService.vote(optionA, user)

        when: 'get option'
        def result = optionService.findById(optionA.getId())
        def logs = logService.findByEntity(Option.class.getName())

        then:
        result != null
        logs.size() == 1
    }

    def 'a option to remove vote'() {
        given: 'a user'
        User user = User
                .builder()
                .username("kik12")
                .password("mysecret")
                .role(UserRole.ADMIN)
                .build()
        userService.save(user)

        and: 'a poll persisted'
        Poll poll = new Poll()
        poll.setTitle("Some options")
        user.addPoll(poll)
        pollService.save(poll)

        and: 'one option persisted'
        Option optionA = new Option()
        optionA.setTitle("A")
        poll.addOptions([optionA] as Set<Option>)
        optionService.saveAll([optionA])

        and: 'some user votes option A'
        optionService.vote(optionA, user)

        and: 'the user remove the vote'
        optionService.removeVote(optionA, user)

        when: 'get option and log'
        def result = optionService.findById(optionA.getId())
        def logs = logService.findByEntity(Option.class.getName())

        then:
        result != null
        logs.size() == 2
    }
}
