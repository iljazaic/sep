package sep.project.Models.AtomicModels;

import java.util.UUID;

public class GreenAction {
    private final Long actionId;
    private String description;
    private int pointValue;
    private boolean approved; // to keep track of the stuff the admin hasnt yet approved
    public final Long userId;

    public GreenAction(String desc, int value, Long userId) {
        this.pointValue = value;
        this.description = desc;

        this.userId = userId;
        this.actionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;// so theres no overlaps between the
                                                                                    // users
        this.approved = false;
    }

    // to persist that bitch
    public String toJsonString() {
        return "{\"actionId\":%s,\"description\":\"%s\",\"pointValue\":%s,\"approved\":%s,\"userId\":%s}"
                .formatted(Long.toString(actionId), description, Integer.toString(pointValue), Boolean.toString(approved), Long.toString(userId));
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setApproved(boolean setTo) {
        this.approved = setTo;
    }

    public boolean getApproved() {
        return this.approved;
    }

    public Long getActionId() {
        return actionId;
    }

    public String getDescription() {
        return description;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

}
