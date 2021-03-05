package fatec.labdev.projeto.pollingapp.option.model;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "votes")
    private Set<User> votes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    public Option(String title, Poll poll) {
        this.setTitle(title);
        this.setPoll(poll);
    }

    public static OptionBuilder builder() {
        return new OptionBuilder();
    }

    public static class OptionBuilder {
        private String title;
        private Poll poll;

        private OptionBuilder() {
        }

        public OptionBuilder title(String title) {
            this.title = title;
            return this;
        }

        public OptionBuilder poll(Poll poll) {
            this.poll = poll;
            return this;
        }

        public Option build() {
            return new Option(title, poll);
        }
    }
}
