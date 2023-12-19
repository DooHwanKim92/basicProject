package org.example.member;

import org.example.Container;

import java.time.Duration;
import java.util.List;

public class MemberService {
    MemberRepository memberRepository;
    public MemberService() {
        memberRepository = new MemberRepository();
    }
    public void join(String userId, String password,String name,int deptId,String email, String birthDate) {
        memberRepository.join(userId,password,name,deptId,email,birthDate);
    }
    public void login(Member checkedMember) {
        Container.setLoginedMember(checkedMember);
        memberRepository.login();
    }
    public void logout() {
        memberRepository.logout();
        Container.setLoginedMember(null);
    }
    public void exit (String userId) {
        this.memberRepository.exit(userId);
    }
    public void info(String userName) {
        this.memberRepository.info(userName);
    }
    public void info(int memberId) {
        this.memberRepository.info(memberId);
    }
    public void modifyPw(String modifyPw) {
        memberRepository.modifyPw(modifyPw);
    }
    public Member memberFindByUserId(String userId) {
        return this.memberRepository.memberFindByUserId(userId);
    }
    public Member memberFindByUserName(String userName) {
        return this.memberRepository.memberFindByUserName(userName);
    }
    public List<Member> findByAll() {
        return this.memberRepository.findByAll();
    }
    public Duration workingSumTime(String userName) {
        return this.memberRepository.workingSumTime(userName);
    }
}