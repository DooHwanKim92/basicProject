package org.example.member;

import org.example.Container;
import org.example.state.State;

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
    public void modifyPw(String modifyPw) {
        String sql = String.format("update member set password = '%s' where id = %d",modifyPw, Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
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

    public Member memberFindByUserName(String userName) {
        List<Member> memberList = this.findByAll();
        for (Member member : memberList) {
            if (userName.equals(member.getName())) {
                return member;
            }
        }
        return null;
    }
    public List<Member> findByState() {
        List<Member> memberList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("SELECT \n" +
                "`member`.id,\n" +
                "`member`.deptId,\n" +
                "`member`.stateId,\n" +
                "`state`.state,\n" +
                "`member`.name,\n" +
                "`member`.userId,\n" +
                "`member`.password,\n" +
                "`member`.regDate,\n" +
                "`member`.`position`,\n" +
                "`member`.email,\n" +
                "`member`.birthDate\n" +
                "FROM `member`\n" +
                "INNER JOIN `state`\n" +
                "ON `member`.stateId = `state`.id;");

        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }
    public Member stateFindByUserName(String userName) {
        List<Member> memberList = this.findByState();
        for (Member member : memberList) {
            if (userName.equals(member.getName())) {
                return member;
            }
        }
        return null;
    }
    public void modifyState(int modifyNumber) {
        String sql = String.format("update member set `stateId` = %d where id = %d;",modifyNumber, Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
    }
}