package org.example.confirm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@Getter
@Setter
public class Confirm {
    @Getter
    @Setter
    private int id;
    private String title;
    private String date;
    private String reason;
    private int memberId;
    private int deptId;
    private String createdDate;
    private String modifiedDate;

    private String deptName;
    private String name;
    private String position;

    public Confirm(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.title = (String)row.get("title");
        this.date = (String)row.get("date");
        this.reason = (String)row.get("reason");
        this.memberId = (int)row.get("memberId");
        this.deptId = (int)row.get("deptId");
        this.createdDate = row.get("createdDate").toString();
        this.modifiedDate = row.get("modifiedDate").toString();
        this.name = (String)row.get("name");
        this.deptName = (String)row.get("deptName");
        this.position = (String)row.get("position");
    }

}