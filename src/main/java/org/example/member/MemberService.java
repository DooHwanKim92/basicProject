package org.example.member;

public class MemberService {
    MemberRepository memberRepository;
    MemberService() {
        memberRepository = new MemberRepository();
    }
    public void join(String userId, String password,String name,String email, String birthDate) {
        memberRepository.join(userId,password,name,email,birthDate);
    }
    public void logIn() {

    }
    public void logOut() {

    }
    public void exit() {

    }
    public void info() {

    }
    public void findId() {

    }
    public void findPw() {

    }
    public void modifyPw() {

    }
}
