package sep.project.Models.AtomicModels;

public class CommunityTask {
    private String description;
    private int pointReward;

    public CommunityTask(){};


    
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

    public String toJsonString(){
        return "Community Task";
    }
    
}
