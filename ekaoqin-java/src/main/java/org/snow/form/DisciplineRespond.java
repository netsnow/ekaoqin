package org.snow.form;

import org.snow.model.business.Discipline;

public class DisciplineRespond extends Discipline {
    private String studentName;

    private String claxxName;

    private String roomName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClaxxName() {
        return claxxName;
    }

    public void setClaxxName(String claxxName) {
        this.claxxName = claxxName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
