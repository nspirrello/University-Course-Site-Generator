/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Nick
 */
public class RecDeleteUR implements jTPS_Transaction{
    private CSGApp app;
    private CSGData data;
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String taOne;
    private String taTwo;
    public RecDeleteUR(CSGApp app, String section){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        this.section = section;
        instructor = data.getRecitationData().find(section).getInstructor();
        dayTime = data.getRecitationData().find(section).getDayTime();
        location = data.getRecitationData().find(section).getLocation();
        taOne = data.getRecitationData().find(section).getTaOne();
        taTwo = data.getRecitationData().find(section).getTaTwo();
        
    }
    @Override
    public void doTransaction() {
        data.getRecitationData().removeRecitation(section);
    }

    @Override
    public void undoTransaction() {
        data.getRecitationData().addRecitation(section, instructor, dayTime, location, taOne, taTwo);
    }
    
}
