package org.snow.form;

import org.snow.model.business.Student;

public class StudentRespond extends Student {

    private String roomName;

    private String claxxName;

    private String statusName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getClaxxName() {
        return claxxName;
    }

    public void setClaxxName(String claxxName) {
        this.claxxName = claxxName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
