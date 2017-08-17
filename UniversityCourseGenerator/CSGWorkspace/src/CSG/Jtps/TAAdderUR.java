/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import java.util.regex.Pattern;
import jtps.jTPS_Transaction;


/**
 *
 * @author zhaotingyi
 */
public class TAAdderUR implements jTPS_Transaction{
    
    private String TAName;
    private String TAEmail;
    private CSGApp app;
    private Workspace workspace;
    
    public TAAdderUR(CSGApp app){
        this.app = app;
        workspace = (Workspace)app.getWorkspaceComponent();
        TAName = workspace.getTaDataPane().getNameTextField().getText();
        TAEmail = workspace.getTaDataPane().getEmailTextField().getText();
    }

    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getTaData().addTA(TAName, TAEmail);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getTaData().removeTA(TAName);
    }
    
}
