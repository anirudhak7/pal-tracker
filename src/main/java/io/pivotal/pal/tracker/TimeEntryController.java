package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry tm = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(tm);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry tm = timeEntryRepository.find(timeEntryId);
        if(tm == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(tm);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> tms = timeEntryRepository.list();
        return ResponseEntity.ok().body(tms);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry originalEntry = timeEntryRepository.find(timeEntryId);
        if(originalEntry == null && timeEntryId != expected.getId()){
            return ResponseEntity.notFound().build();
        }
        TimeEntry tm = timeEntryRepository.update(timeEntryId, expected);
        return ResponseEntity.ok().body(tm);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
