package sep.project.Models.AggregativeModels;

import java.util.ArrayList;

public abstract class ModelList {

    private ArrayList<Object> list;

    public abstract String toJsonString() throws Exception;

    public void addToList(Object toAdd) {
        this.list.add(toAdd);
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    public ArrayList<Object> getList() {
        return this.list;
    }
}
