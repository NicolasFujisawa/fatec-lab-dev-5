package fatec.labdev.projeto.pollingapp.poll.service


import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import fatec.labdev.projeto.pollingapp.option.model.Option
import fatec.labdev.projeto.pollingapp.option.service.OptionService
import fatec.labdev.projeto.pollingapp.poll.model.Poll
import fatec.labdev.projeto.pollingapp.user.enums.UserRole
import fatec.labdev.projeto.pollingapp.user.model.User
import fatec.labdev.projeto.pollingapp.user.service.UserService
import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import spock.lang.Specification

@SpringBootTest
@Transactional
class PollServiceSpec extends Specification {

    @Autowired
    private PollService pollService

    @Autowired
    private UserService userService

    @Autowired
    private OptionService optionService

    def 'poll with two options and one vote'() {
        given: 'an user persisted'
        User user = User
                .builder()
                .username("kik12")
                .password("mysecret")
                .role(UserRole.ADMIN)
                .build()
        userService.save(user)

        and: 'a poll persisted'
        Poll poll = new Poll()
        poll.setTitle("north/south")
        user.addPoll(poll)
        pollService.save(poll)

        and: 'two options persisted'
        Option optionNorth = new Option()
        optionNorth.setTitle("north")
        Option optionSouth = new Option()
        optionSouth.setTitle("south")
        poll.addOptions([optionSouth, optionNorth] as Set<Option>)
        optionService.saveAll([optionSouth, optionNorth])

        and: 'some user votes option north'
        user.vote(optionNorth)

        when: 'get all entities by service'
        def userResult = userService.findById(user.id)
        def pollResult = pollService.findById(poll.id)
        def optionResult = optionService.findById(optionNorth.id)
        def mostVotedOptions = pollService.mostVotedOptions(poll.id)

        then:
        userResult.pollings.size() == 1
        userResult.votes.size() == 1
        pollResult.options.size() == 2
        pollResult.owner != null
        optionResult.get().poll != null
        optionResult.get().votes.size() == 1
        mostVotedOptions.size() == 1
    }

    def 'poll with three options and two votes'() {
        given: 'two users persisted'
        User user1 = User
                .builder()
                .username("kek13")
                .password("123")
                .role(UserRole.ADMIN)
                .build()

        User user2 = User
                .builder()
                .username("rog11")
                .password("132")
                .role(UserRole.ADMIN)
                .build()

        userService.save(user1)
        userService.save(user2)

        and: 'a poll persisted'
        Poll poll = new Poll()
        poll.setTitle("quem descobriu america?")
        user1.addPoll(poll)
        pollService.save(poll)

        and: 'three options persisted'
        Option optionA = new Option()
        optionA.setTitle("pedro")
        Option optionB = new Option()
        optionB.setTitle("joão")
        Option optionC = new Option()
        optionC.setTitle("cabral")
        poll.addOptions([optionA, optionB, optionC] as Set<Option>)
        optionService.saveAll([optionA, optionB, optionC])

        and: 'one vote in optionA and one vote in optionB'
        user1.vote(optionA)
        user2.vote(optionB)

        when: 'get all entities by service'
        def userResult = userService.findById(user1.id)
        def pollResult = pollService.findById(poll.id)
        def optionResult = optionService.findById(optionA.id)
        def mostVotedOptions = pollService.mostVotedOptions(poll.id)

        then:
        userResult.pollings.size() == 1
        userResult.votes.size() == 1
        pollResult.options.size() == 3
        pollResult.owner != null
        optionResult.get().poll != null
        optionResult.get().votes.size() == 1
        mostVotedOptions.size() == 2
    }

    def 'get non-existant poll'() {
        given: 'a fake uuid'
        def UUID fakeUUID = UUID.randomUUID()

        when: 'get poll'
        pollService.findById(fakeUUID)

        then:
        thrown EntityNotFoundException
    }
}
