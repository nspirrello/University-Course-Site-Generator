/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import jtps.jTPS_Transaction;

/**
 *
 * @author Nick
 */
public class TeamAdderUR implements jTPS_Transaction{
    private String name;
    private String color;
    private String textColor;
    private String link;
    private CSGApp app;
    private Workspace workspace;
    
    public TeamAdderUR(CSGApp app){
        this.app = app;
        workspace = (Workspace)app.getWorkspaceComponent();
        name = workspace.getProjectPane().getNameField().getText();
        color = workspace.getProjectPane().getColorPick().getValue().toString();
        textColor = workspace.getProjectPane().getTextColorPick().getValue().toString();
        link = workspace.getProjectPane().getLinkField().getText();
    }
    
    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getProjectData().addTeam(name, color, textColor, link);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getProjectData().removeTeam(name);
    }
    
}
