package fatec.labdev.projeto.pollingapp.option.model;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Option {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    private List<User> votes;

    @ManyToOne
    private Poll poll;
}
