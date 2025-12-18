package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.Interfaces.JsonManager;

public class CommunityTaskList implements JsonManager {
    private ArrayList<CommunityTask> taskList;

    public CommunityTaskList(ArrayList<CommunityTask> list) {
        this.taskList = list;
    }

    public CommunityTaskList() {
    };

    public void addCommunityTask(CommunityTask task) {
        this.taskList.add(task);
    }

    public ArrayList<CommunityTask> getCommunityTasks() {
        return taskList;
    }

    public void setCommuntyTaskList(ArrayList<CommunityTask> list) {
        this.taskList = list;
    }

    public void removeTask(CommunityTask task) {
        for (CommunityTask communityTask : taskList) {
            if (task.equals(communityTask)) {
                taskList.remove(task);
            }
        }
    }

    @Override
    public String toJsonString() throws Exception {
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

}
