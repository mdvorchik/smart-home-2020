package ru.sbt.mipt.oop;

public class DoorImpl implements Door {
    private final String id;
    private boolean isOpen;

    public DoorImpl(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
