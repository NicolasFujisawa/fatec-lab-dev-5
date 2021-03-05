package fatec.labdev.projeto.pollingapp.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;

import fatec.labdev.projeto.pollingapp.user.enums.UserRole
import fatec.labdev.projeto.pollingapp.user.model.User
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest
@Stepwise
class UserServiceSpec extends Specification {

    @Autowired
    private UserService userService

    @Shared
    def userId = UUID.randomUUID()

    def 'create a user'() {
        given:
        User user = new User()
        user.setUsername("polo")
        user.setPassword("mysecret")
        user.setRole(UserRole.MEMBER)

        when:
        def userPersisted = userService.save(user)
        userId = user.id

        then:
        user.id != null
        user.role == UserRole.MEMBER
        userPersisted == user
    }

    def 'get user'() {
        given:
        User user

        when:
        user = userService.findById(userId)

        then:
        user.id == userId
    }

    def 'update user'() {
        given:
        User user = userService.findById(userId)
        user.setUsername(username)

        when:
        userService.save(user)

        then:
        userService.findById(userId).username == expectedUsername

        where:
        username || expectedUsername
        "reis"   || "reis"
        "abob"   || "abob"
        "oscar"  || "oscar"

    }

    def 'delete user'() {
        when:
        userService.deleteById(userId)
        userService.findById(userId)

        then:
        thrown EntityNotFoundException
    }

}
