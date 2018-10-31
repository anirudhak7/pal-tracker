package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    private TimeEntryRepository timeEntryRepository;
    private final int MAX_NUMBER_OF_ENTRIES = 5;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepository){

        this.timeEntryRepository = timeEntryRepository;
    }

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();

        builder.down();
        int count = timeEntryRepository.list().size();
        if(count < MAX_NUMBER_OF_ENTRIES){
            builder.up();
        }

        return builder.build();
    }
}
