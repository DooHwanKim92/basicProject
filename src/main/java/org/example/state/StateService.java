package org.example.state;

import org.example.Container;
import org.example.member.Member;

import java.util.List;

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

    public void modifyState(int modifyNumber) {
        stateRepository.modifyState(modifyNumber);
    }

    public List<State> findByAll() {
        return this.stateRepository.findByAll();
    }

    public State stateFindById(int id) {
        return this.stateRepository.stateFindById(id);
    }
}