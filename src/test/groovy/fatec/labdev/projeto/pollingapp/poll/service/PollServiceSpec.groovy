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

    def 'cria um cenario pra poll'() {
        given: 'um user persistido'
        User user = new User()
        user.setUsername("kik12")
        user.setPassword("mysecret")
        userService.save(user)

        and: 'uma poll persistida'
        Poll poll = new Poll()
        poll.setTitle("norte/sul")
        pollService.save(poll)
        user.addPoll(poll)

        and: '2 options tbm persistidas'
        Option optionNorte = new Option()
        optionNorte.setTitle("norte")
        Option optionSul = new Option()
        optionSul.setTitle("sul")
        poll.addOptions([optionSul, optionNorte])
        optionService.saveAll([optionSul, optionNorte])

        and: 'some user votes option norte'
        user.vote(optionNorte)

        when: 'recupera as entidades por id'
        def userResult = userService.findById(user.id)
        def pollResult = pollService.findById(poll.id)
        def optionResult = optionService.findById(optionNorte.id)

        then:
        userResult.get().pollings.size() == 1
        userResult.get().votes.size() == 1
        pollResult.get().options.size() == 2
        pollResult.get().owner != null
        optionResult.get().poll != null
        optionResult.get().votes.size() == 1
    }

}
