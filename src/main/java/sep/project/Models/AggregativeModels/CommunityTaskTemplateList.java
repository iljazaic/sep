package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.CommunityTaskTemplate;
import sep.project.Models.Interfaces.JsonManager;

public class CommunityTaskTemplateList implements JsonManager {
    private ArrayList<CommunityTaskTemplate> list;

    public CommunityTaskTemplateList() {
    }

    public void setTemplateList(ArrayList<CommunityTaskTemplate> list) {
        this.list = list;
    }

    public void addCommunityTaskTemplate(CommunityTaskTemplate template) {
        this.list.add(template);
    }

    public void removeActionTemplate(CommunityTaskTemplate template) {
        if (list.contains(template))
            list.remove(template);
    }

    public ArrayList<CommunityTaskTemplate> getList(){
        return this.list;
    }

    @Override
    public String toJsonString() throws Exception {
        if (this.list.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (CommunityTaskTemplate action : this.list) {
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