/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Workspace;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Jtps.RecAdderUR;
import CSG.Jtps.RecDeleteUR;
import CSG.Jtps.RecReplaceUR;
import CSG.Jtps.ScheduleAdderUR;
import CSG.Jtps.ScheduleChangeDateUR;
import CSG.Jtps.ScheduleRemoveUR;
import CSG.Jtps.ScheduleReplaceUR;
import CSG.Jtps.StudentAdderUR;
import CSG.Jtps.StudentDeleteUR;
import CSG.Jtps.StudentReplaceUR;
import CSG.Jtps.TAAdderUR;
import CSG.Jtps.TAReplaceUR;
import CSG.Jtps.TAdeletUR;
import CSG.Jtps.TAhourschangeUR;
import CSG.Jtps.TAtoggleUR;
import CSG.Jtps.TeamAdderUR;
import CSG.Jtps.TeamDeleteUR;
import CSG.Jtps.TeamReplaceUR;
import static CSG.Style.CSGStyle.CLASS_HIGHLIGHTED_GRID_CELL;
import static CSG.Style.CSGStyle.CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN;
import static CSG.Style.CSGStyle.CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE;
import djf.controller.AppFileController;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import jtps.jTPS;
import jtps.jTPS_Transaction;
import projectData.ProjectPane;
import projectData.Student;
import projectData.Team;
import properties_manager.PropertiesManager;
import recitationData.Recitation;
import recitationData.RecitationPane;
import scheduleData.Schedule;
import scheduleData.SchedulePane;
import taData.TeachingAssistant;
import taData.TimeSlot;


/**
 *
 * @author Nick
 */

public class Controller{
    CSGApp app;
    static jTPS jTPS = new jTPS();
     public Controller(CSGApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
    }
    
    /**
     * This helper method should be called every time an edit happens.
     */    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    public void handleAddTA() {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TextField nameTextField = workspace.getTaDataPane().getNameField();
        TextField emailTextField = workspace.getTaDataPane().getEmailField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CSGData data = (CSGData)app.getDataComponent();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(CSG.CSGProp.MISSING_TA_NAME_TITLE), props.getProperty(CSG.CSGProp.MISSING_TA_NAME_MESSAGE));            
        }
        // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(CSG.CSGProp.MISSING_TA_EMAIL_TITLE), props.getProperty(CSG.CSGProp.MISSING_TA_EMAIL_MESSAGE));                        
        }
        // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.getTaData().containsTA(name, email)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(CSG.CSGProp.TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(CSG.CSGProp.TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
        else if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches()){
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(CSG.CSGProp.TA_EMAIL_INVALID_TITLE), props.getProperty(CSG.CSGProp.TA_EMAIL_INVALID_MESSAGE));
        }
        // EVERYTHING IS FINE, ADD A NEW TA
        else {
            
            // ADD THE NEW TA TO THE DATA
            jTPS_Transaction addTAUR = new TAAdderUR(app);
            jTPS.addTransaction(addTAUR);
            workspace.getTaDataPane().getTATable().refresh();
            
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
     public void changeExistTA(){
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTaDataPane().getTATable();
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        CSGData data = (CSGData)app.getDataComponent();
        TeachingAssistant ta = (TeachingAssistant)selectedItem;
        String name = ta.getName();
        String newName = workspace.getTaDataPane().getNameField().getText();
        String newEmail = workspace.getTaDataPane().getEmailField().getText();
        jTPS_Transaction replaceTAUR = new TAReplaceUR(app);
        jTPS.addTransaction(replaceTAUR);

        data.getTaData().replaceTAName(name, newName);
        data.getTaData().removeTA(name);
        data.getTaData().addTA(newName, newEmail);
        workspace = (Workspace)app.getWorkspaceComponent();
        taTable.getSelectionModel().select(data.getTaData().getTA(newName));
        markWorkAsEdited();
    }
    
    public void handleKeyPress(KeyCode code) {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) {
            // GET THE TABLE
            Workspace workspace = (Workspace)app.getWorkspaceComponent();
            TableView taTable = workspace.getTaDataPane().getTATable();
            
            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // GET THE TA AND REMOVE IT
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
                String taName = ta.getName();
                CSGData data = (CSGData)app.getDataComponent();
                
                jTPS_Transaction deletUR = new TAdeletUR(app, taName);
                jTPS.addTransaction(deletUR);
                
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                // WE'VE CHANGED STUFF
                markWorkAsEdited();
            }
        }
    }

    /**
     * This function provides a response for when the user clicks
     * on the office hours grid to add or remove a TA to a time slot.
     * 
     * @param pane The pane that was toggled.
     */
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTaDataPane().getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            CSGData data = (CSGData)app.getDataComponent();
            String cellKey = pane.getId();
            
            // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
            jTPS_Transaction toggleUR = new TAtoggleUR(taName, cellKey, data);
            jTPS.addTransaction(toggleUR);
            
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }
    
    public void handleGridCellMouseExited(Pane pane) {
        String cellKey = pane.getId();
        CSGData data = (CSGData)app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        Workspace workspace = (Workspace)app.getWorkspaceComponent();

        Pane mousedOverPane = workspace.getTaDataPane().getTACellPane(data.getTaData().getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);

        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getTaDataPane().getOfficeHoursGridDayHeaderPanes().get(data.getTaData().getCellKey(column, 0));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getTaDataPane().getOfficeHoursGridTimeCellPanes().get(data.getTaData().getCellKey(0, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getTaDataPane().getOfficeHoursGridTimeCellPanes().get(data.getTaData().getCellKey(1, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getTaData().getCellKey(i, row);
            Pane cell = workspace.getTaDataPane().getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getTaData().getCellKey(column, i);
            Pane cell = workspace.getTaDataPane().getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }
    }

    public void handleGridCellMouseEntered(Pane pane) {
        String cellKey = pane.getId();
        CSGData data = (CSGData)app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        // THE MOUSED OVER PANE
        Pane mousedOverPane = workspace.getTaDataPane().getTACellPane(data.getTaData().getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_CELL);
        
        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getTaDataPane().getOfficeHoursGridDayHeaderPanes().get(data.getTaData().getCellKey(column, 0));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getTaDataPane().getOfficeHoursGridTimeCellPanes().get(data.getTaData().getCellKey(0, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getTaDataPane().getOfficeHoursGridTimeCellPanes().get(data.getTaData().getCellKey(1, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        
        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getTaData().getCellKey(i, row);
            Pane cell = workspace.getTaDataPane().getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getTaData().getCellKey(column, i);
            Pane cell = workspace.getTaDataPane().getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }
    }
    
    public void Undo(){
        jTPS.undoTransaction();
        markWorkAsEdited();
    }
    public void Redo(){
        jTPS.doTransaction();
        markWorkAsEdited();
    }
    
    public void changeTime(){
        CSGData data = (CSGData)app.getDataComponent();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox comboBox1 = workspace.getTaDataPane().getOfficeHour(true);
        ComboBox comboBox2 = workspace.getTaDataPane().getOfficeHour(false);
        int startTime = data.getTaData().getStartHour();
        int endTime = data.getTaData().getEndHour();
        int newStartTime = comboBox1.getSelectionModel().getSelectedIndex();
        int newEndTime = comboBox2.getSelectionModel().getSelectedIndex();
        if(newStartTime > endTime || newEndTime < startTime){
            comboBox1.getSelectionModel().select(startTime);
            comboBox2.getSelectionModel().select(endTime);
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(CSG.CSGProp.START_OVER_END_TITLE.toString()), props.getProperty(CSG.CSGProp.START_OVER_END_MESSAGE.toString()));
            return;
        }
        ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(data.getTaData());
        if(officeHours.isEmpty()){
            workspace.getTaDataPane().getOfficeHoursGridPane().getChildren().clear();
            data.getTaData().initHours("" + newStartTime, "" + newEndTime);
        }
        String firsttime = officeHours.get(0).getTime();
        int firsthour = Integer.parseInt(firsttime.substring(0, firsttime.indexOf('_')));
        if(firsttime.contains("pm"))
            firsthour += 12;
        if(firsttime.contains("12"))
            firsthour -= 12;
        String lasttime = officeHours.get(officeHours.size() - 1).getTime();
        int lasthour = Integer.parseInt(lasttime.substring(0, lasttime.indexOf('_')));
        if(lasttime.contains("pm"))
            lasthour += 12;
        if(lasttime.contains("12"))
            lasthour -= 12;
        if(firsthour < newStartTime || lasthour + 1 > newEndTime){
            AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
            yesNoDialog.show(props.getProperty(CSG.CSGProp.OFFICE_HOURS_REMOVED_TITLE.toString()), props.getProperty(CSG.CSGProp.OFFICE_HOURS_REMOVED_MESSAGE).toString());
            String selection = yesNoDialog.getSelection();
            if (!selection.equals(AppYesNoCancelDialogSingleton.YES)){
                comboBox1.getSelectionModel().select(startTime);
                comboBox2.getSelectionModel().select(endTime);
                return;
            }
        }
        
        jTPS_Transaction changeTimeUR = new TAhourschangeUR(app);
        jTPS.addTransaction(changeTimeUR);
        
        markWorkAsEdited();
    }
    
    
    public void loadTAtotext(){
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTaDataPane().getTATable();
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String name = ta.getName();
            String email = ta.getEmail();
            workspace.getTaDataPane().getNameTextField().setText(name);
            workspace.getTaDataPane().getEmailTextField().setText(email);
        }
    }

    public void handleAddStudent() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        jTPS_Transaction StudentAdderUR = new StudentAdderUR(app);
        jTPS.addTransaction(StudentAdderUR);
        workspace.getProjectPane().getStudentTable().refresh();
        markWorkAsEdited();
        ProjectPane pp = workspace.getProjectPane();
        pp.getFirstNameField().clear();
        pp.getLastNameField().clear();
        pp.getTeamBox().getSelectionModel().clearSelection();
        pp.getRoleField().clear();
    }

    public void handleUpdateStudent() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        jTPS_Transaction StudentReplaceUR = new StudentReplaceUR(app);
        jTPS.addTransaction(StudentReplaceUR);
        workspace.getProjectPane().getStudentTable().refresh();
        markWorkAsEdited();
        
    }

    public void handleRemoveStudent() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView sTable = workspace.getProjectPane().getStudentTable();
        Student s = (Student)sTable.getSelectionModel().getSelectedItem();
        String firstName = s.getFirstName();
        String lastName = s.getLastName();
        jTPS_Transaction StudentDeleteUR = new StudentDeleteUR(app,firstName,lastName);
        jTPS.addTransaction(StudentDeleteUR);
        workspace.getProjectPane().getStudentTable().refresh();
        markWorkAsEdited();
        ProjectPane pp = workspace.getProjectPane();
        pp.getFirstNameField().clear();
        pp.getLastNameField().clear();
        pp.getTeamBox().getSelectionModel().clearSelection();
        pp.getRoleField().clear();
    }

    public void handleAddTeam() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        CSGData data = (CSGData)app.getDataComponent();
        jTPS_Transaction TeamAdderUR = new TeamAdderUR(app);
        jTPS.addTransaction(TeamAdderUR);
        workspace.getProjectPane().getTeamTable().refresh();
        markWorkAsEdited();
        ProjectPane pp = workspace.getProjectPane();
        pp.getNameField().clear();
        pp.getLinkField().clear();
    }

    public void handleUpdateTeam() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        jTPS_Transaction TeamReplaceUR = new TeamReplaceUR(app);
        jTPS.addTransaction(TeamReplaceUR);
        workspace.getProjectPane().getTeamTable().refresh();
        markWorkAsEdited();
        
    }

    public void handleRemoveTeam() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView tTable = workspace.getProjectPane().getTeamTable();
        Team t = (Team)tTable.getSelectionModel().getSelectedItem();
        String name = t.getName();
        jTPS_Transaction TeamDeleteUR = new TeamDeleteUR(app,name);
        jTPS.addTransaction(TeamDeleteUR);
        workspace.getProjectPane().getTeamTable().refresh();
        markWorkAsEdited();
        ProjectPane pp = workspace.getProjectPane();
        pp.getNameField().clear();
        pp.getLinkField().clear();
    }
    
    public void handleAddRec() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        CSGData data = (CSGData)app.getDataComponent();
        jTPS_Transaction RecAdderUR = new RecAdderUR(app);
        jTPS.addTransaction(RecAdderUR);
        workspace.getRecitationPane().getRecitationTable().refresh();
        markWorkAsEdited();
        RecitationPane rp = workspace.getRecitationPane();
        rp.getSectionField().clear();
        rp.getInstructorField().clear();
        rp.getDayTimeField().clear();
        rp.getLocationField().clear();
        rp.getTa1().getSelectionModel().clearSelection();
        rp.getTa2().getSelectionModel().clearSelection();
    }

    public void handleUpdateRec() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        jTPS_Transaction RecReplaceUR = new RecReplaceUR(app);
        jTPS.addTransaction(RecReplaceUR);
        workspace.getRecitationPane().getRecitationTable().refresh();
        markWorkAsEdited();
    }

    public void handleRemoveRec() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView rTable = workspace.getRecitationPane().getRecitationTable();
        Recitation r = (Recitation)rTable.getSelectionModel().getSelectedItem();
        String section = r.getSection();
        jTPS_Transaction RecDeleteUR = new RecDeleteUR(app,section);
        jTPS.addTransaction(RecDeleteUR);
        workspace.getRecitationPane().getRecitationTable().refresh();
        markWorkAsEdited();
        RecitationPane rp = workspace.getRecitationPane();
        rp.getSectionField().clear();
        rp.getInstructorField().clear();
        rp.getDayTimeField().clear();
        rp.getLocationField().clear();
        rp.getTa1().getSelectionModel().clearSelection();
        rp.getTa2().getSelectionModel().clearSelection();
    }

    public void handleUpdateSchedule() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        jTPS_Transaction ScheduleReplaceUR = new ScheduleReplaceUR(app);
        jTPS.addTransaction(ScheduleReplaceUR);
        workspace.getSchedulePane().getSchedule().refresh();
        markWorkAsEdited();
        
    }

    public void handleAddSchedule() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        CSGData data = (CSGData)app.getDataComponent();
        jTPS_Transaction ScheduleAdderUR = new ScheduleAdderUR(app);
        jTPS.addTransaction(ScheduleAdderUR);
        workspace.getSchedulePane().getSchedule().refresh();
        markWorkAsEdited();
        SchedulePane sp = workspace.getSchedulePane();
        sp.getTypeField().clear();
        sp.getDateField().clear();
        sp.getTitleField().clear();
        sp.getTimeField().clear();
        sp.getTopicField().clear();
        sp.getLinkField().clear();
        sp.getCriteriaField().clear();
    }

    public void handleRemoveSchedule() {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView tTable = workspace.getSchedulePane().getSchedule();
        Schedule s = (Schedule)tTable.getSelectionModel().getSelectedItem();
        String date  = s.getDate();
        String title = s.getTitle();
        jTPS_Transaction ScheduleRemoveUR = new ScheduleRemoveUR(app,date,title);
        jTPS.addTransaction(ScheduleRemoveUR);
        workspace.getProjectPane().getTeamTable().refresh();
        markWorkAsEdited();
        SchedulePane sp = workspace.getSchedulePane();
        sp.getTypeField().clear();
        sp.getDateField().clear();
        sp.getTitleField().clear();
        sp.getTimeField().clear();
        sp.getTopicField().clear();
        sp.getLinkField().clear();
        sp.getCriteriaField().clear();
    }
    
    public void handleChangeDates(DatePicker starting, DatePicker ending, LocalDate t, String which, LocalDate t1){
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        LocalDate start  = null;
        LocalDate end = null;
        if(which.equals("start")){
            start = t1;
            end = ending.getValue();
            System.out.println("Start: " + start + " End: " + end);
        } else if(which.equals("end")){
            end = t1;
            start = starting.getValue();
            System.out.println("Start: " + start + " End: " + end);
        }
        
        if(start !=null && end != null){
            DayOfWeek startDay = start.getDayOfWeek();
            int startPos = start.getDayOfYear();
            DayOfWeek endDay = end.getDayOfWeek();
            int endPos = end.getDayOfYear();
            int endYear = end.getYear();
            int startYear = start.getYear();
            
            if(startDay.equals(DayOfWeek.MONDAY) && endDay.equals(DayOfWeek.FRIDAY)){
                if(startYear < endYear){
                    jTPS_Transaction scheduleChangeDateUR = new ScheduleChangeDateUR(app);
                    jTPS.addTransaction(scheduleChangeDateUR);
                } else if(startYear == endYear && startPos < endPos){
                        jTPS_Transaction scheduleChangeDateUR = new ScheduleChangeDateUR(app);
                        jTPS.addTransaction(scheduleChangeDateUR);
                        CSGData data = (CSGData)app.getDataComponent();
                           data.getScheduleData().setDates(start.toString(),end.toString());
                        }
                } else {
                    System.out.println("END DATE BEFORE START");
                    if(which.equals("end")){
                        workspace.getSchedulePane().getEnding().setValue(t);
                        System.out.println("ENDING VALUE AFTER SWAP BACK: " + workspace.getSchedulePane().getEnding().getValue());
                    } else if(which.equals("start")) {
                        workspace.getSchedulePane().getStarting().setValue(t);
                    }
                    
                    
                }
            } else {
                System.out.println("LEARN TO READ");
                if(which.equals("end")){
                    workspace.getSchedulePane().getEnding().setValue(t);
                    
                } else if(which.equals("start")) {
                    workspace.getSchedulePane().getStarting().setValue(t);
                }
                
            }
            markWorkAsEdited();
        }
    }
    
