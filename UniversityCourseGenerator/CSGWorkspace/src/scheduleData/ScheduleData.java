/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 *
 * @author Nick
 */
public class ScheduleData {
    ObservableList<Schedule> schedules = FXCollections.observableArrayList();
    String startMon = "";
    String endFri = "";
    public void addSchedule(String type, String date, String time, String title, String topic, String link, String criteria) {
        Schedule schedule = new Schedule(type,date,time,title,topic,link,criteria);
        schedules.add(schedule);
    }

    public void setDates(String startingMon, String endingFri) {
        startMon = startingMon;
        endFri = endingFri;
    }

    public ObservableList<Schedule> getSchedules() {
        return schedules;
    }

    public String getStartMon() {
        return startMon;
    }

    public String getEndFri() {
        return endFri;
    }

    public Schedule findSchedule(String date, String title) {
        for(int i = 0;i < schedules.size();i++){
            if(schedules.get(i).getDate().equals(date) && schedules.get(i).getTitle().equals(title)){
                return schedules.get(i);
            }
        }
        return null;
    }

    public void removeSchedule(String date, String title) {
        Schedule s = findSchedule(date,title);
        if(s != null){
            schedules.remove(s);
        }
    }

    public void changeDates(String newStartMon, String newEndFri) {
        startMon = newStartMon;
        endFri = newEndFri;
    }
    
    
}
