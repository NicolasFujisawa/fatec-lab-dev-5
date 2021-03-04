package fatec.labdev.projeto.pollingapp.log.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "logs")
@Builder
public class Log {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
