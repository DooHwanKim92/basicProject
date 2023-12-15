package org.example.member;

import org.example.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    public void join(String userId, String password,String name,int deptId,String email, String birthDate) {
        String sql = String.format("insert into member set userId = '%s', password = '%s', name = '%s', deptId =%d, email = '%s', birthDate = '%s',position = '사원', regDate = NOW(), stateId = 1;", userId, password,name,deptId,email,birthDate);

        Container.getDBConnection().insert(sql);
    }
    public void exit(String userId) {
        String sql = String.format("delete from member where userId = '%s'", userId);

        Container.getDBConnection().delete(sql);
    }
    public void info(String userId) {
        Member member = this.memberFindByUserId(userId);
        System.out.println("==========================");
        System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptId(),member.getPosition(),member.getRegDate());
        System.out.println("==========================");
    }
    public void findId() {

    }
    public void findPw() {

    }
    public void modifyPw() {

    }
    public List<Member> findByAll() {
        List<Member> memberList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("select * from member");

        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }
    public Member memberFindByUserId(String userId) {
        List<Member> memberList = this.findByAll();
        for (Member member : memberList) {
            if (userId.equals(member.getUserId())) {
                return member;
            }
        }
        return null;
    }
}