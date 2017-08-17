/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taData;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Style.CSGStyle;
import CSG.Workspace.Controller;
import CSG.Workspace.Workspace;
import djf.components.AppDataComponent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import properties_manager.PropertiesManager;

/**
 *
 * @author Nick
 */
public class TaDataPane extends Pane{
    SplitPane split;
    VBox content;
    HBox taHeader;
    HBox buttons;
    HBox officeHourHeader;
    TableView taTable;
    TableColumn<TeachingAssistant, Boolean> undergrad;
    TableColumn<TeachingAssistant, String> name;
    TableColumn<TeachingAssistant, String> email;
    TextField nameField;
    TextField emailField;
    Button add;
    Button clear;
    Label teachingAssistants;
    Label officeHours;
    Controller controller;
    Label start;
    Label end;
    ComboBox starting;
    ComboBox ending;
    TAData data;
    ScrollPane sp;
    
   
    Button remove;
    
    ObservableList<String> time_options;
    CSGApp app;
    
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    
    
    
    // THE OFFICE HOURS GRID
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    boolean canAdd;

    public SplitPane getSplit() {
        return split;
    }
    
    
    public TaDataPane(CSGApp app){
        canAdd = true;
        
        
        
        this.app = app;
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // INIT THE HEADER ON THE LEFT
        taHeader = new HBox();
        String tasHeaderText = props.getProperty(CSG.CSGProp.TAS_HEADER_TEXT.toString());
        teachingAssistants = new Label(tasHeaderText);
        taHeader.getChildren().add(teachingAssistants);
        
        
        // MAKE THE TABLE AND SETUP THE DATA MODEL
        taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        CSGData data = (CSGData)app.getDataComponent();
        ObservableList<TeachingAssistant> tableData = data.getTaData().getTeachingAssistants();
        taTable.setItems(tableData);
        taTable.setEditable(true);
        String nameColumnText = props.getProperty(CSG.CSGProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CSG.CSGProp.EMAIL_COLUMN_TEXT.toString());
        name = new TableColumn(nameColumnText);
        email = new TableColumn(emailColumnText);
        undergrad = new TableColumn("Undergrad");
        name.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        email.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("email")
        );
        undergrad.setCellValueFactory((TableColumn.CellDataFeatures<TeachingAssistant, Boolean> p) -> {
            return p.getValue().undergradProperty();
        });  
        undergrad.setCellFactory(new CheckBoxTableCell().forTableColumn(undergrad));
        undergrad.setEditable(true);

//      
        taTable.getColumns().addAll(undergrad,name,email);

        // ADD BOX FOR ADDING A TA
        String namePromptText = props.getProperty(CSG.CSGProp.NAME_PROMPT_TEXT.toString());
        String emailPromptText = props.getProperty(CSG.CSGProp.EMAIL_PROMPT_TEXT.toString());
        String addButtonText = props.getProperty(CSG.CSGProp.ADD_BUTTON_TEXT.toString());
        String clearButtonText = props.getProperty(CSG.CSGProp.CLEAR_BUTTON_TEXT.toString());
        String removeButtonText = props.getProperty(CSG.CSGProp.REMOVE_BUTTON_TEXT.toString());
        remove = new Button(removeButtonText);
        nameField = new TextField();
        emailField = new TextField();
        nameField.setPromptText(namePromptText);
        emailField.setPromptText(emailPromptText);
        add = new Button(addButtonText);
        clear = new Button(clearButtonText);
        buttons = new HBox();
        nameField.prefWidthProperty().bind(buttons.widthProperty().multiply(.4));
        emailField.prefWidthProperty().bind(buttons.widthProperty().multiply(.4));
        add.prefWidthProperty().bind(buttons.widthProperty().multiply(.1));
        clear.prefWidthProperty().bind(buttons.widthProperty().multiply(.1));
        buttons.getChildren().add(nameField);
        buttons.getChildren().add(emailField);
        buttons.getChildren().add(add);
        buttons.getChildren().add(clear);
        
        
        officeHourHeader = new HBox();
        String officeHoursGridText = props.getProperty(CSG.CSGProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHours = new Label(officeHoursGridText);
        officeHourHeader.getChildren().addAll(officeHours);
        
       
        

        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox();
        HBox leftHeader = new HBox(20);
        leftHeader.getChildren().addAll(taHeader,remove);
        leftPane.getChildren().add(leftHeader);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(buttons);
        VBox rightPane = new VBox();
        
        
        time_options = FXCollections.observableArrayList(
        props.getProperty(CSG.CSGProp.TIME_12AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_1AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_2AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_3AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_4AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_5AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_6AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_7AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_8AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_9AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_10AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_11AM.toString()),
        props.getProperty(CSG.CSGProp.TIME_12PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_1PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_2PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_3PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_4PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_5PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_6PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_7PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_8PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_9PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_10PM.toString()),
        props.getProperty(CSG.CSGProp.TIME_11PM.toString())
        );
        officeHoursHeaderBox = new HBox();
        officeHoursGridText = props.getProperty(CSG.CSGProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.getChildren().add(officeHoursHeaderLabel);
        
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();

        // ORGANIZE THE LEFT AND RIGHT PANES
        
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        starting = new ComboBox(time_options);
        ending = new ComboBox(time_options);
        
        officeHourHeader.getChildren().add(starting);
        starting.setPrefHeight(42);
        starting.setPrefWidth(150);
        starting.getSelectionModel().select(data.getTaData().getStartHour());
        starting.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null){
                    if(starting.getSelectionModel().getSelectedIndex() != data.getTaData().getStartHour()){
                        controller.changeTime();
                    }
                }
            }
        });
        officeHourHeader.getChildren().add(ending);
        ending.setPrefHeight(42);
        ending.setPrefWidth(150);
        ending.getSelectionModel().select(data.getTaData().getEndHour());
        ending.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null)
                    if(ending.getSelectionModel().getSelectedIndex() != data.getTaData().getEndHour()){
                        controller.changeTime();
                    }
                        
            }    
        });
        rightPane.getChildren().addAll(officeHourHeader,officeHoursGridPane);
        
        sp = new ScrollPane(rightPane);
        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        split = new SplitPane(leftPane, sp);
        
        content = new VBox();
        // AND PUT EVERYTHING IN THE WORKSPACE
        content.getChildren().add(split);
        controller = new Controller(app);

        // MAKE SURE THE TABLE EXTENDS DOWN FAR ENOUGH
        taTable.prefHeightProperty().bind(content.heightProperty().multiply(.5));
        
        nameField.setOnAction(e -> {
            if(!canAdd)
                controller.changeExistTA();
            else
                controller.handleAddTA();
        });
        emailField.setOnAction(e -> {
            if(!canAdd)
                controller.changeExistTA();
            else
                controller.handleAddTA();
        });
        add.setOnAction(e -> {
            if(!canAdd)
                controller.changeExistTA();
            else
                controller.handleAddTA();
        });
        clear.setOnAction(e -> {
            add.setText(props.getProperty(CSG.CSGProp.ADD_BUTTON_TEXT.toString()));
            canAdd = true;
            nameField.clear();
            emailField.clear();
            taTable.getSelectionModel().select(null);
        });
        
    }

    public ScrollPane getSp() {
        return sp;
    }
    
    public VBox getContent(){
        return content;
    }
    public HBox getTAsHeaderBox() {
        return taHeader;
    }

    public Label getTAsHeaderLabel() {
        return teachingAssistants;
    }

    public TableView getTATable() {
        return taTable;
    }

    public HBox getAddBox() {
        return buttons;
    }

    public TextField getNameTextField() {
        return nameField;
    }

    public TextField getEmailTextField() {
        return emailField;
    }

    public Button getAddButton() {
        return add;
    }
    
    public Button getClearButton() {
        return clear;
    }

    public HBox getOfficeHoursSubheaderBox() {
        return officeHourHeader;
    }

    public Label getOfficeHoursSubheaderLabel() {
        return officeHours;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getNameField() {
        return nameField;
    }
    public GridPane getOfficeHoursGridPane() {
        return officeHoursGridPane;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {
        return officeHoursGridTimeHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {
        return officeHoursGridTimeHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {
        return officeHoursGridDayHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {
        return officeHoursGridDayHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {
        return officeHoursGridTimeCellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {
        return officeHoursGridTimeCellLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {
        return officeHoursGridTACellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {
        return officeHoursGridTACellLabels;
    }
    
    public String getCellKey(Pane testPane) {
        for (String key : officeHoursGridTACellLabels.keySet()) {
            if (officeHoursGridTACellPanes.get(key) == testPane) {
                return key;
            }
        }
        return null;
    }

    public Label getTACellLabel(String cellKey) {
        return officeHoursGridTACellLabels.get(cellKey);
    }

    public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }
    
    public ComboBox getOfficeHour(boolean start){
        if(start)
            return starting;
        return ending;
    }

    public String buildCellKey(int col, int row) {
        return "" + col + "_" + row;
    }

    public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }

    public void resetWorkspace() {
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
    }
    
    public void reloadWorkspace(AppDataComponent dataComponent) {
        TAData taData = (TAData)dataComponent;
        reloadOfficeHoursGrid(taData);
    }

    public void reloadOfficeHoursGrid(TAData dataComponent) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
            row += 2;
        }

        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        CSGStyle csgStyle = (CSGStyle)app.getStyleComponent();
        csgStyle.initOfficeHoursGridStyle();
    }
    
    public void addCellToGrid(TAData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
}

   
    

   
