package seedu.scheduler.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.scheduler.logic.parser.CliSyntax.PREFIX_EVENT_REMINDER_DURATION;

import java.util.List;
import java.util.logging.Logger;

import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.core.index.Index;
import seedu.scheduler.logic.CommandHistory;
import seedu.scheduler.logic.commands.exceptions.CommandException;
import seedu.scheduler.logic.parser.Flag;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.event.Event;
import seedu.scheduler.model.event.ReminderDurationList;

/**
 * Delete reminders to an event identified using it's displayed index from the scheduler.
 */
public class DeleteReminderCommand extends EditCommand {

    public static final String COMMAND_WORD = "deleteReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete Reminders of the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_REMINDER_DURATION + "REMINDER DURATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EVENT_REMINDER_DURATION + "1h "
            + PREFIX_EVENT_REMINDER_DURATION + "30m ";

    public static final String MESSAGE_DO_NOT_SUPPORT_RECURRING = COMMAND_WORD
            + " current do not support recurring events. Coming in v2.0";
    public static final String MESSAGE_SOME_REMINDERS_NOT_PRESENT = "Warning: Some reminders entered are not present. "
            + "Other present reminders are removed from Event: %1$s";
    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Remove reminders from Event: %1$s";

    private static final Logger logger = LogsCenter.getLogger(DeleteReminderCommand.class);

    private final ReminderDurationList durationsToDelete;


    public DeleteReminderCommand(Index index, ReminderDurationList durationsToDelete, Flag... flags) {
        super(index, new EditCommand.EditEventDescriptor(), flags);
        this.durationsToDelete = durationsToDelete;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            logger.info(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        if (flags.length > 0) {
            logger.info(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            throw new CommandException(MESSAGE_DO_NOT_SUPPORT_RECURRING);
        }

        //Set up event to be edited and edited event according to user input
        logger.info("Creating event to be edited.");
        Event eventToEdit;
        eventToEdit = lastShownList.get(index.getZeroBased());
        ReminderDurationList reminderDurationListToEdit = eventToEdit.getReminderDurationList();
        Boolean isPresent = reminderDurationListToEdit.removeAll(durationsToDelete);
        editEventDescriptor.setReminderDurationList(reminderDurationListToEdit);
        super.execute(model, history);
        if (isPresent) {
            return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS, eventToEdit.getEventName()));
        } else {
            return new CommandResult(String.format(MESSAGE_SOME_REMINDERS_NOT_PRESENT, eventToEdit.getEventName()));
        }
    }
}

