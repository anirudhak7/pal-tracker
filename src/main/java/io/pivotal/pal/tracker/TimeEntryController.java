package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity create(TimeEntry timeEntryToCreate) {

        TimeEntry tm = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(tm);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry tm = timeEntryRepository.find(timeEntryId);
        if(tm == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(tm);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> tms = timeEntryRepository.list();
        return ResponseEntity.ok().body(tms);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        if(timeEntryId != expected.getId()){
            return ResponseEntity.notFound().build();
        }
        TimeEntry tm = timeEntryRepository.update(timeEntryId, expected);
        return ResponseEntity.ok().body(tm);
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
