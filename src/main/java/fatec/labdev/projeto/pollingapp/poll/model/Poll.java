package fatec.labdev.projeto.pollingapp.poll.model;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pollings")
@Getter
@Setter
@NoArgsConstructor
public class Poll {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToMany(mappedBy = "poll", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Option> options = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public void addOption(Option option) {
        this.options.add(option);
        option.setPoll(this);
    }

    public void addOptions(Set<Option> options) {
        for (Option option : options) {
            this.addOption(option);
        }
    }

    public static PollBuilder builder() {
        return new PollBuilder();
    }

    public Poll(String title, String description, boolean isEnabled, User user) {
        this.title = title;
        this.description = description;
        this.isEnabled = isEnabled;
        this.owner = user;
    }

    public static class PollBuilder {
        private String title;
        private String description;
        private boolean isEnabled;
        private User owner;

        private PollBuilder() {
        }

        public PollBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PollBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PollBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public PollBuilder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Poll build() {
            return new Poll(title, description, isEnabled, owner);
        }
    }
}
