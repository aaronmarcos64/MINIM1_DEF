package models;

import java.util.ArrayList;
import java.util.List;

public class Instituto {
    private String id;
    private String name;
    public Instituto() {};
    public Instituto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
