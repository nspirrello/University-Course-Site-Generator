/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleData;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Controller;
import CSG.Workspace.Workspace;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;
import properties_manager.PropertiesManager;

/**
 *
 * @author Nick
 */
public class SchedulePane extends Pane{
    Label header,calendar,startingMon,endingFri,scheduleItem,addEdit,type,date,time,title,topic,link,criteria;
    Button removeBttn,addUpdateBttn,clearBttn;
    DatePicker starting,ending;
    TextField typeField,dateField,timeField,titleField,topicField,linkField,criteriaField;
    
    VBox contentHolder,leftSide,rightSide;
    HBox infoArea, buttonHolder;
    HBox scheduleItems, calendarHolder;
    VBox topArea;
    VBox midArea;
    
    TableView schedule;
    TableColumn<Schedule,String> typeCol, dateCol,titleCol,topicCol;
    ScrollPane scroll;
    VBox content;
    Controller controller;
    
    public void clearFields(){
        LocalDate startDate = LocalDate.parse("2016-01-04");
        starting.setValue(startDate);
        LocalDate endDate = LocalDate.parse("2018-01-05");
        ending.setValue(endDate);
        typeField.clear();
        dateField.clear();
        timeField.clear();
        titleField.clear();
        topicField.clear();
        linkField.clear();
        criteriaField.clear();
    }
    CSGApp app;
    public SchedulePane(CSGApp app){
        this.app = app;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData data = (CSGData)app.getDataComponent();
        schedule = new TableView();
        typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(
            new PropertyValueFactory<>("type")
        );
        dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
        titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(
            new PropertyValueFactory<>("title")
        );
        topicCol = new TableColumn("Topic");
        topicCol.setCellValueFactory(
            new PropertyValueFactory<>("topic")
        );
        schedule.getColumns().addAll(typeCol,dateCol,titleCol,topicCol);
        schedule.setItems(data.getScheduleData().getSchedules());
        LocalDate startDate = LocalDate.parse("2016-01-04");
        starting = new DatePicker(startDate);
        starting.setEditable(false);
        LocalDate endDate = LocalDate.parse("2018-01-05");
        ending = new DatePicker(endDate);
        ending.setEditable(false);
        typeField = new TextField();
        dateField = new TextField();
        timeField = new TextField();
        titleField = new TextField();
        topicField = new TextField();
        linkField = new TextField();
        criteriaField = new TextField();
        
        contentHolder = new VBox(20);
        calendarHolder = new HBox(25);
        leftSide = new VBox(25);
        rightSide = new VBox(15);
        infoArea = new HBox(20);
        buttonHolder = new HBox(15);
        scheduleItems = new HBox(15);
        topArea = new VBox(15);
        midArea = new VBox(20);
        
        String headerText = props.getProperty(CSG.CSGProp.SCHEDULE_HEADER.toString());
        header = new Label(headerText);
        String calendarText = props.getProperty(CSG.CSGProp.CALENDAR_HEADER.toString());
        calendar = new Label(calendarText);
        String startingMonText = props.getProperty(CSG.CSGProp.STARTING_MON_TEXT.toString());
        startingMon = new Label(startingMonText);
        String endingFriText = props.getProperty(CSG.CSGProp.ENDING_FRI_TEXT.toString());
        endingFri = new Label(endingFriText);
        String scheduleItemText = props.getProperty(CSG.CSGProp.SCHEDULE_ITEM_TEXT.toString());
        scheduleItem = new Label(scheduleItemText);
        String addEditText = props.getProperty(CSG.CSGProp.ADD_EDIT_SCHEUDLE_TEXT.toString());
        addEdit = new Label(addEditText);
        String typeText = props.getProperty(CSG.CSGProp.TYPE_TEXT.toString());
        type = new Label(typeText);
        String dateText = props.getProperty(CSG.CSGProp.DATE_TEXT.toString());
        date = new Label(dateText);
        String timeText = props.getProperty(CSG.CSGProp.TIME_TEXT.toString());
        time = new Label(timeText);
        String titleText = props.getProperty(CSG.CSGProp.TITLE_SCHEDULE_TEXT.toString());
        title = new Label(titleText);
        String topicText = props.getProperty(CSG.CSGProp.TOPIC_TEXT.toString());
        topic = new Label(topicText);
        String linkText = props.getProperty(CSG.CSGProp.LINK_TEXT.toString());
        link = new Label(linkText);
        String criteriaText = props.getProperty(CSG.CSGProp.CRITERIA_TEXT.toString());
        criteria = new Label(criteriaText);
        String removeBttnText = props.getProperty(CSG.CSGProp.REMOVE_SCHEDULE_BUTTON.toString());
        removeBttn = new Button(removeBttnText);
        String addUpdateBttnText = props.getProperty(CSG.CSGProp.ADD_UPDATE_SCHEDULE_BUTTON_TEXT.toString());
        addUpdateBttn = new Button(addUpdateBttnText);
        String clearBttnText = props.getProperty(CSG.CSGProp.CLEAR_SCHEDULE_BUTTON_TEXT.toString());
        clearBttn = new Button(clearBttnText);
        
        
        scheduleItems.getChildren().addAll(scheduleItem,removeBttn);
        calendarHolder.getChildren().addAll(startingMon,starting,endingFri,ending);
        rightSide.getChildren().addAll(typeField,dateField,timeField,titleField,topicField,linkField,criteriaField);
        leftSide.getChildren().addAll(addEdit,type,date,time,title,topic,link,criteria);
        infoArea.getChildren().addAll(leftSide,rightSide);
        buttonHolder.getChildren().addAll(addUpdateBttn,clearBttn);
        
        topArea.getChildren().addAll(calendar,calendarHolder);
        midArea.getChildren().addAll(scheduleItems,schedule,addEdit,infoArea,buttonHolder);
        contentHolder.getChildren().addAll(header,topArea,midArea);
        scroll = new ScrollPane();
        scroll.setContent(contentHolder);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        content = new VBox();
        content.getChildren().add(scroll);
        content.setAlignment(Pos.CENTER);
        
        schedule.setOnMouseClicked(eh -> {
            Schedule s = (Schedule)schedule.getSelectionModel().getSelectedItem();
            typeField.setText(s.getType());
            dateField.setText(s.getDate());
            titleField.setText(s.getTitle());
            timeField.setText(s.getTime());
            topicField.setText(s.getTopic());
            linkField.setText(s.getLink());
            criteriaField.setText(s.getCriteria());
       });
        
       clearBttn.setOnAction(eh -> {
           schedule.getSelectionModel().clearSelection();
           typeField.clear();
           dateField.clear();
           titleField.clear();
           timeField.clear();
           topicField.clear();
           linkField.clear();
           criteriaField.clear();
       });
       
       removeBttn.setOnAction(eh -> {
           Workspace work = (Workspace)app.getWorkspaceComponent();
           work.getController().handleRemoveSchedule();
       });
       
       addUpdateBttn.setOnAction(eh -> {
           Workspace work = (Workspace)app.getWorkspaceComponent();
           if(work.getSchedulePane().getSchedule().getSelectionModel().getSelectedItem() == null){
               work.getController().handleAddSchedule();
           } else {
               work.getController().handleUpdateSchedule();
           }
       });
       
       starting.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> ov, LocalDate t, LocalDate t1) {
                if(t1 != null){
                    if(!t1.toString().equals(data.getScheduleData().getEndFri())){
                        Workspace work = (Workspace)app.getWorkspaceComponent();
                        System.out.println("Starting: " + " t1: " + t1 + " t: " + t);
                        work.getController().handleChangeDates(starting,ending,t,"start",t1);
                        
                    }
                }
            }
           
       });
       
       ending.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> ov, LocalDate t, LocalDate t1) {
                if(t1 != null){
                    if(!t1.toString().equals(data.getScheduleData().getEndFri())){
                        Workspace work = (Workspace)app.getWorkspaceComponent();
                        System.out.println("Ending: " + " t1: " + t1 + " t: " + t);
                        work.getController().handleChangeDates(starting, ending,t,"end",t1);
                        
                    }
                }
            }
           
       });
       
    }

    public DatePicker getStarting() {
        return starting;
    }

    public DatePicker getEnding() {
        return ending;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public TextField getDateField() {
        return dateField;
    }

    public TextField getTimeField() {
        return timeField;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getTopicField() {
        return topicField;
    }

    public TextField getLinkField() {
        return linkField;
    }

    public TextField getCriteriaField() {
        return criteriaField;
    }
    
    public TableView getSchedule() {
        return schedule;
    }

    public void setSchedule(TableView schedule) {
        this.schedule = schedule;
    }
    
    public VBox getContentHolder() {
        return contentHolder;
    }

    public VBox getTopArea() {
        return topArea;
    }

    public VBox getMidArea() {
        return midArea;
    }

    public ScrollPane getScroll() {
        return scroll;
    }
    
    public VBox getContent(){
        return content;
    }
}
