package org.example.state;

import org.example.Container;
import org.example.member.Member;
import org.example.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateRepository {
    MemberRepository memberRepository;
    StateRepository() {
        memberRepository = new MemberRepository();
    }
    public void modify() {
        String sql = String.format("update `state` set modifiedDate = NOW() where id = %d;",Container.getLoginedMember().getStateId());
        // 조회만 해도 바꿔버리네??
        // 왜 또 안바뀌냐
        Container.getDBConnection().update(sql);
    }
    public void listByMember(String memberName) {
        stateFindByName(memberName);
    }
    public void listByMemberAll() {
    }
    public void listByDept(String deptName) {
        stateFindByDept(deptName);
    }
    public void listByDeptAll() {

    }
    public void stateFindByName(String memberName) {}
    public void stateFindByMemberAll() {}
    public void stateFindByDept(String deptName) {}
    public void stateFindByDeptAll() {}
    public void modifyState(int modifyNumber) {
        memberRepository.modifyState(modifyNumber);
    }
    public List<State> findByAll() {
        List<State> stateList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("select * from `state`");

        for (Map<String, Object> row : rows) {
            State state = new State(row);
            stateList.add(state);
        }
        return stateList;
    }
    public State stateFindById(int id) {
        List<State> stateList = this.findByAll();
        for (State state : stateList) {
            if (id == state.getId()) {
                return state;
            }
        }
        return null;
    }

}