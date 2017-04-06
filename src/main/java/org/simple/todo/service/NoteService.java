package org.simple.todo.service;

import org.simple.todo.dto.NoteDTO;

import java.util.List;

public interface NoteService {

    List<NoteDTO> getAllStoredNotes();

    NoteDTO saveNote(NoteDTO note);

    NoteDTO updateNote(NoteDTO note);

    NoteDTO findNote(int id);

    void deleteNote(int id);

    List<NoteDTO> getNotesByUser(int id);
}
