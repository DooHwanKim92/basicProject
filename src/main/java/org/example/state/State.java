package org.example.state;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class State {
    private int id;
    private String state;
    private String createdDate;
    private String modifiedDate;
    public State(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.state = (String)row.get("state");
        this.createdDate = row.get("createdDate").toString();
        this.modifiedDate = row.get("modifiedDate").toString();
    }
}