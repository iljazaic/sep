package sep.project.Services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Models.Interfaces.JsonManager;

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

    public static GreenActionList loadClovervilleGreenActionList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/GreenActions.json"));

        GreenActionList list = new GreenActionList();

        ArrayList<GreenAction> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<GreenAction>>() {
                });
        list.setGreenActionList(jsonList);
        return list;
    }

    public static CommunityTaskList loadCommunityTasks() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/CommunityTasks.json"));

        CommunityTaskList list = new CommunityTaskList();

        ArrayList<CommunityTask> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<CommunityTask>>() {
                });
        list.setCommuntyTaskList(jsonList);
        return list;
    }

    /**
     * general saving method makes the rest obsolete
     * @param listObject to save (must be aggregative class)
     * @throws Exception (to shut up the jvm)
     * 
     */
    public static void saveList(Object listObject) throws Exception {
        List<Class> allowedClasses = new ArrayList<>();
        allowedClasses.add(CommunityTaskList.class);
        allowedClasses.add(GreenActionList.class);
        allowedClasses.add(ClovervilleResidentList.class);
        allowedClasses.add(PointTradeList.class);
        allowedClasses.add(CommunityGreenPoints.class);
        if (allowedClasses.contains(listObject.getClass())) {
            String jsonList = ((JsonManager) listObject).toJsonString();
            writeStringToJsonFile(jsonList, "/%s.json".formatted(listObject.getClass().getName()));
        } else {
            return;
        }
    }


    
}

// load residents *
// save residents *
// load trades *
// save trades *
// load points*
// save points *
// load greenactions *
// save greenactions *
// load community actions
// save community actions