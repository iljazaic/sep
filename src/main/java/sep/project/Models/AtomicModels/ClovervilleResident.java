package sep.project.Models.AtomicModels;

import java.util.UUID;

import sep.project.Models.Interfaces.JsonManager;

public class ClovervilleResident implements JsonManager {
    private Long residentId;
    private String name;
    private String email;
    private int personalPoints;
    private Long phoneNumber;

    public ClovervilleResident(String name, String email, Long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        // bits - ignore the sloppy implementation
        this.residentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.personalPoints = 0;
        this.email = email;
    }

    // these two are ONLY for persistence to use you hear me
    public ClovervilleResident() {
    };

    public ClovervilleResident(String name, Long residentId, String email, int personalPoints, Long phoneNumber) {
        this.name = name;
        this.residentId = residentId;
        this.email = email;
        this.personalPoints = personalPoints;
        this.phoneNumber=phoneNumber;
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

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getPersonalPoints() {
        return this.personalPoints;
    }

    public void editPersonalPoints(int delta){
        this.personalPoints+=delta;
    }

    @Override
    public String toJsonString() {
        return "{\"name\":\"%s\",\"residentId\":%s,\"email\":\"%s\",\"personalPoints\":%s,\"phoneNumber\":%s}"
                .formatted(name,
                        Long.toString(residentId), email, Integer.toString(personalPoints), Long.toString(phoneNumber));
    }
}
