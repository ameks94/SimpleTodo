package org.simple.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoginDTO {
    private Integer loginedUserId;
    private String username;
    private Boolean enabled;
}
