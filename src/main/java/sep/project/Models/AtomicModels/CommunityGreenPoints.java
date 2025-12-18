package sep.project.Models.AtomicModels;

import sep.project.Models.Interfaces.JsonManager;

public class CommunityGreenPoints implements JsonManager {
    private int totalPoints;
    private int pointMilestone;

    public CommunityGreenPoints(int totalPoints, int pointMilestone) {
        this.totalPoints = totalPoints;
        this.pointMilestone = pointMilestone;
    }

    public CommunityGreenPoints() {
    };

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPointMilestone() {
        return this.pointMilestone;
    }

    public void setTotalPoints(int points) {
        this.totalPoints = points;
    }

    public void setPointMilestone(int milestone) {
        this.pointMilestone = milestone;
    }

    // takes care of the array issue.
    // lets me use the one size fits all approach to loading it too
    @Override
    public String toJsonString() {
        return "[{\"totalPoints\":%s,\"pointMilestone\":%s}]".formatted(Integer.toString(totalPoints),
                Integer.toString(pointMilestone));
    }
}
