package org.example.state;

import org.example.Container;

public class StateService {
    StateRepository stateRepository;
    StateService() {
        stateRepository = new StateRepository();
    }
    public void modify() {
        stateRepository.modify();
    }
    public void listByMember(String memberName) {
        stateRepository.listByMember(memberName);
    }
    public void listByMemberAll() {
        stateRepository.listByMemberAll();
    }
    public void listByDept(String deptName) {
        stateRepository.listByDept(deptName);
    }
    public void listByDeptAll() {
        stateRepository.listByDeptAll();
    }
}
