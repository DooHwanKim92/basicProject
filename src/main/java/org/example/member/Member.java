package org.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Member {
    private int id;
    private int deptId;
    private int stateId;
    private String stateName;
    private String name;
    private String position;
    private String regDate;
    private String email;
    private String birthDate;
    private String createdDate;
    private String modifiedDate;
}
