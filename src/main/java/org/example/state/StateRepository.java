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
    public void modifyState(int modifyNumber) {
        memberRepository.modifyState(modifyNumber);
    }
    public Member stateFindByUserName(String memberName) {
        return memberRepository.stateFindByUserName(memberName);
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
    public Member memberFindByUserName(String userName) {
        return this.memberRepository.memberFindByUserName(userName);
    }
    public List<Member> findByDept(int deptId) {
        return this.memberRepository.findByDept(deptId);
    }
    public Member stateGroupByDept(int deptId) {
        return this.memberRepository.stateGroupByDept(deptId);
    }
    public Member memberFindById(int id) {
        return this.memberRepository.memberFindById(id);
    }
}