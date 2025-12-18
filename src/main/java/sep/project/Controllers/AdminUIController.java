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
import sep.project.Models.AtomicModels.GreenActionTemplate;
import sep.project.Models.AtomicModels.CommunityTask;
import sep.project.Models.AtomicModels.CommunityTaskTemplate;
import sep.project.Models.AtomicModels.PointTrade;
import sep.project.Services.ClovervillePersistenceService;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.GreenActionTemplateList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.CommunityTaskTemplateList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

public class AdminUIController {
    private final ClovervilleResidentList residentList;
    private final GreenActionList greenActionList;
    private final CommunityTaskList communityTaskList;
    private final PointTradeList tradeList;
    private final CommunityGreenPoints communityPoints;
    private final GreenActionTemplateList greenActionTemplateList;
    private final CommunityTaskTemplateList communityTaskTemplateList;
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
    private TableColumn<PointTrade, String> tradeCreatorNameCol;
    @FXML
    private TableColumn<PointTrade, Integer> tradePointsCol;
    @FXML
    private TableColumn<PointTrade, String> tradeDescCol;
    @FXML
    private TableView<GreenActionTemplate> actionTemplateTable;
    @FXML
    private TableColumn<GreenActionTemplate, String> actionTemplateDescCol;
    @FXML
    private TableColumn<GreenActionTemplate, Integer> actionTemplateValCol;
    @FXML
    private TableView<CommunityTaskTemplate> taskTemplateTable;
    @FXML
    private TableColumn<GreenActionTemplate, String> taskTemplateDescCol;
    @FXML
    private TableColumn<GreenActionTemplate, Integer> taskTemplateValCol;
    @FXML
    private Label communityPointsLabel;
    @FXML
    private TextField newPointsField;
    @FXML
    private Label pointsStatusLabel;
    @FXML
    private TextField milestoneField;
    // i hate this project

    public AdminUIController(
            ClovervilleResidentList residentList,
            GreenActionList greenActionList,
            CommunityTaskList communityTaskList,
            PointTradeList tradeList,
            CommunityGreenPoints communityPoints,
            GreenActionTemplateList greenActionTemplateList,
            CommunityTaskTemplateList communityTaskTemplateList) {

        this.residentList = residentList;
        this.greenActionList = greenActionList;
        this.communityTaskList = communityTaskList;
        this.tradeList = tradeList;
        this.communityPoints = communityPoints;
        this.greenActionTemplateList = greenActionTemplateList;
        this.communityTaskTemplateList = communityTaskTemplateList;
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
        greenActionTable.setItems(getObservableGreenActions());

        taskDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        taskRewardCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
        taskTable.setItems(getObservableTasks());

        actionTemplateDescCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        actionTemplateValCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
        actionTemplateTable.setItems(getObservableGreenActionTemplates());

        taskTemplateDescCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        taskTemplateValCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
        taskTemplateTable.setItems(getObservableCommunityTaskTemplates());

        tradeIdCol.setCellValueFactory(new PropertyValueFactory<>("pointTradeId"));
        tradeNameCol.setCellValueFactory(new PropertyValueFactory<>("tradeName"));
        tradeCreatorNameCol.setCellValueFactory(new PropertyValueFactory<>("creatroResidentName"));
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

    private ObservableList<GreenAction> getObservableGreenActions() {
        ArrayList<GreenAction> allActions = greenActionList.getGreeActionList();
        return FXCollections.observableArrayList(allActions);
    }

    private ObservableList<CommunityTask> getObservableTasks() {
        return FXCollections.observableArrayList(communityTaskList.getCommunityTasks());
    }

    private ObservableList<PointTrade> getObservableTrades() {
        return FXCollections.observableArrayList(tradeList.getList());
    }

    private ObservableList<CommunityTaskTemplate> getObservableCommunityTaskTemplates() {
        return FXCollections.observableArrayList(communityTaskTemplateList.getList());
    }

    private ObservableList<GreenActionTemplate> getObservableGreenActionTemplates() {
        return FXCollections.observableArrayList(greenActionTemplateList.getList());
    }

    // for live feed
    private void refreshAllTables() {
        residentTable.setItems(getObservableResidents());
        greenActionTable.setItems(getObservableGreenActions());
        taskTable.setItems(getObservableTasks());
        tradeTable.setItems(getObservableTrades());
        taskTemplateTable.setItems(getObservableCommunityTaskTemplates());
        actionTemplateTable.setItems(getObservableGreenActionTemplates());
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
        String input = newPointsField.getText();
        try {
            int a = Integer.parseInt(input);
            communityPoints.setTotalPoints(a);
        } catch (Exception e) {
            showAlert("Input error", "Please input a valid number!");
        }
    }

    @FXML
    private void setPointMilestone() {
        String input = milestoneField.getText();
        try {
            int a = Integer.parseInt(input);
            communityPoints.setPointMilestone(a);
        } catch (Exception e) {
            showAlert("Input error", "Please input a valid number!");
        }
    }

    @FXML
    private void handleCreateActionTemplate() throws Exception {
        Dialog<GreenActionTemplate> dialog = new Dialog<>();
        dialog.setTitle("Create Green Action Template");
        dialog.setHeaderText("Enter details for the new template:");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField descField = new TextField();
        descField.setPromptText("Description (e.g. Recycling)");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Points (e.g. 50)");

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Points:"), 0, 1);
        grid.add(pointsField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);
        descField.textProperty().addListener((obs, old, newVal) -> createButton.setDisable(newVal.trim().isEmpty()));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    int points = Integer.parseInt(pointsField.getText().trim());
                    return new GreenActionTemplate(descField.getText().trim(), points);
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "Points must be a number.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(action -> {
            greenActionTemplateList.addGreenActionTemplate(action);
            try {
                ClovervillePersistenceService.saveList(greenActionTemplateList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshAllTables();
        });
    }

    @FXML
    private void handleCreateTaskTemplate() throws Exception {
        Dialog<CommunityTaskTemplate> dialog = new Dialog<>();
        dialog.setTitle("Create Green Action Template");
        dialog.setHeaderText("Enter details for the new template:");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField descField = new TextField();
        descField.setPromptText("Description (e.g. Recycling)");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Points (e.g. 50)");

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Points:"), 0, 1);
        grid.add(pointsField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);
        descField.textProperty().addListener((obs, old, newVal) -> createButton.setDisable(newVal.trim().isEmpty()));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    int points = Integer.parseInt(pointsField.getText().trim());
                    return new CommunityTaskTemplate(descField.getText().trim(), points);
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "Points must be a number.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(action -> {
            communityTaskTemplateList.addCommunityTaskTemplate(action);
            try {
                ClovervillePersistenceService.saveList(communityTaskTemplateList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshAllTables();
        });
    }

    @FXML
    private void handleCreateTask() {
        Dialog<CommunityTask> dialog = new Dialog<>();
        dialog.setTitle("Create Community Task");
        dialog.setHeaderText("Enter details for the new task:");
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // ComboBox for templates
        ComboBox<CommunityTaskTemplate> templateComboBox = new ComboBox<>();
        templateComboBox.setPromptText("Select a template (optional)");
        templateComboBox.getItems().addAll(communityTaskTemplateList.getList());

        // Custom cell factory to display template info nicely
        templateComboBox.setCellFactory(param -> new ListCell<CommunityTaskTemplate>() {
            @Override
            protected void updateItem(CommunityTaskTemplate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategory() + " (" + item.getPointReward() + " points)");
                }
            }
        });
        templateComboBox.setButtonCell(new ListCell<CommunityTaskTemplate>() {
            @Override
            protected void updateItem(CommunityTaskTemplate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategory() + " (" + item.getPointReward() + " points)");
                }
            }
        });

        TextField descField = new TextField();
        descField.setPromptText("Description");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Point reward");

        // Listener to auto-fill fields when template is selected
        templateComboBox.setOnAction(e -> {
            CommunityTaskTemplate selected = templateComboBox.getValue();
            if (selected != null) {
                descField.setText(selected.getCategory());
                pointsField.setText(String.valueOf(selected.getPointReward()));
            }
        });

        grid.add(new Label("Template:"), 0, 0);
        grid.add(templateComboBox, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descField, 1, 1);
        grid.add(new Label("Points:"), 0, 2);
        grid.add(pointsField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);

        descField.textProperty().addListener((obs, old, newVal) -> createButton.setDisable(newVal.trim().isEmpty()));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    int points = Integer.parseInt(pointsField.getText().trim());
                    return new CommunityTask(descField.getText(), points);
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "Points must be numerical.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(action -> {
            communityTaskList.addCommunityTask(action);
            refreshAllTables();
        });
    }

    @FXML
    private void handleCreateAction() throws Exception {
        Dialog<GreenAction> dialog = new Dialog<>();
        dialog.setTitle("Create Green Action");
        dialog.setHeaderText("Enter details for the new action:");
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // ComboBox for templates
        ComboBox<GreenActionTemplate> templateComboBox = new ComboBox<>();
        templateComboBox.setPromptText("Select a template (optional)");
        templateComboBox.getItems().addAll(greenActionTemplateList.getList()); // You'll need to add a getList() method

        // Custom cell factory to display template info nicely
        templateComboBox.setCellFactory(param -> new ListCell<GreenActionTemplate>() {
            @Override
            protected void updateItem(GreenActionTemplate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategory() + " (" + item.getPointReward() + " points)");
                }
            }
        });
        templateComboBox.setButtonCell(new ListCell<GreenActionTemplate>() {
            @Override
            protected void updateItem(GreenActionTemplate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategory() + " (" + item.getPointReward() + " points)");
                }
            }
        });

        TextField descField = new TextField();
        descField.setPromptText("Description (e.g. Recycling)");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Points (e.g. 50)");
        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID (e.g. 101)");

        // Listener to auto-fill fields when template is selected
        templateComboBox.setOnAction(e -> {
            GreenActionTemplate selected = templateComboBox.getValue();
            if (selected != null) {
                descField.setText(selected.getCategory());
                pointsField.setText(String.valueOf(selected.getPointReward()));
            }
        });

        grid.add(new Label("Template:"), 0, 0);
        grid.add(templateComboBox, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descField, 1, 1);
        grid.add(new Label("Points:"), 0, 2);
        grid.add(pointsField, 1, 2);
        grid.add(new Label("User ID:"), 0, 3);
        grid.add(userIdField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);

        // Enable button only when description and userId are filled
        descField.textProperty().addListener((obs, old, newVal) -> createButton
                .setDisable(newVal.trim().isEmpty() || userIdField.getText().trim().isEmpty()));
        userIdField.textProperty().addListener((obs, old, newVal) -> createButton
                .setDisable(newVal.trim().isEmpty() || descField.getText().trim().isEmpty()));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    int points = Integer.parseInt(pointsField.getText().trim());
                    Long userId = Long.parseLong(userIdField.getText().trim());
                    return new GreenAction(descField.getText().trim(), points, userId);
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "Points and User ID must be numerical.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(action -> {
            greenActionList.addGreenAction(action);
            refreshAllTables();
        });
    }

    @FXML
    private void handleCreateTrade() {
        Dialog<PointTrade> dialog = new Dialog<>();
        dialog.setTitle("Create Point Trade");
        dialog.setHeaderText("Specify the terms of the point trade:");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Trade Name (e.g. Garden Help)");
        TextField pointsField = new TextField();
        pointsField.setPromptText("Point Amount");
        TextField creatorField = new TextField();
        creatorField.setPromptText("Resident Name");
        TextArea descArea = new TextArea();
        descArea.setPromptText("Terms and conditions...");
        descArea.setPrefRowCount(3);

        grid.add(new Label("Trade Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Points:"), 0, 1);
        grid.add(pointsField, 1, 1);
        grid.add(new Label("Creator:"), 0, 2);
        grid.add(creatorField, 1, 2);
        grid.add(new Label("Description:"), 0, 3);
        grid.add(descArea, 1, 3);

        dialog.getDialogPane().setContent(grid);

        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);
        nameField.textProperty().addListener((obs, old, newVal) -> createButton.setDisable(newVal.trim().isEmpty()));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    int points = Integer.parseInt(pointsField.getText().trim());
                    return new PointTrade(
                            nameField.getText().trim(),
                            points,
                            descArea.getText().trim(),
                            creatorField.getText().trim());
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "The Point Amount must be a number.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(trade -> {

            tradeList.addTrade(trade);
            refreshAllTables();
        });
    }

    @FXML
    private void publishCommunityTasksAndActions() throws Exception {
        ClovervillePersistenceService.saveList(greenActionList);
        ClovervillePersistenceService.saveList(communityTaskList);
    }

    @FXML
    private void publishCommunityPoints() throws Exception {
        ClovervillePersistenceService.saveList(communityPoints);
    }

    @FXML
    private void publishTradeOffers() throws Exception {
        ClovervillePersistenceService.saveList(tradeList);
    }

    @FXML
    private void handleRemoveTask() throws Exception {
        CommunityTask selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            communityTaskList.removeTask(selected);
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a task to remove.");
        }
    }

    @FXML
    private void handleDeleteAction() throws Exception {
        GreenAction selected = greenActionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            greenActionList.removeAction(selected);
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a task to remove.");
        }
    }

    @FXML
    private void handleDeleteActionTemplate() throws Exception {
        GreenActionTemplate selected = actionTemplateTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            greenActionTemplateList.removeActionTemplate(selected);
            ClovervillePersistenceService.saveList(greenActionTemplateList);
            refreshAllTables();
        } else {
            showAlert("Error", "Please select a task to remove.");
        }
    }

    @FXML
    private void handleRemoveTaskTemplate() throws Exception {
        CommunityTaskTemplate selected = taskTemplateTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            communityTaskTemplateList.removeActionTemplate(selected);
            ClovervillePersistenceService.saveList(communityTaskTemplateList);
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
            updateCommunityPointsDisplay();
            pointsStatusLabel.setText("Successfully updated community points.");
        } catch (NumberFormatException e) {
            pointsStatusLabel.setText("Error: Please enter a valid number.");
        }
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
    private Button navResidents, navActivities, navTrade, navReset, navPublish, navTemplates;

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
    private void switchToTemplates() {
        mainTabPane.getSelectionModel().select(2);
        updateNavButtonStyles(navTemplates);
    }

    @FXML
    private void switchToTrade() {
        mainTabPane.getSelectionModel().select(3);
        updateNavButtonStyles(navTrade);
    }

    @FXML
    private void switchToReset() {
        mainTabPane.getSelectionModel().select(4);
        updateNavButtonStyles(navReset);
    }

    @FXML
    private void switchToPublish() {
        mainTabPane.getSelectionModel().select(5);
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
        navTemplates.setStyle(inactiveStyle);

        activeButton.setStyle(activeStyle);
    }

}