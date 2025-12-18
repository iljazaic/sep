package sep.project.Models.AtomicModels;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import sep.project.Models.Interfaces.JsonManager;

public class GreenAction implements JsonManager {
    private Long actionId;
    private String description;
    private int pointValue;
    private Long userId;
    private Instant timestamp;
    private boolean pointsApplied;

    public GreenAction(String description, int pointValue, Long userId) {
        this.pointValue = pointValue;
        this.description = description;

        this.userId = userId;
        this.actionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;// so theres no overlaps between the
                                                                                    // users
        this.pointsApplied = false;

        this.timestamp = Instant.now();
    }

    // for jackson to be able to pull from persistence
    public GreenAction() {
    };

    public GreenAction(String description, int pointValue, Long userId, Long actionId,
            Instant timestamp) {
        this.pointValue = pointValue;
        this.description = description;
        this.timestamp = timestamp;
        this.userId = userId;
        this.actionId = actionId;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public Long getUserId() {
        return this.userId;
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

    public boolean getPointsApplied() {
        return this.pointsApplied;
    }

    public void setPointsApplied(boolean value) {
        this.pointsApplied = value;
    }

    // to persist this boy
    @Override
    public String toJsonString() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String safeDescription = mapper.writeValueAsString(description);
        mapper.registerModule(new JavaTimeModule());
        return "{\"actionId\":%s,\"description\":%s,\"pointValue\":%s,\"userId\":%s,\"timestamp\":%s,\"pointsApplied\":%s}"
                .formatted(
                        actionId,
                        safeDescription,
                        pointValue,
                        userId,
                        mapper.writeValueAsString(timestamp),
                        pointsApplied);

    }

}
