package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Models.Interfaces.JsonManager;

public class PointTradeList implements JsonManager {
    private ArrayList<PointTrade> trades;

    public PointTradeList() {
        this.trades = new ArrayList<PointTrade>();
    }

    public void populateArrayFromPersistence(ArrayList<PointTrade> listFromStorage) {
        this.trades = listFromStorage;
    }

    public ArrayList<?> getPointTradeList() {
        return this.trades;
    }

    public void executePointTrade(Long secondResidentId) {
        // ClovervilleResident ownerResident =
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
        if (this.trades.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (PointTrade pointTrade : trades) {
            String dataString = pointTrade.toJsonString();
            jsonStringBuilder.append(dataString);

            if (trades.indexOf(pointTrade) != trades.size() - 1)
                jsonStringBuilder.append(",\n");
            else {
                jsonStringBuilder.append("\n]");
            }
        }
        return jsonStringBuilder.toString();
    }
}
