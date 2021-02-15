package fatec.labdev.projeto.pollingapp

import org.springframework.boot.test.context.SpringBootTest

import spock.lang.Specification

@SpringBootTest
class PollingAppApplicationSpecs extends Specification {

    def 'load context'() {
        expect:
        true
    }
}
