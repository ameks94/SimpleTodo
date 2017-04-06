package org.simple.todo.service.impl;

import lombok.extern.log4j.Log4j;
import org.dozer.Mapper;
import org.simple.todo.dto.NoteDTO;
import org.simple.todo.exceptions.NoteAppException;
import org.simple.todo.model.Note;
import org.simple.todo.model.User;
import org.simple.todo.repository.NoteRepository;
import org.simple.todo.repository.UserRepository;
import org.simple.todo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public List<NoteDTO> getAllStoredNotes() {
        List<NoteDTO> notes = new ArrayList<>();
        noteRepository.findAll().forEach(note -> notes.add(mapNoteToDto(note)));
        return notes;
    }

    @Override
    public NoteDTO saveNote(NoteDTO note) {
        if (note.getId() != null) {
            throw new NoteAppException("Invalid input data: should not specify id", HttpStatus.BAD_REQUEST);
        }
        User userNote = userRepository.findOne(note.getUserId());
        if (userNote == null) {
            throw new NoteAppException("User not found for saved note", HttpStatus.NOT_FOUND);
        }
        Note noteForSave = mapDtoToNote(note);
        noteForSave.setUser(userNote);
        noteForSave.setTimestamp(new Timestamp(new Date().getTime()));

        Note savedNote = null;
        try {
            savedNote = noteRepository.save(noteForSave);
        } catch (DataAccessException ex) {
            log.error(ex);
            throw new NoteAppException(ex.toString(), HttpStatus.BAD_REQUEST);
        }
        return mapNoteToDto(savedNote);
    }

    @Override
    public NoteDTO updateNote(NoteDTO noteDto) {
        Note uploadedNote = noteRepository.findOne(noteDto.getId());
        if (uploadedNote == null) {
            throw new NoteAppException("Note is not stored in db", HttpStatus.NOT_FOUND);
        }
        if (!noteDto.getUserId().equals(uploadedNote.getUser().getId())) {
            throw new NoteAppException("You can't assign note to another user", HttpStatus.BAD_REQUEST);
        }
        mapper.map(noteDto, uploadedNote);
        return mapNoteToDto(noteRepository.save(uploadedNote));
    }

    @Override
    public NoteDTO findNote(int id) {
        Note note = noteRepository.findOne(id);
        if (note == null) {
            throw new NoteAppException("Note not found", HttpStatus.NOT_FOUND);
        }
        return mapNoteToDto(note);
    }

    @Override
    public void deleteNote(int id) {
        try {
            noteRepository.delete(id);
        } catch (DataAccessException ex) {
            log.error(ex);
            throw new NoteAppException(ex.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<NoteDTO> getNotesByUser(int id) {
        if (userRepository.findOne(id) == null) {
            throw new NoteAppException("User is not stored in db", HttpStatus.NOT_FOUND);
        }
        List<Note> notes = noteRepository.findByUserId(id);
        return notes.stream()
            .map(this::mapNoteToDto)
            .collect(Collectors.toList());
    }

    private Note mapDtoToNote(NoteDTO noteDTO) {
        return mapper.map(noteDTO, Note.class);
    }

    private NoteDTO mapNoteToDto(Note note) {
        return mapper.map(note, NoteDTO.class);
    }
}
