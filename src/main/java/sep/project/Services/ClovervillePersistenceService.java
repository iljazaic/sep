package sep.project.Services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.CommunityTaskTemplateList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.GreenActionTemplateList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.CommunityTaskTemplate;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.AtomicModels.GreenActionTemplate;
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
     * Converts that thing to a string array and saves
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
     * Loads from persistence
     * 
     * @return the trade list
     */
    public static PointTradeList loadTradeList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/PointTradeList.json"));
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
        // in case something is wrong return new object
        return !list.isEmpty() ? list.get(0) : new CommunityGreenPoints(0, 0);
    }

    /**
     * Loads from persistence
     * 
     * @return the resident list object
     */
    public static ClovervilleResidentList loadClovervilleResidentList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/ClovervilleResidentList.json"));

        ClovervilleResidentList list = new ClovervilleResidentList();

        ArrayList<ClovervilleResident> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<ClovervilleResident>>() {
                });
        list.setResidentList(jsonList);
        return list;
    }

    public static GreenActionList loadClovervilleGreenActionList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/GreenActionList.json"));

        GreenActionList list = new GreenActionList();

        ArrayList<GreenAction> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<GreenAction>>() {
                });
        list.setGreenActionList(jsonList);
        return list;
    }

    public static CommunityTaskList loadCommunityTasks() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/CommunityTaskList.json"));

        CommunityTaskList list = new CommunityTaskList();

        ArrayList<CommunityTask> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<CommunityTask>>() {
                });
        list.setCommuntyTaskList(jsonList);
        return list;
    }

    public static CommunityTaskTemplateList loadCommunityTaskTemplates() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/CommunityTaskTemplateList.json"));

        CommunityTaskTemplateList list = new CommunityTaskTemplateList();

        ArrayList<CommunityTaskTemplate> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<CommunityTaskTemplate>>() {
                });
        list.setTemplateList(jsonList);
        return list;
    }

    public static GreenActionTemplateList loadGreenActionTemplates() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        File jsonFile = new File(pathToStorageDirectory.concat("/GreenActionTemplateList.json"));

        GreenActionTemplateList list = new GreenActionTemplateList();

        ArrayList<GreenActionTemplate> jsonList = mapper.readValue(jsonFile,
                new TypeReference<ArrayList<GreenActionTemplate>>() {
                });
        list.setTemplateList(jsonList);
        return list;
    }

    /**
     * general saving method makes the rest obsolete
     * saves and published the data to persistence and or website
     * 
     * 
     * @param listObject to save (must be aggregative class)
     *                   the class is cast into object to generalize across 5
     *                   classes
     * @throws Exception (to shut up the compiler)
     * 
     */
    public static void saveList(Object listObject) throws Exception {
        // i want to generalize. theres gonna be "raw types" so just ignore
        @SuppressWarnings("rawtypes")
        List<Class> allowedClasses = new ArrayList<>();
        allowedClasses.add(CommunityTaskList.class);
        allowedClasses.add(GreenActionList.class);
        allowedClasses.add(ClovervilleResidentList.class);
        allowedClasses.add(PointTradeList.class);
        allowedClasses.add(CommunityGreenPoints.class);
        allowedClasses.add(CommunityTaskTemplateList.class);
        allowedClasses.add(GreenActionTemplateList.class);
        if (allowedClasses.contains(listObject.getClass())) {
            // cast to the one and only
            String jsonList = ((JsonManager) listObject).toJsonString();
            writeStringToJsonFile(jsonList, "/%s.json".formatted(listObject.getClass().getSimpleName()));
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
// load community actions *
// save community actions *