package sep.project.Models.AtomicModels;

import java.util.UUID;

public class Administrator {
    private Long id;
    private String name;
    private boolean loggedIn;

    public Administrator(String name){
        this.name = name;
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.loggedIn = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void addGreenAction(GreenAction action){

    }

    public void editGreenAction(){
        
    }
}