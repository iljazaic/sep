package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

import sep.project.Models.AtomicModels.ClovervilleResident;

public class ClovervilleResidentList {
    private ArrayList<ClovervilleResident> residentList;

    public ClovervilleResidentList() {
    };

    public void setResidentList(ArrayList<ClovervilleResident> list) {
        this.residentList = list;
    }

    public ArrayList<ClovervilleResident> getResidentList() {
        return residentList;
    }

    public ClovervilleResident getClovervilleResidentById(Long Id) {
        for (ClovervilleResident clovervilleResident : residentList) {
            if (clovervilleResident.getResidentId() == Id) {
                return clovervilleResident;
            }
        }
        return null;
    }

    public void addResident(ClovervilleResident resident) {
        residentList.add(resident);
        setResidentList(residentList);
    }

    public void remoevResident(ClovervilleResident resident) {
        if (residentList.contains(resident))
            residentList.remove(resident);
    }

    public String toJsonString() {
        if (this.residentList.isEmpty())
            return "[]";

        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[\n");
        for (ClovervilleResident resident : this.residentList) {
            String dataString = resident.toJsonString();
            jsonStringBuilder.append(dataString);

            if (residentList.indexOf(resident) != residentList.size() - 1)
                jsonStringBuilder.append(",\n");
            else {
                jsonStringBuilder.append("\n]");
            }
        }
        return jsonStringBuilder.toString();
    }

    public ClovervilleResident getClovervilleResidentByName(String name) {
        for (ClovervilleResident clovervilleResident : residentList) {
            if (clovervilleResident.getName().equals(name)) {
                return clovervilleResident;
            }
        }
        return null;
    }
}
