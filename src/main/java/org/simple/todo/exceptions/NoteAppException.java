package org.simple.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class NoteAppException extends RuntimeException {

    private HttpStatus httpStatus;

    public NoteAppException() {
        super();
    }

    public NoteAppException(String msg) {
        super(msg);
    }

    public NoteAppException(String msg, HttpStatus status) {
        super(msg);
        this.httpStatus = status;
    }
}
