package seedu.address.model.event;

import java.util.UUID;

/**
 * Manages Info needed for PopUp alert
 */
public class EventPopUpInfo implements Comparable<EventPopUpInfo> {
    private UUID uid;
    private String description;
    private DateTime popUpDateTime;

    public EventPopUpInfo(String description, DateTime popUpDateTime) {
        this.description = description;
        this.popUpDateTime = popUpDateTime;
    }

    public int compareTo(EventPopUpInfo other) {
        return this.popUpDateTime.compareTo(other.popUpDateTime);
    }

    public DateTime getPopUpDateTime () {
        return popUpDateTime;
    }

    public String getDescription() {
        return description;
    }

}
