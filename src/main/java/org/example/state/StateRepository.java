package org.example.state;

public class StateRepository {
    public void modify() {
        return;
    }
    public void listByMember(String memberName) {
        stateFindByName(memberName);
    }
    public void listByMemberAll() {
        stateFindByMemberAll();
    }
    public void listByDept(String deptName) {
        stateFindByDept(deptName);
    }
    public void listByDeptAll() {
        stateFindByDeptAll();
    }
    public void stateFindByName(String memberName) {}
    public void stateFindByMemberAll() {}
    public void stateFindByDept(String deptName) {}
    public void stateFindByDeptAll() {}
}
