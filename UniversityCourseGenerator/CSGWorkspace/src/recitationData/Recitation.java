/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recitationData;

/**
 *
 * @author Nick
 */
public class Recitation {
    String section;
    String instructor;
    String dayTime;
    String location;
    String taOne;
    String taTwo;
    public Recitation(String section,String instructor,String dayTime,String location,String taOne,String taTwo){
        this.section = section;
        this.instructor = instructor;
        this.dayTime = dayTime;
        this.location = location;
        this.taOne = taOne;
        this.taTwo = taTwo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTaOne() {
        return taOne;
    }

    public void setTaOne(String taOne) {
        this.taOne = taOne;
    }

    public String getTaTwo() {
        return taTwo;
    }

    public void setTaTwo(String taTwo) {
        this.taTwo = taTwo;
    }
    
}
