package org.example.member;

import org.example.Container;
import org.example.state.State;
import org.w3c.dom.ls.LSOutput;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    public void join(String userId, String password,String name,int deptId,String email, String birthDate) {
        String sql = String.format("insert into member set userId = '%s', password = '%s', name = '%s', deptId =%d, email = '%s', birthDate = '%s',position = '사원', regDate = NOW(), stateId = 6, createdDate = NOW(), modifiedDate = NOW(), workStartTime = NOW(), workEndTime = NOW();", userId, password,name,deptId,email,birthDate);

        Container.getDBConnection().insert(sql);
    }
    public void exit(String userId) {
        String sql = String.format("delete from member where userId = '%s'", userId);

        Container.getDBConnection().delete(sql);
    }
    public void info(String userName) {
        Member member = this.stateFindByUserName(userName);
        System.out.println("==========================");
        System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n7. 근태 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptName(),member.getPosition(),member.getRegDate(),member.getState());
        System.out.println("==========================");
    }
    public void info(int memberId) {
        Member member = this.stateFindByUserId(memberId);
        System.out.println("==========================");
        System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n7. 근태 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptName(),member.getPosition(),member.getRegDate(),member.getState());
        System.out.println("==========================");
    }
    public void login() {
        String sql = String.format("update member set stateId = 1, modifiedDate = NOW(), workStartTime = NOW() where id = %d", Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
    }
    public void logout() {
        String sql = String.format("update member set stateId = 6, modifiedDate = NOW(), workEndTime = NOW() where id = %d", Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
    }
    public void modifyPw(String modifyPw) {
        String sql = String.format("update member set password = '%s' where id = %d",modifyPw, Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
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
    public List<Member> findByAll() {
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
                "`member`.birthDate,\n" +
                "`member`.createdDate,\n" +
                "`member`.modifiedDate,\n" +
                "`dept`.deptName,\n" +
                "`member`.workStartTime,\n" +
                "`member`.workEndTime\n" +
                "FROM `member`\n" +
                "INNER JOIN `state`\n" +
                "ON `member`.stateId = `state`.id\n" +
                "INNER JOIN `dept`\n" +
                "ON `member`.deptId = `dept`.id;");

        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }
    public List<Member> findByDept(int deptId) {
        List<Member> memberList = new ArrayList<>();
        String sql = String.format("SELECT \n" +
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
                "`member`.birthDate,\n" +
                "`member`.createdDate,\n" +
                "`member`.modifiedDate,\n" +
                "`dept`.deptName,\n" +
                "`member`.workStartTime,\n" +
                "`member`.workEndTime\n" +
                "FROM `member`\n" +
                "INNER JOIN `state`\n" +
                "ON `member`.stateId = `state`.id\n" +
                "INNER JOIN `dept`\n" +
                "ON `member`.deptId = `dept`.id\n" +
                "WHERE `dept`.id = %d;",deptId);
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }
    public Member stateFindByUserName(String userName) {
        List<Member> memberList = this.findByAll();
        for (Member member : memberList) {
            if (userName.equals(member.getName())) {
                return member;
            }
        }
        return null;
    }
    public Member stateFindByUserId(int userId) {
        List<Member> memberList = this.findByAll();
        for (Member member : memberList) {
            if (userId == member.getId()) {
                return member;
            }
        }
        return null;
    }
    public Member stateGroupByDept(int deptId) {
        List<Member> memberList = this.findByDept(deptId);
        for (Member member : memberList) {
            if (deptId == member.getDeptId()) {
                return member;
            }
        }
        return null;
    }
    public void modifyState(int modifyNumber) {
        String sql = String.format("update member set `stateId` = %d, `modifiedDate`=NOW() where id = %d;",modifyNumber, Container.getLoginedMember().getId());

        Container.getDBConnection().update(sql);
    }
    public Duration workingSumTime(String userName) {
        Member member = memberFindByUserName(userName);
        String startTime = member.getWorkStartTime();
        String endTime = member.getWorkEndTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateStartTime = LocalDateTime.parse(startTime,formatter);
        LocalDateTime dateEndTime = LocalDateTime.parse(endTime,formatter);

        return Duration.between(dateStartTime,dateEndTime);
    }
}