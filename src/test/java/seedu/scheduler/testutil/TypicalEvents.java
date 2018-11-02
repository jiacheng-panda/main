package seedu.scheduler.testutil;

import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_END_DATETIME_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_END_DATETIME_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_EVENT_NAME_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_EVENT_NAME_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REPEAT_TYPE_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REPEAT_TYPE_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REPEAT_UNTIL_DATETIME_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REPEAT_UNTIL_DATETIME_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_START_DATETIME_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_START_DATETIME_MA3220;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_PLAY;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_VENUE_MA2101;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_VENUE_MA3220;
import static seedu.scheduler.model.util.SampleSchedulerDataUtil.getReminderDurationList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import seedu.scheduler.model.Scheduler;
import seedu.scheduler.model.event.Event;
import seedu.scheduler.model.event.RepeatType;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final String KEYWORD_MATCHING_STARTUP = "Startup"; // A keyword that matches Study
    private static final ArrayList<UUID> CONSTANT_UID = new ArrayList<>(Arrays.asList(
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a621"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a622"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a623"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a624"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a625"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a626"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a627"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a628"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a629"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a630"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a631")));
    private static final ArrayList<UUID> CONSTANT_UUID = new ArrayList<>(Arrays.asList(
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a632"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a633"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a634"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a635"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a636"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a637"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a638"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a639"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a640"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a641"),
            UUID.fromString("066db0fd-0bd2-423f-aef4-fd1f8d30a642")));

    // single event
    public static final Event DISCUSSION_WITH_JACK = new EventBuilder()
            .withUid(CONSTANT_UID.get(0))
            .withUuid(CONSTANT_UUID.get(0))
            .withEventName("Discussion with Jack")
            .withStartDateTime(LocalDateTime.of(2018, 1, 1, 14, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 1, 17, 0))
            .withDescription("Talk about personal problems").withVenue("Jack's House")
            .withRepeatType(RepeatType.NONE).withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 1, 17, 0))
            .withTags("Talk", "Personal")
            .withReminderDurationList(getReminderDurationList(0, 2)).build();
    public static final Event INTERVIEW_WITH_JOHN = new EventBuilder()
            .withUid(CONSTANT_UID.get(1))
            .withUuid(CONSTANT_UUID.get(1))
            .withEventName("Interview with John")
            .withStartDateTime(LocalDateTime.of(2018, 1, 2, 13, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 2, 15, 0))
            .withDescription("Interview for position as a software engineer").withVenue("Jane Street")
            .withRepeatType(RepeatType.NONE).withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 2, 15, 0))
            .withTags("Interview", "Work")
            .withReminderDurationList(getReminderDurationList(1)).build();

    // daily event
    public static final Event STUDY_WITH_JANE_DAY_ONE = new EventBuilder()
            .withUid(CONSTANT_UID.get(2))
            .withUuid(CONSTANT_UUID.get(2))
            .withEventName("Study with Jane")
            .withStartDateTime(LocalDateTime.of(2018, 1, 3, 10, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 3, 11, 0))
            .withDescription("Study for MA2101").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 3))
            .build();
    public static final Event STUDY_WITH_JANE_DAY_TWO = new EventBuilder()
            .withUid(CONSTANT_UID.get(2))
            .withUuid(CONSTANT_UUID.get(2))
            .withEventName("Study with Jane")
            .withStartDateTime(LocalDateTime.of(2018, 1, 4, 10, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 4, 11, 0))
            .withDescription("Study for MA2101").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 3))
            .build();
    public static final Event STUDY_WITH_JANE_DAY_THREE = new EventBuilder()
            .withUid(CONSTANT_UID.get(2))
            .withUuid(CONSTANT_UUID.get(2))
            .withEventName("Study with Jane")
            .withStartDateTime(LocalDateTime.of(2018, 1, 5, 10, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 5, 11, 0))
            .withDescription("Study for MA2101").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 3))
            .build();
    public static final Event STUDY_WITH_JANE_DAY_FOUR = new EventBuilder()
            .withUid(CONSTANT_UID.get(2))
            .withUuid(CONSTANT_UUID.get(2))
            .withEventName("Study with Jane")
            .withStartDateTime(LocalDateTime.of(2018, 1, 6, 10, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withDescription("Study for MA2101").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 3))
            .build();
    public static final Event STUDY_WITH_JILL_DAY_ONE = new EventBuilder()
            .withUid(CONSTANT_UID.get(3))
            .withUuid(CONSTANT_UUID.get(3))
            .withEventName("Study with Jill")
            .withStartDateTime(LocalDateTime.of(2018, 1, 3, 11, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 3, 12, 0))
            .withDescription("Study for CS2103").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 12, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList())
            .build();
    public static final Event STUDY_WITH_JILL_DAY_TWO = new EventBuilder()
            .withUid(CONSTANT_UID.get(3))
            .withUuid(CONSTANT_UUID.get(3))
            .withEventName("Study with Jill")
            .withStartDateTime(LocalDateTime.of(2018, 1, 4, 11, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 4, 12, 0))
            .withDescription("Study for CS2103").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 12, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList())
            .build();
    public static final Event STUDY_WITH_JILL_DAY_THREE = new EventBuilder()
            .withUid(CONSTANT_UID.get(3))
            .withUuid(CONSTANT_UUID.get(3))
            .withEventName("Study with Jill")
            .withStartDateTime(LocalDateTime.of(2018, 1, 5, 11, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 5, 12, 0))
            .withDescription("Study for CS2103").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 12, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList())
            .build();
    public static final Event STUDY_WITH_JILL_DAY_FOUR = new EventBuilder()
            .withUid(CONSTANT_UID.get(3))
            .withUuid(CONSTANT_UUID.get(3))
            .withEventName("Study with Jill")
            .withStartDateTime(LocalDateTime.of(2018, 1, 6, 11, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 6, 12, 0))
            .withDescription("Study for CS2103").withVenue("NUS")
            .withRepeatType(RepeatType.DAILY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 1, 6, 12, 0))
            .withTags("Study", "School")
            .withReminderDurationList(getReminderDurationList())
            .build();

    // monthly event
    public static final Event STARTUP_LECTURE_MONTH_ONE = new EventBuilder()
            .withUid(CONSTANT_UID.get(4))
            .withUuid(CONSTANT_UUID.get(4))
            .withEventName("Startup Lecture")
            .withStartDateTime(LocalDateTime.of(2018, 1, 28, 8, 0))
            .withEndDateTime(LocalDateTime.of(2018, 1, 28, 10, 0))
            .withDescription("Lecture about entrepreneurship").withVenue("iCube")
            .withRepeatType(RepeatType.MONTHLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 3, 28, 10, 0))
            .withTags("Timetable", "Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 2, 3))
            .build();
    public static final Event STARTUP_LECTURE_MONTH_TWO = new EventBuilder()
            .withUid(CONSTANT_UID.get(4))
            .withUuid(CONSTANT_UUID.get(4))
            .withEventName("Startup Lecture")
            .withStartDateTime(LocalDateTime.of(2018, 2, 28, 8, 0))
            .withEndDateTime(LocalDateTime.of(2018, 2, 28, 10, 0))
            .withDescription("Lecture about entrepreneurship").withVenue("iCube")
            .withRepeatType(RepeatType.MONTHLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 3, 28, 10, 0))
            .withTags("Timetable", "Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 2, 3))
            .build();
    public static final Event STARTUP_LECTURE_MONTH_THREE = new EventBuilder()
            .withUid(CONSTANT_UID.get(4))
            .withUuid(CONSTANT_UUID.get(4))
            .withEventName("Startup Lecture")
            .withStartDateTime(LocalDateTime.of(2018, 3, 28, 8, 0))
            .withEndDateTime(LocalDateTime.of(2018, 3, 28, 10, 0))
            .withDescription("Lecture about entrepreneurship").withVenue("iCube")
            .withRepeatType(RepeatType.MONTHLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2018, 3, 28, 10, 0))
            .withTags("Timetable", "Study", "School")
            .withReminderDurationList(getReminderDurationList(0, 1, 2, 3))
            .build();

    // yearly event
    public static final Event LEAP_DAY_CELEBRATION_YEAR_ONE = new EventBuilder()
            .withUid(CONSTANT_UID.get(5))
            .withUuid(CONSTANT_UUID.get(5))
            .withEventName("Leap Day Celebration")
            .withStartDateTime(LocalDateTime.of(2016, 2, 29, 0, 0))
            .withEndDateTime(LocalDateTime.of(2016, 2, 29, 1, 0))
            .withDescription("Celebrate a day that happens one in 4 years").withVenue("Marina Bay Sands")
            .withRepeatType(RepeatType.YEARLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2020, 2, 29, 1, 0))
            .withTags("Celebration")
            .withReminderDurationList(getReminderDurationList(0))
            .build();
    public static final Event LEAP_DAY_CELEBRATION_YEAR_TWO = new EventBuilder()
            .withUid(CONSTANT_UID.get(5))
            .withUuid(CONSTANT_UUID.get(5))
            .withEventName("Leap Day Celebration")
            .withStartDateTime(LocalDateTime.of(2020, 2, 29, 0, 0))
            .withEndDateTime(LocalDateTime.of(2020, 2, 29, 1, 0))
            .withDescription("Celebrate a day that happens one in 4 years").withVenue("Marina Bay Sands")
            .withRepeatType(RepeatType.YEARLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2020, 2, 29, 1, 0))
            .withTags("Celebration")
            .withReminderDurationList(getReminderDurationList(1, 2))
            .build();
    public static final Event JIM_BIRTHDAY_YEAR_ONE = new EventBuilder()
            .withUid(CONSTANT_UID.get(6))
            .withUuid(CONSTANT_UUID.get(6))
            .withEventName("Jim's Birthday")
            .withStartDateTime(LocalDateTime.of(2018, 12, 31, 0, 0))
            .withEndDateTime(LocalDateTime.of(2018, 12, 31, 1, 0))
            .withDescription("Celebrate Jim's Birthday").withVenue("Jim's House")
            .withRepeatType(RepeatType.YEARLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2020, 12, 31, 1, 0))
            .withTags("Celebration")
            .withReminderDurationList(getReminderDurationList(1, 2))
            .build();
    public static final Event JIM_BIRTHDAY_YEAR_TWO = new EventBuilder()
            .withUid(CONSTANT_UID.get(6))
            .withUuid(CONSTANT_UUID.get(6))
            .withEventName("Jim's Birthday")
            .withStartDateTime(LocalDateTime.of(2019, 12, 31, 0, 0))
            .withEndDateTime(LocalDateTime.of(2019, 12, 31, 1, 0))
            .withDescription("Celebrate Jim's Birthday").withVenue("Jim's House")
            .withRepeatType(RepeatType.YEARLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2020, 12, 31, 1, 0))
            .withTags("Celebration")
            .withReminderDurationList(getReminderDurationList(1, 2))
            .build();
    public static final Event JIM_BIRTHDAY_YEAR_THREE = new EventBuilder()
            .withUid(CONSTANT_UID.get(6))
            .withUuid(CONSTANT_UUID.get(6))
            .withEventName("Jim's Birthday")
            .withStartDateTime(LocalDateTime.of(2020, 12, 31, 0, 0))
            .withEndDateTime(LocalDateTime.of(2020, 12, 31, 1, 0))
            .withDescription("Celebrate Jim's Birthday").withVenue("Jim's House")
            .withRepeatType(RepeatType.YEARLY)
            .withRepeatUntilDateTime(LocalDateTime.of(2020, 12, 31, 1, 0))
            .withTags("Celebration")
            .withReminderDurationList(getReminderDurationList(1, 2))
            .build();

    // daily list
    public static final List<Event> STUDY_WITH_JANE_DAILY_LIST = List.of(STUDY_WITH_JANE_DAY_ONE,
            STUDY_WITH_JANE_DAY_TWO, STUDY_WITH_JANE_DAY_THREE, STUDY_WITH_JANE_DAY_FOUR);
    public static final List<Event> STUDY_WITH_JILL_DAILY_LIST = List.of(STUDY_WITH_JILL_DAY_ONE,
            STUDY_WITH_JILL_DAY_TWO, STUDY_WITH_JILL_DAY_THREE, STUDY_WITH_JILL_DAY_FOUR);

    // monthly list
    public static final List<Event> STARTUP_LECTURE_MONTHLY_LIST = List.of(STARTUP_LECTURE_MONTH_ONE,
            STARTUP_LECTURE_MONTH_TWO, STARTUP_LECTURE_MONTH_THREE);

    // yearly list
    public static final List<Event> LEAP_DAY_CELEBRATION_YEARLY_LIST = List.of(LEAP_DAY_CELEBRATION_YEAR_ONE,
            LEAP_DAY_CELEBRATION_YEAR_TWO);
    public static final List<Event> JIM_BIRTHDAY_YEARLY_LIST = List.of(JIM_BIRTHDAY_YEAR_ONE,
            JIM_BIRTHDAY_YEAR_TWO, JIM_BIRTHDAY_YEAR_THREE);

    // Manually added
    public static final Event AD_HOC_WORK = new EventBuilder().withUid(CONSTANT_UID.get(7))
            .withUuid(CONSTANT_UUID.get(7))
            .withEventName("Ad hoc work").withStartDateTime(LocalDateTime.of(2018, 12, 12, 0, 0))
            .withEndDateTime(LocalDateTime.of(2018, 12, 12, 1, 0))
            .withDescription("Ad hoc part time work").withVenue("Starbucks")
            .withRepeatType(RepeatType.NONE).withRepeatUntilDateTime(LocalDateTime.of(2018, 12, 12, 1, 0))
            .withTags("Work").withReminderDurationList(getReminderDurationList(3)).build();
    public static final Event ONE_TIME_JOB = new EventBuilder().withUid(CONSTANT_UID.get(7))
            .withUuid(CONSTANT_UUID.get(8))
            .withEventName("One time job").withStartDateTime(LocalDateTime.of(2018, 6, 1, 0, 0))
            .withEndDateTime(LocalDateTime.of(2018, 6, 1, 1, 0))
            .withDescription("One time job and get paid").withVenue("McDonald's")
            .withRepeatType(RepeatType.NONE).withRepeatUntilDateTime(LocalDateTime.of(2018, 6, 1, 1, 0))
            .withTags("Work").withReminderDurationList(getReminderDurationList(3)).build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event MA2101_JANUARY_1_2018_YEARLY = new EventBuilder().withUid(CONSTANT_UID.get(9))
            .withUuid(CONSTANT_UUID.get(9))
            .withEventName(VALID_EVENT_NAME_MA2101).withStartDateTime(VALID_START_DATETIME_MA2101)
            .withEndDateTime(VALID_END_DATETIME_MA2101).withDescription(VALID_DESCRIPTION_MA2101)
            .withVenue(VALID_VENUE_MA2101).withRepeatType(VALID_REPEAT_TYPE_MA2101)
            .withRepeatUntilDateTime(VALID_REPEAT_UNTIL_DATETIME_MA2101).withTags(VALID_TAG_SCHOOL)
            .withReminderDurationList(getReminderDurationList(3)).build();

    public static final Event MA3220_JANUARY_1_2019_SINGLE = new EventBuilder().withUid(CONSTANT_UID.get(10))
            .withUuid(CONSTANT_UUID.get(10))
            .withEventName(VALID_EVENT_NAME_MA3220).withStartDateTime(VALID_START_DATETIME_MA3220)
            .withEndDateTime(VALID_END_DATETIME_MA3220).withDescription(VALID_DESCRIPTION_MA3220)
            .withVenue(VALID_VENUE_MA3220).withRepeatType(VALID_REPEAT_TYPE_MA3220)
            .withRepeatUntilDateTime(VALID_REPEAT_UNTIL_DATETIME_MA3220).withTags(VALID_TAG_PLAY)
            .withReminderDurationList(getReminderDurationList(3)).build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code Scheduler} with all the typical events.
     */
    public static Scheduler getTypicalScheduler() {
        Scheduler scheduler = new Scheduler();
        for (Event event : getTypicalEvents()) {
            scheduler.addEvent(event);
        }
        return scheduler;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(DISCUSSION_WITH_JACK, INTERVIEW_WITH_JOHN, STUDY_WITH_JANE_DAY_ONE,
                STUDY_WITH_JANE_DAY_TWO, STUDY_WITH_JANE_DAY_THREE, STUDY_WITH_JANE_DAY_FOUR,
                STUDY_WITH_JILL_DAY_ONE, STUDY_WITH_JILL_DAY_TWO, STUDY_WITH_JILL_DAY_THREE,
                STUDY_WITH_JILL_DAY_FOUR, JIM_BIRTHDAY_YEAR_ONE, JIM_BIRTHDAY_YEAR_TWO,
                JIM_BIRTHDAY_YEAR_THREE, LEAP_DAY_CELEBRATION_YEAR_ONE, LEAP_DAY_CELEBRATION_YEAR_TWO,
                STARTUP_LECTURE_MONTH_ONE, STARTUP_LECTURE_MONTH_TWO, STARTUP_LECTURE_MONTH_THREE));
    }
}
