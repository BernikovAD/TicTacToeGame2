package com.bernikov;

public class Cell {
    char field;
    boolean Shot= false;

    public boolean isShot() { return Shot; }

    public void setShot(boolean shot) { Shot = shot; }

    public Cell() { }

    public void setField(char field) {
        this.field = field;
    }

    public char getField() {
        return this.field;
    }


}
