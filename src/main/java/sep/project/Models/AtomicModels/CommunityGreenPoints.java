package sep.project.Models.AtomicModels;

import sep.project.Models.Interfaces.JsonManager;

public class CommunityGreenPoints implements JsonManager {
    private int totalPoints;

    public CommunityGreenPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }
    public CommunityGreenPoints(){};
    public int getTotalPoints(){
        return totalPoints;
    }

    public void setTotalPoints(int points){
        this.totalPoints = points;
    }

    @Override
    public String toJsonString(){
        return "[{\"totalPoints\":%s}]".formatted(Integer.toString(totalPoints));
    }
}
