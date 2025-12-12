package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityTask;

public class CommunityTaskList {
    private ArrayList<CommunityTask> taskList;

    public CommunityTaskList(ArrayList<CommunityTask> list) {
        this.taskList = list;
    }

    public String toJsonString() {
        if (this.taskList.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (CommunityTask task : this.taskList) {
            String dataString = task.toJsonString();
            jsonStringBuilder.append(dataString);

            if (taskList.indexOf(task) != taskList.size() - 1)
                jsonStringBuilder.append(",\n");
            else {
                jsonStringBuilder.append("\n]");
            }
        }
        return jsonStringBuilder.toString();
    };


    public void addCommunityTask(CommunityTask task){
        this.taskList.add(task);
    }


    public void voidRemoveTaskList(CommunityTask task){
        for (CommunityTask communityTask : taskList) {
            if(task.equals(communityTask)){
                taskList.remove(task);
            }
        }
    }



}
