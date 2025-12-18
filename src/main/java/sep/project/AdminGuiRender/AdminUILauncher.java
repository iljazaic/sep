package sep.project.AdminGuiRender;

import javafx.application.Application;
import sep.project.Controllers.AdminUIController;

public class AdminUILauncher {

    // this basically forces the ui to use the static items that are only in the end
    // assigned everything
    public static AdminUIController adminUIController;

    public static void startAdminUI(
            AdminUIController adminUIController) {

        AdminUILauncher.adminUIController = adminUIController;
        Application.launch(AdminUIApp.class);
    }
}