package ru.psu.taskforjob.dto;

public class org {
    private int id;
    private String Name;
    private int headId;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getHeadId() {
        return headId;
    }

    public org() {
    }

    public org(int id, String name, int headId) {
        this.id = id;
        Name = name;
        this.headId = headId;
    }
}
