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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pollings")
@Getter
@Setter
public class Poll {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "poll", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public void addOption(Option option) {
        this.options.add(option);
        option.setPoll(this);
    }

    public void addOptions(List<Option> options) {
        for (Option option : options) {
            this.addOption(option);
        }
    }
}
