package org.simple.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Timestamp creationDate;
    private List<NoteDTO> notes;

}
