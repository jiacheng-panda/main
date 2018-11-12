package seedu.scheduler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.scheduler.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_UNUSED;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_TAG_WORK;
import static seedu.scheduler.testutil.TypicalEvents.AD_HOC_WORK;
import static seedu.scheduler.testutil.TypicalEvents.DISCUSSION_WITH_JACK;
import static seedu.scheduler.testutil.TypicalEvents.INTERVIEW_WITH_JOHN;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.scheduler.model.event.EventNameContainsKeywordsPredicate;
import seedu.scheduler.model.event.Event;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.testutil.EventBuilder;
import seedu.scheduler.testutil.SchedulerBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasEvent(null);
    }

    @Test
    public void hasEvent_eventNotInScheduler_returnsFalse() {
        assertFalse(modelManager.hasEvent(DISCUSSION_WITH_JACK));
    }

    @Test
    public void hasEvent_eventInScheduler_returnsTrue() {
        modelManager.addEvents(List.of(DISCUSSION_WITH_JACK));
        assertTrue(modelManager.hasEvent(DISCUSSION_WITH_JACK));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredEventList().remove(0);
    }

    @Test
    public void deleteTagNonExistentTagModelUnchanged() throws Exception {
        Scheduler scheduler = new SchedulerBuilder().withEvent(DISCUSSION_WITH_JACK).withEvent(INTERVIEW_WITH_JOHN).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManager = new ModelManager(scheduler, userPrefs);
        modelManager.deleteTag(new Tag(VALID_TAG_UNUSED));

        assertEquals(new ModelManager(scheduler, userPrefs), modelManager);
    }

    @Test
    public void deleteTagTagUsedByMultipleEventsTagRemoved() throws Exception {
        Scheduler scheduler = new SchedulerBuilder().withEvent(DISCUSSION_WITH_JACK).withEvent(INTERVIEW_WITH_JOHN).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManager = new ModelManager(scheduler, userPrefs);
        modelManager.deleteTag(new Tag(VALID_TAG_WORK));

        ModelManager expectedModelManager = new ModelManager(scheduler, userPrefs);
        Event discussionWithoutSchoolTag = new EventBuilder(DISCUSSION_WITH_JACK).withTags(VALID_TAG_SCHOOL).build();
        Event interviewWithoutSchoolTag = new EventBuilder(INTERVIEW_WITH_JOHN).withTags(VALID_TAG_INTERVIEW).build();
        expectedModelManager.updateEvent(DISCUSSION_WITH_JACK, discussionWithoutSchoolTag);
        expectedModelManager.updateEvent(INTERVIEW_WITH_JOHN, interviewWithoutSchoolTag);

        assertEquals(expectedModelManager, modelManager);
    }



    @Test
    public void equals() {
        Scheduler scheduler = new SchedulerBuilder().withEvent(DISCUSSION_WITH_JACK)
                .withEvent(INTERVIEW_WITH_JOHN).build();
        Scheduler differentScheduler = new Scheduler();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(scheduler, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(scheduler, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different scheduler -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentScheduler, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = AD_HOC_WORK.getEventName().value.split("\\s+");
        modelManager.updateFilteredEventList(new EventNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(scheduler, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSchedulerFilePath(Paths.get("differentFilePath"));
        assertTrue(modelManager.equals(new ModelManager(scheduler, differentUserPrefs)));
    }
}
