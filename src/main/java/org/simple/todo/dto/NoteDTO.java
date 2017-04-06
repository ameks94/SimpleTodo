package org.simple.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class NoteDTO {

    private Integer id;
    private Integer userId;
    private String noteMessage;
    private Boolean done;
    private Timestamp timestamp;

}
