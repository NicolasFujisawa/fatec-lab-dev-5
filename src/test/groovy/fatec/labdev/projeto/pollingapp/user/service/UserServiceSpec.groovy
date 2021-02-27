package fatec.labdev.projeto.pollingapp.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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
        userPersisted == user
    }

    def 'get user'() {
        given:
        Optional<User> userOptional

        when:
        userOptional = userService.findById(userId)

        then:
        userOptional.isPresent()
        userOptional.get().id == userId
    }

    def 'update user'() {
        given:
        User user = userService.findById(userId).get()
        user.setUsername(username)

        when:
        userService.save(user)

        then:
        userService.findById(userId).get().username == expectedUsername

        where:
        username || expectedUsername
        "reis"   || "reis"
        "abob"   || "abob"
        "oscar"  || "oscar"

    }

    def 'delete user'() {
        when:
        userService.deleteById(userId)

        then:
        userService.findById(userId) == Optional.empty()
    }

}
