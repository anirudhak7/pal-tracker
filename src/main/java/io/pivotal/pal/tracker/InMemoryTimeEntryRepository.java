package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private final HashMap<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> tmList = new ArrayList<TimeEntry>();
        Iterator<Map.Entry<Long, TimeEntry>> it = timeEntryMap.entrySet().iterator();
        while(it.hasNext()){
            tmList.add(it.next().getValue());
        }

        return tmList;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if(timeEntry.getId()==-1)
        {
            timeEntry.setId(timeEntryMap.size()+1);
        }
        timeEntryMap.put(timeEntry.getId(),timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);

    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
//        TimeEntry current = timeEntryMap.get(id);
        timeEntry.setId(id);
        timeEntryMap.put(id,timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        timeEntryMap.remove(id);

    }
}
