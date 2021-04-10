package fatec.labdev.projeto.pollingapp.user.model;

import com.fasterxml.jackson.annotation.JsonView;

import fatec.labdev.projeto.pollingapp.option.controller.v1.OptionView;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.controller.v1.PollView;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.user.controller.v1.UserView;
import fatec.labdev.projeto.pollingapp.user.enums.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @JsonView({UserView.FullUser.class, UserView.CreationUser.class})
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonView({UserView.ShortUser.class, PollView.FullPoll.class, OptionView.FullOption.class})
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonView({UserView.ShortUser.class})
    @Column(name = "role")
    private UserRole role;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Poll> pollings = new HashSet<>();

    @JsonView({UserView.FullUser.class})
    @ManyToMany
    @JoinTable(name = "user_votes",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id"))
    private Set<Option> votes = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public void addPoll(Poll poll) {
        this.pollings.add(poll);
        poll.setOwner(this);
    }

    public void vote(Option option) {
        this.votes.add(option);
        option.getVotes().add(this);
    }

    public void removeVote(Option option) {
        votes.remove(option);
        option.getVotes().remove(this);
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private UserRole role;

        private UserBuilder() {
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(username, password, role);
        }
    }
}
