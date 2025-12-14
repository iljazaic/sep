package sep.project.AdminGuiRender;

import javafx.application.Application;
import sep.project.Controllers.AdminUIController;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;


public class AdminUILauncher {


    //this basically forces the ui to use the static items that are only in the end assigned everything
    public static ClovervilleResidentList residentList;
    public static GreenActionList greenActionList;
    public static CommunityTaskList communityTaskList;
    public static PointTradeList tradeList;
    public static CommunityGreenPoints communityPoints;
    public static AdminUIController adminUIController;

    public static void startAdminUI(
            ClovervilleResidentList residentList,
            GreenActionList greenActionList,
            CommunityTaskList communityTaskList,
            PointTradeList tradeList,
            CommunityGreenPoints communityPoints,
            AdminUIController adminUIController) {

        AdminUILauncher.residentList = residentList;
        AdminUILauncher.greenActionList = greenActionList;
        AdminUILauncher.communityTaskList = communityTaskList;
        AdminUILauncher.tradeList = tradeList;
        AdminUILauncher.communityPoints = communityPoints;

        Application.launch(AdminUIApp.class);
    }
}