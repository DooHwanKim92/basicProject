package org.example.state;

import org.example.Container;
import org.example.member.Member;

import java.util.List;

public class StateService {
    StateRepository stateRepository;

    StateService() {
        stateRepository = new StateRepository();
    }
    public void modifyState(int modifyNumber) {
        stateRepository.modifyState(modifyNumber);
    }
    public State stateFindById(int id) {
        return this.stateRepository.stateFindById(id);
    }
    public Member stateFindByUserName(String memberName) {
        return this.stateRepository.stateFindByUserName(memberName);
    }
}