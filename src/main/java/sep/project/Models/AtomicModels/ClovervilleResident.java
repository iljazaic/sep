package sep.project.Models.AtomicModels;

import java.util.UUID;

public class ClovervilleResident {
    private Long residentId;
    private String name;

    public ClovervilleResident(String name){
        this.name = name;
        this.residentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public void reportGreenAction(){}
    public Long getResidentId(){
        return residentId;
    }
    public String getName(){
        return name;
    }


    public String toJsonString(){
        return "{\"name\":\"%s\",\"residentId\":%s}".formatted(name, Long.toString(residentId));
    }
}
