package org.example.member;

import org.example.Container;

public class MemberService {
    MemberRepository memberRepository;
    MemberService() {
        memberRepository = new MemberRepository();
    }
    public void join(String userId, String password,String name,int deptId,String email, String birthDate) {
        memberRepository.join(userId,password,name,deptId,email,birthDate);
    }
    public void login(Member checkedMember) {
        Container.setLoginedMember(checkedMember);
    }

    public void logout() {
        Container.setLoginedMember(null);
    }

    public void exit (String userId) {
        this.memberRepository.exit(userId);
    }
    public void info(String userId) {
        this.memberRepository.info(userId);
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
    public void modifyState(String modifyState) {
        memberRepository.modifyPw(modifyState);
    }

}