package entities.models;

import entities.enums.Turn;

public class Classroom {
    private String room;
    private String ID;
    private Supplies supplies;
    private Teacher teacher;
    private Turn turn;
    public Classroom(String room, String ID, Supplies supplies,Turn turn , Teacher teacher) {
        this.room = room;
        this.ID = ID;
        this.supplies = supplies;
        this.teacher = teacher;
        this.turn = turn;
    }

    public Classroom(String room, String ID, Supplies supplies, Turn turn) {
        this.room = room;
        this.ID = ID;
        this.supplies = supplies;
        this.turn = turn;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Supplies getSupplies() {
        return supplies;
    }

    public void setSupplies(Supplies supplies) {
        this.supplies = supplies;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
