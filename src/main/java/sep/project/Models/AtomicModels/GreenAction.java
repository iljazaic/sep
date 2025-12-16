package sep.project.Models.AtomicModels;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GreenAction {
    private Long actionId;
    private String description;
    private int pointValue;
    private boolean approved; // to keep track of the stuff the admin hasnt yet approved
    private Long userId;
    private Instant timestamp;

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

    public GreenAction(String description, int pointValue, Long userId, Boolean approved, Long actionId,
            Instant timestamp) {
        this.pointValue = pointValue;
        this.description = description;
        this.timestamp = timestamp;
        this.userId = userId;
        this.actionId = actionId;
        this.approved = approved;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    // to persist this boy
    public String toJsonString() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return "{\"actionId\":%s,\"description\":\"%s\",\"pointValue\":%s,\"approved\":%s,\"userId\":%s}"
                .formatted(Long.toString(actionId), description, Integer.toString(pointValue),
                        Boolean.toString(approved), Long.toString(userId), mapper.writeValueAsString(timestamp));
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
