package sep.project.Models.AggregativeModels;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.Interfaces.JsonManager;
import sep.project.Services.ClovervillePersistenceService;

public class GreenActionList implements JsonManager {
    private ArrayList<GreenAction> list;

    public GreenActionList() {
    };

    public void setGreenActionList(ArrayList<GreenAction> list) {
        this.list = list;
    }

    public ArrayList<GreenAction> getGreeActionList() {
        return this.list;
    }

    public void addGreenAction(GreenAction action) throws Exception {
        this.list.add(action);
        ClovervillePersistenceService.saveList(this);
    }

    public void removeAction(GreenAction action) {
        if (list.contains(action))
            list.remove(action);
    }

    public String getWeekLongActionListJson() throws Exception {
        if (this.list.isEmpty())
            return "[]";
        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (GreenAction action : this.list) {
            if (action.getTimestamp().isAfter(Instant.now().minus(7, ChronoUnit.DAYS))) {
                String dataString = action.toJsonString();
                jsonStringBuilder.append(dataString);

                if (list.indexOf(action) != list.size() - 1)
                    jsonStringBuilder.append(",\n");
                else {
                    jsonStringBuilder.append("\n]");
                }
            }
        }
        return jsonStringBuilder.toString();
    }

    @Override
    public String toJsonString() throws Exception {
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
