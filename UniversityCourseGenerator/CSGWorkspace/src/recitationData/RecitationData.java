/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recitationData;

import CSG.CSGApp;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nick
 */
public class RecitationData {
    ObservableList<Recitation> recitation;
    
    public RecitationData(CSGApp app){
        recitation = FXCollections.observableArrayList();
    }
    public void addRecitation(String section, String instructor, String dayTime, String location, String taOne, String taTwo) {
        Recitation rec = new Recitation(section,instructor,dayTime,location,taOne,taTwo);
        recitation.add(rec);
    }

    public ObservableList<Recitation> getRecitation() {
        return recitation;
    }
    
    public Recitation find(String section){
        for(int i = 0;i < recitation.size();i++){
            if(recitation.get(i).getSection().equals(section)){
                return recitation.get(i);
            }
        }
        return null;
    }
    
    public void removeRecitation(String section) {
        Recitation toRemove = find(section);
        if(toRemove != null){
            recitation.remove(toRemove);
        }
    }
    
}
