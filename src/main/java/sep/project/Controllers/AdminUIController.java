package sep.project.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Services.ClovervillePersistenceService;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

public class AdminUIController {
    private final ClovervilleResidentList residentList;
    private final GreenActionList greenActionList;
    private final CommunityTaskList communityTaskList;
    private final PointTradeList tradeList;
    private final CommunityGreenPoints communityPoints;
    @FXML
    private TableView<ClovervilleResident> residentTable;
    @FXML
    private TableColumn<ClovervilleResident, Long> residentIdCol;
    @FXML
    private TableColumn<ClovervilleResident, String> residentNameCol;
    @FXML
    private TableColumn<ClovervilleResident, String> residentEmailCol;
    @FXML
    private TableColumn<ClovervilleResident, Long> residentPhoneNumberCol;
    @FXML
    private TableColumn<ClovervilleResident, Integer> residentPointsCol;
    @FXML
    private TableView<GreenAction> greenActionTable;
    @FXML
    private TableColumn<GreenAction, Long> actionIdCol;
    @FXML
    private TableColumn<GreenAction, Long> actionUserIdCol;
    @FXML
    private TableColumn<GreenAction, String> actionDescCol;
    @FXML
    private TableColumn<GreenAction, Integer> actionPointsCol;
    @FXML
    private TableView<CommunityTask> taskTable;
    @FXML
    private TableColumn<CommunityTask, String> taskDescCol;
    @FXML
    private TableColumn<CommunityTask, Integer> taskRewardCol;
    @FXML
    private TableView<PointTrade> tradeTable;
    @FXML
    private TableColumn<PointTrade, Long> tradeIdCol;
    @FXML
    private TableColumn<PointTrade, String> tradeNameCol;
    @FXML
    private TableColumn<PointTrade, Long> tradeCreatorIdCol;
    @FXML
    private TableColumn<PointTrade, Integer> tradePointsCol;
    @FXML
    private TableColumn<PointTrade, String> tradeDescCol;
    @FXML
    private Label communityPointsLabel;
    @FXML
    private TextField newPointsField;
    @FXML
    private Label pointsStatusLabel;

    // i hate this project

    public AdminUIController(
            ClovervilleResidentList residentList,
            GreenActionList greenActionList,
            CommunityTaskList communityTaskList,
            PointTradeList tradeList,
            CommunityGreenPoints communityPoints) {

        this.residentList = residentList;
        this.greenActionList = greenActionList;
        this.communityTaskList = communityTaskList;
        this.tradeList = tradeList;
        this.communityPoints = communityPoints;
    }

    @FXML
    public void initialize() {

        residentIdCol.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        residentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        residentEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        residentPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        residentPointsCol.setCellValueFactory(new PropertyValueFactory<>("personalPoints"));
        residentTable.setItems(getObservableResidents());

        actionIdCol.setCellValueFactory(new PropertyValueFactory<>("actionId"));
        actionUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        actionDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        actionPointsCol.setCellValueFactory(new PropertyValueFactory<>("pointValue"));
        greenActionTable.setItems(getObservableUnapprovedGreenActions());

        taskDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        taskRewardCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
        taskTable.setItems(getObservableTasks());

        tradeIdCol.setCellValueFactory(new PropertyValueFactory<>("pointTradeId"));
        tradeNameCol.setCellValueFactory(new PropertyValueFactory<>("tradeName"));
        tradeCreatorIdCol.setCellValueFactory(new PropertyValueFactory<>("creatorResidentId"));
        tradePointsCol.setCellValueFactory(new PropertyValueFactory<>("pointAmount"));
        tradeDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        tradeTable.setItems(getObservableTrades());

        updateCommunityPointsDisplay();
    }

    // for table refreshes
    private ObservableList<ClovervilleResident> getObservableResidents() {
        if (residentList.getResidentList() == null) {
            residentList.setResidentList(new ArrayList<>());
        }
        return FXCollections.observableArrayList(residentList.getResidentList());
    }

    private ObservableList<GreenAction> getObservableUnapprovedGreenActions() {
        ArrayList<GreenAction> allActions = greenActionList.getGreeActionList();
        ArrayList<GreenAction> unapprovedActions = new ArrayList<>();
        if (allActions != null) {
            for (GreenAction action : allActions) {
                if (!action.getApproved()) {
                    unapprovedActions.add(action);
                }
            }
        }
        return FXCollections.observableArrayList(unapprovedActions);
    }

    private ObservableList<CommunityTask> getObservableTasks() {
        return FXCollections.observableArrayList(communityTaskList.getCommunityTasks());
    }

    private ObservableList<PointTrade> getObservableTrades() {
        return FXCollections.observableArrayList(tradeList.getList());
    }

    // for live feed
    private void refreshAllTables() {
        residentTable.setItems(getObservableResidents());
        greenActionTable.setItems(getObservableUnapprovedGreenActions());
        taskTable.setItems(getObservableTasks());
        tradeTable.setItems(getObservableTrades());
        updateCommunityPointsDisplay();
    }

    // here begin the handlers
    @FXML
    private void handleCreateUser() {
        Dialog<ClovervilleResident> dialog = new Dialog<>();
        dialog.setTitle("Create New Resident");
        dialog.setHeaderText("Enter details for the new Cloverville Resident:");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Resident Name (Required)");

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number (Digits only)");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Phone:"), 0, 2);
        grid.add(phoneField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            createButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                Long phoneNumberLong = null;
                String phoneText = phoneField.getText().trim();
                if (!phoneText.isEmpty()) {
                    try {
                        phoneNumberLong = Long.parseLong(phoneText.replaceAll("[^0-9]", ""));
                    } catch (NumberFormatException e) {
                        showAlert("Input Error",
                                "The Phone Number field must only have numbers");
                        return null;
                    }
                }

                return new ClovervilleResident(
                        nameField.getText(),
                        emailField.getText(),
                        phoneNumberLong);
            }
            return null;
        });

        Optional<ClovervilleResident> result = dialog.showAndWait();

        result.ifPresent(newResident -> {
            if (newResident == null) {
                return;
            }

            residentList.addResident(newResident);

            try {
                ClovervillePersistenceService.saveList(residentList);
            } catch (Exception e) {
                showAlert("Error", "Failed to save resident list: " + e.getMessage());
                e.printStackTrace();
                residentList.removeResident(newResident);
                return;
            }
            showAlert("Success", "Resident created: " + newResident.getName()
                    + "\nEmail: " + newResident.getEmail()
                    + "\nPhone: " + (newResident.getPhoneNumber() != null ? newResident.getPhoneNumber() : "N/A"));
            refreshAllTables();
        });
    }

    // i assume the garbage collector will take care of anything that happens to
    // "selected" afterwards
    // if not then we got a memory leak but who cares its a 1st sem project lol
    @FXML
    private void handleRemoveUser() throws Exception {
        ClovervilleResident selected = residentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            residentList.removeResident(selected);
            showAlert("Success", "Resident removed: " + selected.getName());
            ClovervillePersistenceService.saveList(residentList);
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a resident to remove.");
        }
    }

    @FXML
    private void handleEditPoints() {
        // TODO: fix
        showAlert("Placeholder", "Edit Resident Points dialog would open here.");
    }

    @FXML
    private void handleApproveAction() throws Exception {
        GreenAction selected = greenActionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setApproved(true);
            Long userId = selected.getUserId();
            residentList.editResidentPersonalPoints(userId, selected.getPointValue());
            ClovervillePersistenceService.saveList(greenActionList);
            showAlert("Success", "Action Approved: " + selected.getDescription());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select an action to approve.");
        }
    }

    // tested - works
    @FXML
    private void handleDenyAction() throws Exception {
        GreenAction selected = greenActionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            greenActionList.removeAction(selected);
            ClovervillePersistenceService.saveList(greenActionList);
            showAlert("Success", "Action Denied And Removed " + selected.getDescription());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select an action to deny.");
        }
    }

    @FXML
    private void handleCreateTask() {
        showAlert("Placeholder", "Create Community Task dialog would open here.");
    }

    @FXML
    private void handleRemoveTask() throws Exception {
        CommunityTask selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            communityTaskList.voidRemoveTaskList(selected);
            ClovervillePersistenceService.saveList(communityTaskList);
            showAlert("Success", "Task removed.");
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a task to remove.");
        }
    }

    @FXML
    private void handleRemoveTrade() throws Exception {
        PointTrade selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tradeList.removeTrade(selected);
            ClovervillePersistenceService.saveList(tradeList);
            showAlert("Success", "Point Trade removed: " + selected.getTradeName());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a trade to remove.");
        }
    }

    private void updateCommunityPointsDisplay() {
        communityPointsLabel.setText(Integer.toString(communityPoints.getTotalPoints()));
    }

    @FXML
    private void handleSetCommunityPoints() throws Exception {
        try {
            int newPoints = Integer.parseInt(newPointsField.getText());
            communityPoints.setTotalPoints(newPoints);
            ClovervillePersistenceService.saveList(communityPoints);
            updateCommunityPointsDisplay();
            pointsStatusLabel.setText("Successfully updated community points.");
        } catch (NumberFormatException e) {
            pointsStatusLabel.setText("Error: Please enter a valid number.");
        }
    }



    //for publishing - basically building persistence
    @FXML
    private void handlePublishTrades() throws Exception {
        ClovervillePersistenceService.saveList(tradeList);
    }

    @FXML
    private void handlePublishGreenActions() throws Exception{
        ClovervillePersistenceService.saveList(greenActionList);
    }

    @FXML
    private void handlePublishCommunityPool() throws Exception{
        ClovervillePersistenceService.saveList(communityPoints);
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Button navResidents, navActivities, navTrade, navReset, navPublish;

    @FXML
    private void switchToResidents() {
        mainTabPane.getSelectionModel().select(0);
        updateNavButtonStyles(navResidents);
    }

    @FXML
    private void switchToActivities() {
        mainTabPane.getSelectionModel().select(1);
        updateNavButtonStyles(navActivities);
    }

    @FXML
    private void switchToTrade() {
        mainTabPane.getSelectionModel().select(2);
        updateNavButtonStyles(navTrade);
    }

    @FXML
    private void switchToReset() {
        mainTabPane.getSelectionModel().select(3);
        updateNavButtonStyles(navReset);
    }

    @FXML
    private void switchToPublish() {
        mainTabPane.getSelectionModel().select(4);
        updateNavButtonStyles(navPublish);
    }

    private void updateNavButtonStyles(Button activeButton) {
        String inactiveStyle = "-fx-background-color: #2e8b57; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12 24; -fx-font-size: 14px; -fx-background-radius: 0; -fx-border-width: 0;";
        String activeStyle = "-fx-background-color: #55b740; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12 24; -fx-font-size: 14px; -fx-background-radius: 0; -fx-border-width: 0;";

        navResidents.setStyle(inactiveStyle);
        navActivities.setStyle(inactiveStyle);
        navTrade.setStyle(inactiveStyle);
        navReset.setStyle(inactiveStyle);
        navPublish.setStyle(inactiveStyle);

        activeButton.setStyle(activeStyle);
    }

}