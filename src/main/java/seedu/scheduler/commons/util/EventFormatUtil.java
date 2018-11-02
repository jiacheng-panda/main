package seedu.scheduler.commons.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event.Reminders;
import com.google.api.services.calendar.model.EventReminder;

import seedu.scheduler.logic.parser.ParserUtil;
import seedu.scheduler.logic.parser.exceptions.ParseException;
import seedu.scheduler.model.event.Description;
import seedu.scheduler.model.event.Event;
import seedu.scheduler.model.event.EventName;
import seedu.scheduler.model.event.ReminderDurationList;
import seedu.scheduler.model.event.RepeatType;
import seedu.scheduler.model.event.Venue;
import seedu.scheduler.model.tag.Tag;

/**
 * A class for converting the format between Google Event and local Event.
 */
public class EventFormatUtil {

    /**
     * Convert a List Google Event format to a list of Event in local Format.
     *
     * @param listOfGoogleEvents A list of Google event.
     *
     * @return A list of local Event.
     */
    public List<Event> convertGoogleListToLocalList(
            List<com.google.api.services.calendar.model.Event> listOfGoogleEvents) {
        List<Event> eventsToAddToLocal = new ArrayList<>();

        for (com.google.api.services.calendar.model.Event googleEvent : listOfGoogleEvents) {
            Event newEvent = convertGeventToLocalEvent(googleEvent);
            eventsToAddToLocal.add(newEvent);
        }
        return eventsToAddToLocal;
    }

    /**
     * Converts a local Event's starting data and time to Google format.
     *
     * @param event a local Event.
     *
     * @return a String in Google format.
     */
    public static String convertStartDateTimeToGoogleFormat(Event event) {
        //local format:2018-10-20 17:00:00
        //target :2018-10-21T22:30:00+08:00
        return event.getStartDateTime()
                .getPrettyString()
                .substring(0, 19)
                .replaceFirst(" ", "T")
                + "+08:00";
    }

    /**
     * Converts a local Event's ending data and time to Google format.
     *
     * @param event a local Event.
     *
     * @return a String in Google format.
     */
    public static String convertEndDateTimeToGoogleFormat(Event event) {
        return event.getEndDateTime()
                .getPrettyString()
                .substring(0, 19)
                .replaceFirst(" ", "T")
                + "+08:00";
    }

    /**
     * Convert the Google Event format to local Format.
     *
     * @param googleEvent The Google event.
     *
     * @return Event   The local Event.
     */
    public Event convertGeventToLocalEvent(
            com.google.api.services.calendar.model.Event googleEvent) {

        DateTime start = googleEvent.getStart().getDateTime();
        if (start == null) {
            //if no time, only date
            start = googleEvent.getStart().getDate();
        }
        String newEventname = googleEvent.getSummary(); //Summary==title in GoogleAPI

        //TODO:Simply the string processing code (OOP possible here)
        String newEventStart = String.valueOf(start); //eg:2018-10-16T22:30:00.000+08:00
        //TODO:Try not to use the migc number 0,10,11,19. Use other ways of string processing
        String newEventStartDate = newEventStart.substring(0, 10); //2018-10-16
        String newEventStartTime = newEventStart.substring(11, 19); //22:30:00
        newEventStartTime = newEventStartTime.replaceAll(":", ""); //223000

        DateTime end = googleEvent.getEnd().getDateTime();
        if (end == null) {
            //if no time, only date
            start = googleEvent.getEnd().getDate();
        }
        String newEventEnd = String.valueOf(end);
        String newEventEndDate = newEventEnd.substring(0, 10);
        String newEventEndTime = newEventEnd.substring(11, 19);
        newEventEndTime = newEventEndTime.replaceAll(":", "");
        String newDescription = googleEvent.getDescription() == null ? "" : googleEvent.getDescription();
        String newVenue = googleEvent.getLocation() == null ? "" : googleEvent.getLocation();


        EventName eventName = null;
        seedu.scheduler.model.event.DateTime startDateTime = null;
        seedu.scheduler.model.event.DateTime endDateTime = null;
        Description description = null;
        Venue venue = null;
        List<String> recurrence = new ArrayList<>();
        seedu.scheduler.model.event.DateTime
                repeatUntilDateTime = null;
        ReminderDurationList reminderDurationList = new ReminderDurationList();
        try {
            eventName = ParserUtil.parseEventName(newEventname);
            startDateTime = ParserUtil.parseDateTime(newEventStartDate + " " + newEventStartTime);
            endDateTime = ParserUtil.parseDateTime(newEventEndDate + " " + newEventEndTime);
            description = ParserUtil.parseDescription(newDescription);
            venue = ParserUtil.parseVenue(newVenue);
            recurrence = googleEvent.getRecurrence();
            repeatUntilDateTime = endDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //RRULE:FREQ=WEEKLY;BYDAY=SU
        String[] recurrenceText = null;
        RepeatType repeatType;
        if (recurrence == null) {
            repeatType = RepeatType.NONE;
        } else {
            switch (recurrence.size()) {
            case 1:
                recurrenceText = recurrence.get(0).split(";");
                break;
            case 3:
                recurrenceText = recurrence.get(2).split(";");
                break;
            default:
                //nothing
            }

            int rrulePosition = recurrenceText[0].indexOf("=") + 1;
            String rRule = recurrenceText[0].substring(rrulePosition); //RRULE:FREQ=WEEKLY

            switch (rRule) {
            case "YEARLY":
                repeatType = RepeatType.YEARLY;
                break;
            case "MONTHLY":
                repeatType = RepeatType.MONTHLY;
                break;
            case "WEEKLY":
                repeatType = RepeatType.WEEKLY;
                break;
            case "DAILY":
                repeatType = RepeatType.DAILY;
                break;
            default:
                repeatType = RepeatType.NONE;
                break;
            }
            switch (recurrenceText.length) {
            case 2:
                //RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH
                //ignore in local implementation
                break;
            case 3:
                //RRULE:FREQ=WEEKLY;UNTIL=20181108T155959Z;BYDAY=MO,TU,WE,TH
                try {
                    //local format:2018-10-20T17:00:00
                    //UNTIL=20181108T155959Z
                    String newRepeatUntil = recurrenceText[1].substring(6, 19);
                    StringBuilder newRepeatUntil2 = new StringBuilder(newRepeatUntil);
                    newRepeatUntil2.insert(4, "-")
                            .insert(7, "-")
                            .insert(13, ":")
                            .append(":00");
                    repeatUntilDateTime = ParserUtil.parseDateTime(newRepeatUntil2.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //nothing
            }
        }
        Reminders reminder = googleEvent.getReminders();
        if (reminder.getUseDefault()) {
            reminderDurationList.add(Duration.ofMinutes(30));
        } else {
            List<EventReminder> reminderOverrides = reminder.getOverrides();
            for (EventReminder eventReminder : reminderOverrides) {
                reminderDurationList.add(Duration.ofMinutes(eventReminder.getMinutes()));
            }
        }
        Set<Tag> tags = Collections.emptySet();
        return new Event(eventName, startDateTime, endDateTime,
                description,
                venue, repeatType, repeatUntilDateTime, tags, reminderDurationList);
    }

    /**
     * Calculates the relative index of an existing recurring event instance to the first recurring event instance.
     *
     * @param lastShownList The last shown list of events.
     * @param eventToDelete The recurring event to be deleted.
     *
     * @return The relative index of the event to be deleted to the first recurring event.
     */
    public static int calculateInstanceIndex(List<Event> lastShownList, Event eventToDelete) {
        int counter = -1;
        List<Event> lastShownListSorted = new ArrayList<>();
        for (Event event : lastShownList) {
            lastShownListSorted.add(event);
        }
        Collections.sort(lastShownListSorted, (
                a, b) -> a.getStartDateTime()
                        .compareTo(b.getStartDateTime()));

        for (Event event : lastShownListSorted) {
            if (event.getUuid().equals(eventToDelete.getUuid())) {
                counter++;
            }
            if (event.getUid() == eventToDelete.getUid()) {
                break;
            }
        }
        return counter;
    }

    /**
     * Calculates total number of existing recurring event instance.
     *
     * @param lastShownList The last shown list of events.
     * @param eventToDelete The recurring event to be deleted.
     *
     * @return The relative index of the event to be deleted to the first recurring event.
     */
    public static int calculateTotalInstanceNumber(List<Event> lastShownList, Event eventToDelete) {
        int counter = -1;

        for (Event event : lastShownList) {
            if (event.getUuid().equals(eventToDelete.getUuid())) {
                counter++;
            }
        }
        return counter;
    }
}
