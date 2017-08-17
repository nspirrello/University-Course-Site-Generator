/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import recitationData.Recitation;
import taData.TeachingAssistant;


/**
 *
 * @author zhaotingyi
 */
public class TAReplaceUR implements jTPS_Transaction{
    private String TAname;
    private String TAemail;
    private String newName;
    private String newEmail;
    private CSGApp app;
    private CSGData data;
    private ArrayList<Recitation> recitationsOne;
    private ArrayList<Recitation> recitationsTwo;
    public TAReplaceUR(CSGApp app){
        this.app = app;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        data = (CSGData)app.getDataComponent();
        newName = workspace.getTaDataPane().getNameTextField().getText();
        newEmail = workspace.getTaDataPane().getEmailTextField().getText();
        TableView taTable = workspace.getTaDataPane().getTATable();
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        TeachingAssistant ta = (TeachingAssistant)selectedItem;
        TAname = ta.getName();
        TAemail = ta.getEmail();
        ObservableList<Recitation> rec = data.getRecitationData().getRecitation();
        recitationsOne = new ArrayList();
        recitationsTwo = new ArrayList();
        for(int i = 0;i < rec.size();i++){
            if(rec.get(i).getTaOne().equals(TAname)){
                recitationsOne.add(rec.get(i));
            } else if(rec.get(i).getTaTwo().equals(TAname)){
                recitationsTwo.add(rec.get(i));
            }
        }
    }

    @Override
    public void doTransaction() {
        data.getTaData().replaceTAName(TAname, newName);
        data.getTaData().removeTA(TAname);
        data.getTaData().addTA(newName, newEmail);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTaDataPane().getTATable();
        taTable.getSelectionModel().select(data.getTaData().getTA(newName));
        for(int i = 0;i < recitationsOne.size();i++){
            recitationsOne.get(i).setTaOne(newName);
        }
        for(int i = 0;i < recitationsTwo.size();i++){
            recitationsTwo.get(i).setTaTwo(newName);
        }
    }

    @Override
    public void undoTransaction() {
        data.getTaData().replaceTAName(newName, TAname);
        data.getTaData().removeTA(newName);
        data.getTaData().addTA(TAname, TAemail);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTaDataPane().getTATable();
        taTable.getSelectionModel().select(data.getTaData().getTA(TAname));
        for(int i = 0;i < recitationsOne.size();i++){
            recitationsOne.get(i).setTaOne(TAname);
        }
        for(int i = 0;i < recitationsTwo.size();i++){
            recitationsTwo.get(i).setTaTwo(TAname);
        }
    }
    
    
    
}
