package sep.project.Models.Interfaces;

public interface JsonManager {
    /**
     * Builds a json string to save
     * 
     * @return json formatted string :
     *         formatted as array with objects
     * @throws Exception
     */
    String toJsonString() throws Exception;
}