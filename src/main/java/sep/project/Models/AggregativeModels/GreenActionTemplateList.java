package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.AtomicModels.GreenActionTemplate;
import sep.project.Models.Interfaces.JsonManager;

public class GreenActionTemplateList implements JsonManager {
    private ArrayList<GreenActionTemplate> list;

    public GreenActionTemplateList() {
    }

    public void setTemplateList(ArrayList<GreenActionTemplate> list) {
        this.list = list;
    }

    public void addGreenActionTemplate(GreenActionTemplate template) {
        this.list.add(template);
    }

    public void removeActionTemplate(GreenActionTemplate template) {
        if (list.contains(template))
            list.remove(template);
    }

    public ArrayList<GreenActionTemplate> getList(){
        return this.list;
    }

    @Override
    public String toJsonString() throws Exception {
        if (this.list.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (GreenActionTemplate action : this.list) {
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