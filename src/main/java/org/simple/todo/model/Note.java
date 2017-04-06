package org.simple.todo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "note_message")
    private String noteMessage;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "creation_date")
    private Timestamp timestamp;

}
