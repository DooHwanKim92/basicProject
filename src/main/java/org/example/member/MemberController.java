package org.example.member;

import org.example.Container;

public class MemberController {
    MemberService memberService;
    MemberController() {
        memberService = new MemberService();
    }
    public void join() {
        System.out.print("(회원가입)ID 입력 : ");
        String userId = Container.getSc().nextLine().trim();
        String checkedPassword;

        while(true) {
            System.out.print("(회원가입)PW 입력 : ");
            String password = Container.getSc().nextLine().trim();
            System.out.print("(회원가입)PW 확인 : ");
            String checkPassword = Container.getSc().nextLine().trim();

            if (!password.equals(checkPassword)) {
                System.out.println("비밀번호 확인이 맞지 않습니다.");
                continue;
            }
            checkedPassword = password;
            break;
        }

        System.out.print("(회원가입)이름 입력 : ");
        String name = Container.getSc().nextLine().trim();

        System.out.print("(회원가입)이메일 입력 : ");
        String email = Container.getSc().nextLine().trim();

        System.out.print("(회원가입)생년월일 입력(YYYY.MM.DD) : ");
        String birthDate = Container.getSc().nextLine().trim();

        memberService.join(userId,checkedPassword,name,email,birthDate);

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
