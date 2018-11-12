package seedu.scheduler.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.scheduler.logic.CommandHistory;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.tag.Tag;

/**
 * Delete reminders to an event identified using it's displayed index from the scheduler.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the selected tag in all events.\n"
            + "Parameters: TAG NAME \n"
            + "Example: " + COMMAND_WORD + " Study ";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Tag %1$s is removed from all events.";
    public static final String MESSAGE_EMPTY_REMINDER_ENTERED = "Warning: no reminder is entered";

    private final Tag tagName;

    public DeleteTagCommand(Tag tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.deleteTag(tagName);
        return new CommandResult(
                String.format(MESSAGE_DELETE_TAG_SUCCESS, tagName));
    }
}

