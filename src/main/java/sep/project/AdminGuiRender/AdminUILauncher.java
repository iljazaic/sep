package sep.project.AdminGuiRender; // Create a package for UI launching/hosting

import javafx.application.Application;

// Import your model lists
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

/**
 * Utility class to launch the JavaFX Admin UI from an external main method
 * and pass the persistent model instances.
 */
public class AdminUILauncher {

    // Static fields to temporarily hold the persistent data during launch
    // This is the common pattern used to pass data across the static launch() call.
    public static ClovervilleResidentList residentList;
    public static GreenActionList greenActionList;
    public static CommunityTaskList communityTaskList;
    public static PointTradeList tradeList;
    public static CommunityGreenPoints communityPoints;

    /**
     * The method your main application will call to start the Admin UI.
     * * @param residentList The single instance of ClovervilleResidentList.
     * @param greenActionList The single instance of GreenActionList.
     * @param communityTaskList The single instance of CommunityTaskList.
     * @param tradeList The single instance of PointTradeList.
     * @param communityPoints The single instance of CommunityGreenPoints.
     */
    public static void startAdminUI(
            ClovervilleResidentList residentList,
            GreenActionList greenActionList,
            CommunityTaskList communityTaskList,
            PointTradeList tradeList,
            CommunityGreenPoints communityPoints) {

        // 1. Store the persistent instances in the static fields
        AdminUILauncher.residentList = residentList;
        AdminUILauncher.greenActionList = greenActionList;
        AdminUILauncher.communityTaskList = communityTaskList;
        AdminUILauncher.tradeList = tradeList;
        AdminUILauncher.communityPoints = communityPoints;

        // 2. Launch the JavaFX Application
        // Pass the AdminUI class to be instantiated by the JavaFX runtime
        Application.launch(AdminUIApp.class);
    }
}