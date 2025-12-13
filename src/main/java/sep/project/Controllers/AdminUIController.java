package sep.project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Optional;

import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.PointTrade;

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
    @FXML private TableView<ClovervilleResident> residentTable;
    @FXML private TableColumn<ClovervilleResident, Long> residentIdCol;
    @FXML private TableColumn<ClovervilleResident, String> residentNameCol;
    @FXML private TableColumn<ClovervilleResident, Integer> residentPointsCol;
    @FXML private TableView<GreenAction> greenActionTable;
    @FXML private TableColumn<GreenAction, Long> actionIdCol;
    @FXML private TableColumn<GreenAction, Long> actionUserIdCol;
    @FXML private TableColumn<GreenAction, String> actionDescCol;
    @FXML private TableColumn<GreenAction, Integer> actionPointsCol;
    @FXML private TableView<CommunityTask> taskTable;
    @FXML private TableColumn<CommunityTask, String> taskDescCol;
    @FXML private TableColumn<CommunityTask, Integer> taskRewardCol;
    @FXML private TableView<PointTrade> tradeTable;
    @FXML private TableColumn<PointTrade, Long> tradeIdCol;
    @FXML private TableColumn<PointTrade, String> tradeNameCol;
    @FXML private TableColumn<PointTrade, Long> tradeCreatorIdCol;
    @FXML private TableColumn<PointTrade, Integer> tradePointsCol;
    @FXML private TableColumn<PointTrade, String> tradeDescCol;
    @FXML private Label communityPointsLabel;
    @FXML private TextField newPointsField;
    @FXML private Label pointsStatusLabel;

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



    /**
     * init the controller
     */
    @FXML
    public void initialize() {
        simulateInitialData();
        
        residentIdCol.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        residentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        // NOTE: ClovervilleResident.java does NOT have a 'points' field, this requires a modification
        // For demonstration, we'll assume a method `getPoints()` exists or use a custom cell factory.
        // For now, we'll leave it as a placeholder.
        // residentPointsCol.setCellValueFactory(new PropertyValueFactory<>("points")); 
        residentTable.setItems(getObservableResidents());

        // --- Green Actions Table Setup ---
        actionIdCol.setCellValueFactory(new PropertyValueFactory<>("actionId"));
        actionUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        actionDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        actionPointsCol.setCellValueFactory(new PropertyValueFactory<>("pointValue"));
        greenActionTable.setItems(getObservableUnapprovedGreenActions());

        // --- Community Tasks Table Setup ---
        taskDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        taskRewardCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
        taskTable.setItems(getObservableTasks());

        // --- Point Trades Table Setup ---
        tradeIdCol.setCellValueFactory(new PropertyValueFactory<>("pointTradeId"));
        tradeNameCol.setCellValueFactory(new PropertyValueFactory<>("tradeName"));
        tradeCreatorIdCol.setCellValueFactory(new PropertyValueFactory<>("creatorResidentId"));
        tradePointsCol.setCellValueFactory(new PropertyValueFactory<>("pointAmount"));
        tradeDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        tradeTable.setItems(getObservableTrades());

        // --- Community Points Display ---
        updateCommunityPointsDisplay();
    }
    
    // --- Helper Methods to Prepare Data for TableViews ---

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
        // CommunityTaskList does not have a public getter for taskList besides the list passed in the constructor
        // Assuming you can get the list from the CommunityTaskList instance.
        // For now, returning an empty list for safety.
        return FXCollections.observableArrayList(); 
    }

    private ObservableList<PointTrade> getObservableTrades() {
        // PointTradeList's getPointTradeList returns ArrayList<?> which is unsafe.
        // Assuming a castable list is available from the model.
        // For now, returning an empty list for safety.
        return FXCollections.observableArrayList();
    }
    
    private void refreshAllTables() {
        residentTable.setItems(getObservableResidents());
        greenActionTable.setItems(getObservableUnapprovedGreenActions());
        taskTable.setItems(getObservableTasks());
        tradeTable.setItems(getObservableTrades());
        updateCommunityPointsDisplay();
    }

    // --- Resident Management Handlers ---

    @FXML
    private void handleCreateUser() {
        // Placeholder for dialog/form to get new resident details
        TextInputDialog dialog = new TextInputDialog("New Resident");
        dialog.setTitle("Create Resident");
        dialog.setHeaderText("Enter Name for New Resident");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            ClovervilleResident newResident = new ClovervilleResident(name);
            residentList.addResident(newResident);
            showAlert("Success", "Resident created: " + name);
            refreshAllTables();
        });
    }

    @FXML
    private void handleRemoveUser() {
        ClovervilleResident selected = residentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            residentList.remoevResident(selected); // Note: typo in your model 'remoevResident'
            showAlert("Success", "Resident removed: " + selected.getName());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a resident to remove.");
        }
    }
    
    @FXML
    private void handleEditPoints() {
        // Placeholder for opening a dialog to edit points
        showAlert("Placeholder", "Edit Resident Points dialog would open here.");
    }

    // --- Green Action Handlers ---

    @FXML
    private void handleApproveAction() {
        GreenAction selected = greenActionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setApproved(true);
            // TODO: Add logic to update the resident's points
            showAlert("Success", "Action Approved: " + selected.getDescription());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select an action to approve.");
        }
    }

    @FXML
    private void handleDenyAction() {
        GreenAction selected = greenActionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // In a real app, you might remove it from the unapproved list/database
            // Since GreenActionList doesn't have a remove method, this is a placeholder
            showAlert("Success", "Action Denied (Simulated Removal): " + selected.getDescription());
            // Need a way to remove from greenActionList or mark as rejected
            refreshAllTables(); 
        } else {
            showAlert("Error", "Please select an action to deny.");
        }
    }

    // --- Community Task Handlers ---

    @FXML
    private void handleCreateTask() {
        // Placeholder for dialog/form to create a new task
        showAlert("Placeholder", "Create Community Task dialog would open here.");
    }

    @FXML
    private void handleRemoveTask() {
        CommunityTask selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            communityTaskList.voidRemoveTaskList(selected);
            showAlert("Success", "Task removed.");
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a task to remove.");
        }
    }

    // --- Point Trade Handlers ---

    @FXML
    private void handleRemoveTrade() {
        PointTrade selected = tradeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // You will need to implement a removeTrade method in PointTradeList
            showAlert("Success", "Point Trade removed (Placeholder): " + selected.getTradeName());
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a trade to remove.");
        }
    }

    // --- Community Points Handlers ---

    private void updateCommunityPointsDisplay() {
        communityPointsLabel.setText(Integer.toString(communityPoints.getTotalPoints()));
    }

    @FXML
    private void handleSetCommunityPoints() {
        try {
            int newPoints = Integer.parseInt(newPointsField.getText());
            communityPoints.setTotalPoints(newPoints);
            updateCommunityPointsDisplay();
            pointsStatusLabel.setText("Successfully updated community points.");
        } catch (NumberFormatException e) {
            pointsStatusLabel.setText("Error: Please enter a valid number.");
        }
    }
    
    // --- Utility Alert Method ---
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // --- Temporary Data Population for Testing ---
    private void simulateInitialData() {
        // Residents
        ClovervilleResident alice = new ClovervilleResident("Alice Smith");
        ClovervilleResident bob = new ClovervilleResident("Bob Johnson");
        residentList.setResidentList(new ArrayList<>()); // Must initialize the list
        residentList.addResident(alice);
        residentList.addResident(bob);
        
        // Green Actions
        GreenAction action1 = new GreenAction("Recycled 10lbs of paper", 50, alice.getResidentId());
        GreenAction action2 = new GreenAction("Used reusable grocery bags for a week", 20, bob.getResidentId());
        action2.setApproved(true); // Approved action to test filtering
        GreenAction action3 = new GreenAction("Cleaned up local park", 75, alice.getResidentId());
        greenActionList.setGreenActionList(new ArrayList<>()); // Must initialize the list
        greenActionList.addGreenAction(action1);
        greenActionList.addGreenAction(action2);
        greenActionList.addGreenAction(action3);
        
        // Point Trades (Note: PointTradeList uses ArrayList<?> in getPointTradeList, making it hard to use here without a cast)
        // Ignoring trades for observable list for now to avoid runtime errors based on model definitions.
    }
}