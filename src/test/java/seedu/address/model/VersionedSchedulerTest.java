package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEvents.JANUARY_1_2018_SINGLE;
import static seedu.address.testutil.TypicalEvents.JANUARY_2_2018_SINGLE;
import static seedu.address.testutil.TypicalEvents.JANUARY_3_2018_SINGLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.SchedulerBuilder;

public class VersionedSchedulerTest {

    private final ReadOnlyScheduler schedulerWithJanuaryFirstSingle =
            new SchedulerBuilder().withEvent(JANUARY_1_2018_SINGLE).build();
    private final ReadOnlyScheduler schedulerWithJanuarySecondSingle =
            new SchedulerBuilder().withEvent(JANUARY_2_2018_SINGLE).build();
    private final ReadOnlyScheduler schedulerWithJanuaryThirdSingle =
            new SchedulerBuilder().withEvent(JANUARY_3_2018_SINGLE).build();
    private final ReadOnlyScheduler emptyScheduler = new SchedulerBuilder().build();

    @Test
    public void commit_singleAddressBook_noStatesRemovedCurrentStateSaved() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(emptyScheduler);

        versionedScheduler.commit();
        assertSchedulerListStatus(versionedScheduler,
                Collections.singletonList(emptyScheduler),
                emptyScheduler,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleSchedulerPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        versionedScheduler.commit();
        assertSchedulerListStatus(versionedScheduler,
                Arrays.asList(emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle),
                schedulerWithJanuarySecondSingle,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleSchedulerPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 2);

        versionedScheduler.commit();
        assertSchedulerListStatus(versionedScheduler,
                Collections.singletonList(emptyScheduler),
                emptyScheduler,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleSchedulerPointerAtEndOfStateList_returnsTrue() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        assertTrue(versionedScheduler.canUndo());
    }

    @Test
    public void canUndo_multipleSchedulerPointerAtStartOfStateList_returnsTrue() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 1);

        assertTrue(versionedScheduler.canUndo());
    }

    @Test
    public void canUndo_singleScheduler_returnsFalse() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(emptyScheduler);

        assertFalse(versionedScheduler.canUndo());
    }

    @Test
    public void canUndo_multipleSchedulerPointerAtStartOfStateList_returnsFalse() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 2);

        assertFalse(versionedScheduler.canUndo());
    }

    @Test
    public void canRedo_multipleSchedulerPointerNotAtEndOfStateList_returnsTrue() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 1);

        assertTrue(versionedScheduler.canRedo());
    }

    @Test
    public void canRedo_multipleSchedulerPointerAtStartOfStateList_returnsTrue() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 2);

        assertTrue(versionedScheduler.canRedo());
    }

    @Test
    public void canRedo_singleScheduler_returnsFalse() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(emptyScheduler);

        assertFalse(versionedScheduler.canRedo());
    }

    @Test
    public void canRedo_multipleSchedulerPointerAtEndOfStateList_returnsFalse() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        assertFalse(versionedScheduler.canRedo());
    }

    @Test
    public void undo_multipleSchedulerPointerAtEndOfStateList_success() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        versionedScheduler.undo();
        assertSchedulerListStatus(versionedScheduler,
                Collections.singletonList(emptyScheduler),
                schedulerWithJanuaryFirstSingle,
                Collections.singletonList(schedulerWithJanuarySecondSingle));
    }

    @Test
    public void undo_multipleSchedulerPointerNotAtStartOfStateList_success() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 1);

        versionedScheduler.undo();
        assertSchedulerListStatus(versionedScheduler,
                Collections.emptyList(),
                emptyScheduler,
                Arrays.asList(schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle));
    }

    @Test
    public void undo_singleScheduler_throwsNoUndoableStateException() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(emptyScheduler);

        assertThrows(VersionedScheduler.NoUndoableStateException.class, versionedScheduler::undo);
    }

    @Test
    public void undo_multipleSchedulerPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 2);

        assertThrows(VersionedScheduler.NoUndoableStateException.class, versionedScheduler::undo);
    }

    @Test
    public void redo_multipleSchedulerPointerNotAtEndOfStateList_success() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 1);

        versionedScheduler.redo();
        assertSchedulerListStatus(versionedScheduler,
                Arrays.asList(emptyScheduler, schedulerWithJanuaryFirstSingle),
                schedulerWithJanuarySecondSingle,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleSchedulerPointerAtStartOfStateList_success() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 2);

        versionedScheduler.redo();
        assertSchedulerListStatus(versionedScheduler,
                Collections.singletonList(emptyScheduler),
                schedulerWithJanuaryFirstSingle,
                Collections.singletonList(schedulerWithJanuarySecondSingle));
    }

    @Test
    public void redo_singleScheduler_throwsNoRedoableStateException() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(emptyScheduler);

        assertThrows(VersionedScheduler.NoRedoableStateException.class, versionedScheduler::redo);
    }

    @Test
    public void redo_multipleSchedulerPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedScheduler versionedScheduler = prepareSchedulerList(
                emptyScheduler, schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        assertThrows(VersionedScheduler.NoRedoableStateException.class, versionedScheduler::redo);
    }

    @Test
    public void equals() {
        VersionedScheduler versionedScheduler =
                prepareSchedulerList(schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        // same values -> returns true
        VersionedScheduler copy =
                prepareSchedulerList(schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);

        assertTrue(versionedScheduler.equals(copy));

        // same object -> returns true
        assertTrue(versionedScheduler.equals(versionedScheduler));

        // null -> returns false
        assertFalse(versionedScheduler.equals(null));

        // different types -> returns false
        assertFalse(versionedScheduler.equals(1));

        // different state list -> returns false
        VersionedScheduler differentSchedulerList = prepareSchedulerList(schedulerWithJanuarySecondSingle, schedulerWithJanuaryThirdSingle);
        assertFalse(versionedScheduler.equals(differentSchedulerList));

        // different current pointer index -> returns false
        VersionedScheduler differentCurrentStatePointer = prepareSchedulerList(
                schedulerWithJanuaryFirstSingle, schedulerWithJanuarySecondSingle);
        shiftCurrentStatePointerLeftwards(versionedScheduler, 1);
        assertFalse(versionedScheduler.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedScheduler} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedScheduler#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedScheduler#currentStatePointer} is equal to {@code expectedStatesAfterPointer}.
     */
    private void assertSchedulerListStatus(VersionedScheduler versionedScheduler,
                                           List<ReadOnlyScheduler> expectedStatesBeforePointer,
                                           ReadOnlyScheduler expectedCurrentState,
                                           List<ReadOnlyScheduler> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new Scheduler(versionedScheduler), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedScheduler.canUndo()) {
            versionedScheduler.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyScheduler expectedScheduler : expectedStatesBeforePointer) {
            assertEquals(expectedScheduler, new Scheduler(versionedScheduler));
            versionedScheduler.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyScheduler expectedScheduler : expectedStatesAfterPointer) {
            versionedScheduler.redo();
            assertEquals(expectedScheduler, new Scheduler(versionedScheduler));
        }

        // check that there are no more states after pointer
        assertFalse(versionedScheduler.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedScheduler.undo());
    }

    /**
     * Creates and returns a {@code VersionedScheduler} with the {@code schedulerStates} added into it, and the
     * {@code VersionedScheduler#currentStatePointer} at the end of list.
     */
    private VersionedScheduler prepareSchedulerList(ReadOnlyScheduler... schedulerStates) {
        assertFalse(schedulerStates.length == 0);

        VersionedScheduler versionedScheduler = new VersionedScheduler(schedulerStates[0]);
        for (int i = 1; i < schedulerStates.length; i++) {
            versionedScheduler.resetData(schedulerStates[i]);
            versionedScheduler.commit();
        }

        return versionedScheduler;
    }

    /**
     * Shifts the {@code VersionedScheduler#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedScheduler versionedScheduler, int count) {
        for (int i = 0; i < count; i++) {
            versionedScheduler.undo();
        }
    }
}
