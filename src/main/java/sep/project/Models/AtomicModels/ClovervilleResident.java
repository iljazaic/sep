package sep.project.Models.AtomicModels;

import java.util.UUID;

public class ClovervilleResident {
    private Long residentId;
    private String name;
    private String email;
    private int personalPoints;

    public ClovervilleResident(String name, String email) {
        this.name = name;

        //bits - ignore the sloppy implementation
        this.residentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.personalPoints = 0;
        this.email = email;
    }

    // this is ONLY for persistence to use you hear me

    public ClovervilleResident() {
    };

    public ClovervilleResident(String name, Long residentId, String email, int personalPoints) {
        this.name = name;
        this.residentId = residentId;
        this.email = email;
        this.personalPoints = personalPoints;
    }

    public void reportGreenAction() {
    }

    public Long getResidentId() {
        return residentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPersonalPoints() {
        return this.personalPoints;
    }

    public String toJsonString() {
        return "{\"name\":\"%s\",\"residentId\":%s,\"email\":\"%s\",\"personalPoints\":%s}".formatted(name, Long.toString(residentId), email, Integer.toString(personalPoints));
    }
}
