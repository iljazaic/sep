package sep.project.Models.AtomicModels;

import sep.project.Models.Interfaces.JsonManager;

public class CommunityTask implements JsonManager {
    private String description;
    private int pointReward;

    public CommunityTask() {
    };

    public CommunityTask(String description, int pointReward) {
        this.description = description;
        this.pointReward = pointReward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }

    @Override
    public String toJsonString() {
        return "{\"description\":\"%s\",\"pointReward\":%s}".formatted(description, Integer.toString(pointReward));
    }

}
