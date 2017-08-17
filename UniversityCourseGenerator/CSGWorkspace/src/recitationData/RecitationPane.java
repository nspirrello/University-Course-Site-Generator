/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recitationData;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Nick
 */
public class RecitationPane extends Pane{ 
    
    Label header,addEdit,section,instructor,dayTime,location,supTa1,supTa2;
    Button removeBttn,addEditBttn,clearBttn;
    ComboBox ta1,ta2;
    TextField sectionField,instructorField,dayTimeField,locationField,supTa1Field,supTa2Field;
    TableView recitationTable;
    TableColumn<Recitation, String> sectionCol,instructorCol,dayTimeCol,locationCol,supTa1Col,supTa2Col;
    
    VBox leftSide;
    VBox rightSide;
    HBox bttnHolder;
    HBox bottomPane;
    VBox bottom;
    
    HBox headerBox;
    VBox contentHolder;
    private final ScrollPane scroll;
    private final VBox content;
    CSGApp app;
    public void clearFields(){
        ta1.getSelectionModel().clearSelection();
        ta2.getSelectionModel().clearSelection();
        sectionField.clear();
        instructorField.clear();
        dayTimeField.clear();
        locationField.clear();
    }
    
    public RecitationPane(CSGApp app){
        this.app = app;
        PropertiesManager props = PropertiesManager.getPropertiesManager();  
        
        contentHolder = new VBox(20);
        
        String headerText = props.getProperty(CSG.CSGProp.RECITATION_HEADER.toString());
        header = new Label(headerText);
        String addEditText = props.getProperty(CSG.CSGProp.ADD_EDIT_TEXT.toString());
        addEdit = new Label(addEditText);
        String sectionText = props.getProperty(CSG.CSGProp.SECTION_TEXT.toString());
        section = new Label(sectionText);
        String instructorText = props.getProperty(CSG.CSGProp.INSTRUCTOR_TEXT.toString());
        instructor = new Label(instructorText);
        String dayTimeText = props.getProperty(CSG.CSGProp.DAY_TIME_TEXT.toString());
        dayTime = new Label(dayTimeText);
        String locationText = props.getProperty(CSG.CSGProp.LOCATION_TEXT.toString());
        location = new Label(locationText);
        String supTaText = props.getProperty(CSG.CSGProp.SP_TA_TEXT.toString());
        supTa1 = new Label(supTaText);
        supTa2 = new Label(supTaText);
        String removeBttnText = props.getProperty(CSG.CSGProp.REMOVE_BUTTON_TEXT.toString());
        removeBttn = new Button(removeBttnText);
        String addUpdateText = props.getProperty(CSG.CSGProp.ADD_UPDATE_BUTTON_TEXT.toString());
        addEditBttn = new Button(addUpdateText);
        String clearBttnText = props.getProperty(CSG.CSGProp.CLEAR_BUTTON_TEXT_REC.toString());
        clearBttn = new Button(clearBttnText);
        ta1 = new ComboBox();
        ta2 = new ComboBox();
        CSGData data = (CSGData)app.getDataComponent();
        ta1.setItems(data.getTaData().getTeachingAssistants());
        ta2.setItems(data.getTaData().getTeachingAssistants());
        sectionField = new TextField();
        instructorField = new TextField();
        dayTimeField = new TextField();
        locationField = new TextField();
        recitationTable = new TableView();
        
        sectionCol = new TableColumn("Section");
        sectionCol.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("section")
        );
        
        instructorCol = new TableColumn("Instructor");
        instructorCol.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("instructor")
        );
        
        dayTimeCol = new TableColumn("Day/Time");
        dayTimeCol.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("dayTime")
        );
        
        locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("location")
        );
        
        supTa1Col = new TableColumn("Supervising TA");
        supTa1Col.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("taOne")
        );
        
        supTa2Col = new TableColumn("Supervising TA");
        supTa2Col.setCellValueFactory(
                new PropertyValueFactory<Recitation,String>("taTwo")
        );
        
        recitationTable.getColumns().addAll(sectionCol,instructorCol,dayTimeCol,locationCol,supTa1Col,supTa2Col);
        
        recitationTable.setItems(data.getRecitationData().getRecitation());
        
        bottomPane = new HBox(20);
        bttnHolder = new HBox(15);
        
        leftSide = new VBox(20);
        rightSide = new VBox(10);
        headerBox = new HBox(10);
        
        bottom = new VBox(20);
        bottom.getChildren().addAll(addEdit,bottomPane,bttnHolder);
        
        bttnHolder.getChildren().addAll(addEditBttn,clearBttn);
        leftSide.getChildren().addAll(section,instructor,dayTime,location,supTa1,supTa2);
        rightSide.getChildren().addAll(sectionField,instructorField,dayTimeField,locationField,ta1,ta2);
        
        bottomPane.getChildren().addAll(leftSide,rightSide);
        headerBox.getChildren().addAll(header,removeBttn);
        
        contentHolder.getChildren().addAll(headerBox,recitationTable,bottom);
        scroll = new ScrollPane();
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        scroll.setContent(contentHolder);
        content = new VBox();
        content.getChildren().add(scroll);
        
        removeBttn.setOnAction(eh -> {
            Workspace work = (Workspace)app.getWorkspaceComponent();
            work.getController().handleRemoveRec();
        });
        
        addEditBttn.setOnAction(eh -> {
            Workspace workspace = (Workspace)app.getWorkspaceComponent();
            if(workspace.getRecitationPane().getRecitationTable().getSelectionModel().getSelectedItem() == null){
                workspace.getController().handleAddRec();
            } else {
                workspace.getController().handleUpdateRec();
            }
        });
        
        recitationTable.setOnMouseClicked(eh -> {
            Recitation r = (Recitation)recitationTable.getSelectionModel().getSelectedItem();
            sectionField.setText(r.getSection());
            instructorField.setText(r.getInstructor());
            dayTimeField.setText(r.getDayTime());
            locationField.setText(r.getLocation());
            ta1.getSelectionModel().select(r.getTaOne());
            ta2.getSelectionModel().select(r.getTaTwo());
        });
        
        clearBttn.setOnAction(eh -> {
            sectionField.clear();
            instructorField.clear();
            dayTimeField.clear();
            locationField.clear();
            ta1.getSelectionModel().clearSelection();
            ta2.getSelectionModel().clearSelection();
            recitationTable.getSelectionModel().clearSelection();
        });
    }

    public ComboBox getTa1() {
        return ta1;
    }

    public void setTa1(ComboBox ta1) {
        this.ta1 = ta1;
    }

    public ComboBox getTa2() {
        return ta2;
    }

    public TableView getRecitationTable() {
        return recitationTable;
    }

    public void setRecitationTable(TableView recitationTable) {
        this.recitationTable = recitationTable;
    }
    
    public void setTa2(ComboBox ta2) {
        this.ta2 = ta2;
    }

    public TextField getSectionField() {
        return sectionField;
    }

    public void setSectionField(TextField sectionField) {
        this.sectionField = sectionField;
    }

    public TextField getInstructorField() {
        return instructorField;
    }

    public void setInstructorField(TextField instructorField) {
        this.instructorField = instructorField;
    }

    public TextField getDayTimeField() {
        return dayTimeField;
    }

    public void setDayTimeField(TextField dayTimeField) {
        this.dayTimeField = dayTimeField;
    }

    public TextField getLocationField() {
        return locationField;
    }

    public void setLocationField(TextField locationField) {
        this.locationField = locationField;
    }
    
    public VBox getBottom() {
        return bottom;
    }
    
    public HBox getBttnHolder() {
        return bttnHolder;
    }

    public ScrollPane getScroll() {
        return scroll;
    }
    
    public VBox getContentHolder() {
        return contentHolder;
    }
    
    public HBox getBottomPane() {
        return bottomPane;
    }
    
    public VBox getContent(){
        return content;
    }
}
