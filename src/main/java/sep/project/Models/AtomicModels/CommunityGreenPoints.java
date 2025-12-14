package sep.project.Models.AtomicModels;

public class CommunityGreenPoints {
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

    public String toJsonString(){
        return "[{\"totalPoints\":%s}]".formatted(Integer.toString(totalPoints));
    }
}
