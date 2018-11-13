package seedu.scheduler.logic.commands;

import static seedu.scheduler.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.scheduler.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.scheduler.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.core.index.Index;
import seedu.scheduler.logic.CommandHistory;
import seedu.scheduler.logic.commands.exceptions.CommandException;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.event.Event;
import seedu.scheduler.model.event.Remark;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the event identified "
            + "by the index number used in the last event listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "A talk on renewable energy.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Event: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Event: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index  of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownList.get(index.getZeroBased());
        Event editedEvent = new Event(eventToEdit.getEventUid(), eventToEdit.getEventSetUid(),
                eventToEdit.getEventName(), eventToEdit.getStartDateTime(),
                eventToEdit.getEndDateTime(), eventToEdit.getDescription(), eventToEdit.getVenue(),
                remark, eventToEdit.getRepeatType(), eventToEdit.getRepeatUntilDateTime(),
                eventToEdit.getTags(), eventToEdit.getReminderDurationList());

        model.updateEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        model.commitScheduler();

        return new CommandResult(generateSuccessMessage(editedEvent));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Event eventToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, eventToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);

    }
}
