package sep.project.Models.AtomicModels;

import sep.project.Models.Interfaces.JsonManager;

public class GreenActionTemplate implements JsonManager {
    private String category;
    private int pointReward;

    public GreenActionTemplate(String category, int pointReward) {
        this.category = category;
        this.pointReward = pointReward;
    }

    public GreenActionTemplate(){};

    public String getCategory() {
        return this.category;
    }

    public int getPointReward() {
        return this.pointReward;
    }

    @Override
    public String toJsonString() {
        return "{\"category\":\"%s\",\"pointReward\":%s}".formatted(this.category, Integer.toString(pointReward));
    }
}
