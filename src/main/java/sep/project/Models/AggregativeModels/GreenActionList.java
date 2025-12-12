package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.GreenAction;

public class GreenActionList {
    private ArrayList<GreenAction> list;

    public GreenActionList() {
    };

    public void setGreenActionList(ArrayList<GreenAction> list) {
        this.list = list;
    }

    public ArrayList<GreenAction> getGreeActionList() {
        return this.list;
    }


    public void addGreenAction(GreenAction action){
        this.list.add(action);
        
    }



    public String toJsonString() {
        if (this.list.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (GreenAction action : this.list) {
            String dataString = action.toJsonString();
            jsonStringBuilder.append(dataString);

            if (list.indexOf(action) != list.size() - 1)
                jsonStringBuilder.append(",\n");
            else {
                jsonStringBuilder.append("\n]");
            }
        }
        return jsonStringBuilder.toString();
    }
}
