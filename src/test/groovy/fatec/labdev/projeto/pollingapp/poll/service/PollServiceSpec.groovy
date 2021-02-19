package fatec.labdev.projeto.pollingapp.poll.service

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback

import fatec.labdev.projeto.pollingapp.option.model.Option
import fatec.labdev.projeto.pollingapp.option.service.OptionService
import fatec.labdev.projeto.pollingapp.poll.model.Poll
import fatec.labdev.projeto.pollingapp.user.model.User
import fatec.labdev.projeto.pollingapp.user.service.UserService
import spock.lang.Specification

@SpringBootTest
@Rollback
@Transactional
class PollServiceSpec extends Specification {

    @Autowired
    private PollService pollService

    @Autowired
    private UserService userService

    @Autowired
    private OptionService optionService

    def 'simulating a poll flow'() {
        given: 'an user persisted'
        User user = new User()
        user.setUsername("kik12")
        user.setPassword("mysecret")
        userService.save(user)

        and: 'a poll persisted'
        Poll poll = new Poll()
        poll.setTitle("north/south")
        pollService.save(poll)
        user.addPoll(poll)

        and: 'two options persisted'
        Option optionNorth = new Option()
        optionNorth.setTitle("north")
        Option optionSouth = new Option()
        optionSouth.setTitle("south")
        poll.addOptions([optionSouth, optionNorth])
        optionService.saveAll([optionSouth, optionNorth])

        and: 'some user votes option north'
        user.vote(optionNorth)

        when: 'get all entitys by service'
        def userResult = userService.findById(user.id)
        def pollResult = pollService.findById(poll.id)
        def optionResult = optionService.findById(optionNorth.id)

        then:
        userResult.get().pollings.size() == 1
        userResult.get().votes.size() == 1
        pollResult.get().options.size() == 2
        pollResult.get().owner != null
        optionResult.get().poll != null
        optionResult.get().votes.size() == 1
    }

}
