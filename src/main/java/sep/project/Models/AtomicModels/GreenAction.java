package sep.project.Models.AtomicModels;

import java.util.UUID;

public class GreenAction {
    private Long actionId;
    private String description;
    private int pointValue;
    private boolean approved; // to keep track of the stuff the admin hasnt yet approved
    public Long userId;

    public GreenAction(String description, int pointValue, Long userId) {
        this.pointValue = pointValue;
        this.description = description;

        this.userId = userId;
        this.actionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;// so theres no overlaps between the
                                                                                    // users
        this.approved = false;
    }

    // for jackson to be able to pull from persistence
    public GreenAction() {
    };

    public GreenAction(String description, int pointValue, Long userId, Boolean approved, Long actionId) {
        this.pointValue = pointValue;
        this.description = description;

        this.userId = userId;
        this.actionId = actionId;
        this.approved = approved;
    }

    // to persist this boy
    public String toJsonString() {
        return "{\"actionId\":%s,\"description\":\"%s\",\"pointValue\":%s,\"approved\":%s,\"userId\":%s}"
                .formatted(Long.toString(actionId), description, Integer.toString(pointValue),
                        Boolean.toString(approved), Long.toString(userId));
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
