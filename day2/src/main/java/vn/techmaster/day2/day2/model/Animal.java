package vn.techmaster.day2.day2.model;

public class Animal {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
