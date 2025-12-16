package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import javafx.scene.effect.Light.Point;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Models.Interfaces.JsonManager;

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

    /**
     * Builds a json string to save
     * 
     * @return json formatted string :
     *         formatted as array with objects
     * @throws Exception
     */
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
