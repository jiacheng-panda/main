package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyScheduler;
import seedu.address.model.Scheduler;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Priority;
import seedu.address.model.event.RepeatType;
import seedu.address.model.event.Venue;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Scheduler} with sample data.
 */
public class SampleSchedulerDataUtil {

    private static final UUID CONSTANT_UUID = UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a625");

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(CONSTANT_UUID,
                    new EventName("01 January 2018"),
                    new DateTime(LocalDateTime.of(2018, 1, 1, 1, 0)),
                    new DateTime(LocalDateTime.of(2018, 1, 1, 2, 0)),
                    new Description("01 January 2018"),
                    Priority.LOW,
                    new Venue("Computing"),
                    RepeatType.NONE,
                    new DateTime(LocalDateTime.of(2018, 1, 1, 2, 0))),
            new Event(CONSTANT_UUID,
                    new EventName("02 January 2018"),
                    new DateTime(LocalDateTime.of(2018, 1, 2, 1, 0)),
                    new DateTime(LocalDateTime.of(2018, 1, 2, 2, 0)),
                    new Description("02 January 2018"),
                    Priority.MEDIUM,
                    new Venue("Science"),
                    RepeatType.NONE,
                    new DateTime(LocalDateTime.of(2018, 1, 2, 2, 0))),
            new Event(CONSTANT_UUID,
                    new EventName("03 January 2018"),
                    new DateTime(LocalDateTime.of(2018, 1, 3, 1, 0)),
                    new DateTime(LocalDateTime.of(2018, 1, 3, 2, 0)),
                    new Description("03 January 2018"),
                    Priority.HIGH,
                    new Venue("Arts"),
                    RepeatType.NONE,
                    new DateTime(LocalDateTime.of(2018, 1, 3, 2, 0))),
            new Event(CONSTANT_UUID,
                    new EventName("Play"),
                    new DateTime(LocalDateTime.of(2018, 1, 1, 0, 0)),
                    new DateTime(LocalDateTime.of(2018, 1, 1, 1, 0)),
                    new Description("Play on first january"),
                    Priority.LOW,
                    new Venue("Home"),
                    RepeatType.NONE,
                    new DateTime(LocalDateTime.of(2018, 1, 1, 1, 0)))
        };

    }

    public static ReadOnlyScheduler getSampleScheduler() {
        Scheduler sampleScheduler = new Scheduler();
        for (Event sampleEvent : getSampleEvents()) {
            sampleScheduler.addEvent(sampleEvent);
        }
        return sampleScheduler;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
    //                getTagSet("friends")

}
