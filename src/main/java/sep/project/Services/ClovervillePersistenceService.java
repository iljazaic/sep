package sep.project.Services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.PointTrade;

/**
 * Contains logic for loading shit from persistence; should be called on app
 * startup;
 * IS STATIC
 * IS COMPLETE
 */
public class ClovervillePersistenceService {
    public ClovervillePersistenceService() {
    };

    private final static String pathToStorageDirectory = "./lib/storage";

    /**
     * Converts that shit to a string array and saves
     * 
     * @param value
     * @param filename
     * @throws Exception
     */
    private static void writeStringToJsonFile(String value, String filename) throws Exception {
        Path path = Path.of(pathToStorageDirectory.concat("/".concat(filename)));
        Files.write(
                path,
                value.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void saveTradeList(PointTradeList list) throws Exception {
        String jsonList = list.toJsonString();
        writeStringToJsonFile(jsonList, "Trades.json");
    }

    /**
     * Converts that shit to a string array and saves
     * 
     * @param points
     * @throws Exception
     */
    public static void saveCommunityPoints(CommunityGreenPoints points) throws Exception {
        String jsonList = points.toJsonString();
        writeStringToJsonFile(jsonList, "CommunityGreenPoints.json");
    }

    /**
     * Converts that shit to a string array and saves
     * 
     * @param residents
     * @throws Exception
     */
    public static void saveClovervilleResidentList(ClovervilleResidentList residents) throws Exception {
        String jsonList = residents.toJsonString();
        writeStringToJsonFile(jsonList, "Residents.json");
    }

    /**
     * Loads from persistence
     * 
     * @return the trade list
     */
    public static PointTradeList loadTradeList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/Trades.json"));
        PointTradeList list = new PointTradeList();
        list.populateArrayFromPersistence(mapper.readValue(jsonFile, new TypeReference<ArrayList<PointTrade>>() {
        }));
        return list;
    }

    /**
     * Loads from persistence
     * 
     * @return the points object
     */
    public static CommunityGreenPoints loadCommunityGreenPoints() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/CommunityGreenPoints.json"));
        ArrayList<CommunityGreenPoints> list = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<CommunityGreenPoints>>() {
                });
        return !list.isEmpty() ? list.get(0) : new CommunityGreenPoints(0);
    }

    /**
     * Loads from persistence
     * 
     * @return the resident list object
     */
    public static ClovervilleResidentList loadClovervilleResidentList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/Residents.json"));

        ClovervilleResidentList list = new ClovervilleResidentList();

        ArrayList<ClovervilleResident> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<ClovervilleResident>>() {
                });
        list.setResidentList(jsonList);
        return list;
    }

}

// load residents *
// save residents *
// load trades *
// save trades *
// load points*
// save points *