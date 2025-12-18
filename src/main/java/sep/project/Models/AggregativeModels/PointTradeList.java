package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import javafx.scene.effect.Light.Point;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Models.Interfaces.JsonManager;
import sep.project.Services.ClovervillePersistenceService;

public class PointTradeList implements JsonManager {
    private ArrayList<PointTrade> list;

    public PointTradeList() {
        this.list = new ArrayList<PointTrade>();
    }

    public void populateArrayFromPersistence(ArrayList<PointTrade> listFromStorage) {
        this.list = listFromStorage;
    }

    public void executePointTrade(Long secondResidentId) {
        // ClovervilleResident ownerResident =
    }

    public void removeTrade(PointTrade trade) {
        if (list.contains(trade))
            list.remove(trade);
    }

    public ArrayList<PointTrade> getList() {
        return this.list;
    }

    public void addTrade(PointTrade trade){
        this.list.add(trade);
    }

    @Override
    public String toJsonString() throws Exception {
        if (this.list.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (PointTrade pointTrade : list) {
            String dataString = pointTrade.toJsonString();
            jsonStringBuilder.append(dataString);

            if (list.indexOf(pointTrade) != list.size() - 1)
                jsonStringBuilder.append(",\n");
            else {
                jsonStringBuilder.append("\n]");
            }
        }
        return jsonStringBuilder.toString();
    }
}
