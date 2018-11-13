package seedu.scheduler.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REMARK_DISCUSSION;
import static seedu.scheduler.logic.commands.CommandTestUtil.VALID_REMARK_INTERVIEW;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.scheduler.logic.commands.RemarkCommand.MESSAGE_ARGUMENTS;
import static seedu.scheduler.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.scheduler.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.scheduler.testutil.TypicalEvents.getTypicalScheduler;

import org.junit.Test;

import seedu.scheduler.logic.CommandHistory;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.ModelManager;
import seedu.scheduler.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void execute() {
        final String remark = "Some remark";

        assertCommandFailure(new RemarkCommand(INDEX_FIRST_EVENT, remark), model, new CommandHistory(),
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_EVENT.getOneBased(), remark));
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_EVENT, VALID_REMARK_DISCUSSION);

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_EVENT, VALID_REMARK_DISCUSSION);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_EVENT, VALID_REMARK_DISCUSSION)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_EVENT, VALID_REMARK_INTERVIEW)));
    }
}
