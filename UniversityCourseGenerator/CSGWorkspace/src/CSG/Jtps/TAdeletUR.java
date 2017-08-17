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
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;
import recitationData.Recitation;

/**
 *
 * @author zhaotingyi
 */
public class TAdeletUR implements jTPS_Transaction{
    private ArrayList<Recitation> recitationsOne;
    private ArrayList<Recitation> recitationsTwo;
    private CSGApp app;
    private CSGData data;
    private ArrayList<StringProperty> cellProps = new ArrayList<StringProperty>();
    private String TAname;
    private String TAemail;
    
    public TAdeletUR(CSGApp app, String TAname){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        this.TAname = TAname;
        TAemail = data.getTaData().getTA(TAname).getEmail();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        HashMap<String, Label> labels = workspace.getTaDataPane().getOfficeHoursGridTACellLabels();
        for (Label label : labels.values()) {
            if (label.getText().equals(TAname)
            || (label.getText().contains(TAname + "\n"))
            || (label.getText().contains("\n" + TAname))) {
                cellProps.add(label.textProperty());
            }
        }
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
        data.getTaData().removeTA(TAname);
        for(StringProperty cellProp : cellProps){
            data.getTaData().removeTAFromCell(cellProp, TAname);
        }
        for(int i = 0;i < recitationsOne.size();i++){
            recitationsOne.get(i).setTaOne("");
        }
        for(int i = 0;i < recitationsTwo.size();i++){
            recitationsTwo.get(i).setTaTwo("");
        }
    }

    @Override
    public void undoTransaction() {
        data.getTaData().addTA(TAname, TAemail);
        for(StringProperty cellProp : cellProps){
            String cellText = cellProp.getValue();
            if (cellText.length() == 0){
                cellProp.setValue(TAname);
            } else {
                cellProp.setValue(cellText + "\n" + TAname);}
        }
        for(int i = 0;i < recitationsOne.size();i++){
            recitationsOne.get(i).setTaOne(TAname);
        }
        for(int i = 0;i < recitationsTwo.size();i++){
            recitationsTwo.get(i).setTaTwo(TAname);
        }
    }
    
}
