package sep.project.Models.AtomicModels;

import java.util.UUID;

public class GreenAction {
    private Long actionId;
    private String description;
    private int pointValue;


    public GreenAction(String desc, int value){
        this.pointValue = value;
        this.description = desc;
        this.actionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
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
