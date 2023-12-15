package org.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Member {
    private int id;
    private int deptId;
    private int stateId;
    private String userId;
    private String password;
    private String name;
    private String position;
    private String regDate;
    private String email;
    private String birthDate;
    private String createdDate;
    private String modifiedDate;

    public Member(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.deptId = (int)row.get("deptId");
        this.stateId = (int)row.get("stateId");
        this.userId = (String)row.get("userId");
        this.password = (String)row.get("password");
        this.name = (String)row.get("name");
        this.position = (String)row.get("position");
        this.regDate = row.get("regDate").toString();
        this.email = (String)row.get("email");
        this.birthDate = (String)row.get("birthDate");
        this.createdDate = (String)row.get("createdDate");
        this.modifiedDate = (String)row.get("modifiedDate");
    }
}