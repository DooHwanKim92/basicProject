package org.example.member;

import org.example.Container;

public class MemberController {
    MemberService memberService;
    public MemberController() {
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

        System.out.println("부서별 ID 번호");
        System.out.println("=============");
        System.out.print("(회원가입)부서번호 입력 : ");
        int deptId = Container.getSc().nextInt();

        Container.getSc().nextLine();

        System.out.print("(회원가입)이메일 입력 : ");
        String email = Container.getSc().nextLine().trim();

        System.out.print("(회원가입)생년월일 입력(YYYY.MM.DD) : ");
        String birthDate = Container.getSc().nextLine().trim();

        memberService.join(userId,checkedPassword,name,deptId,email,birthDate);

    }
    public void logIn() {
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }

        System.out.print("(로그인)ID 입력 : ");
        String userId = Container.getSc().nextLine().trim();
        System.out.print("(로그인)PW 입력 : ");
        String password = Container.getSc().nextLine().trim();

        Member member = this.memberService.memberFindByUserId(userId);

        if (member == null) {
            System.out.println("<알림> 존재하지 않는 ID 입니다.");
            return;
        } else if (!password.equals(member.getPassword())) {
            System.out.println("<알림> 비밀번호가 일치하지 않습니다.");
            return;
        }
        this.memberService.login(member);
        Container.setLoginedMember(member);

        System.out.println("◈◈◈◈◈ [" + member.getName() + "]님 환영합니다 ◈◈◈◈◈");
    }
    public void logOut() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }

        System.out.println("<알림> 로그아웃 되었습니다.");

        this.memberService.logout();
    }
    public void exit() {
        String password;
        String checkPassword;
        String email;
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        System.out.print("(회원탈퇴)PW 입력 : ");
        password = Container.getSc().nextLine().trim();

        if (!password.equals(Container.getLoginedMember().getPassword())) {
            System.out.println("<알림> 비밀번호를 잘못 입력했습니다.");
            return;
        }

        System.out.print("(회원탈퇴)PW 확인 : ");
        checkPassword = Container.getSc().nextLine().trim();

        if (!password.equals(checkPassword)) {
            System.out.println("<알림> 비밀번호 확인을 잘못 입력했습니다.");
            return;
        }

        System.out.print("(회원탈퇴)이메일 주소 입력 : ");
        email = Container.getSc().nextLine().trim();

        if(!email.equals(Container.getLoginedMember().getEmail())) {
            System.out.println("<알림> 이메일 주소가 맞지 않습니다.");
            return;
        }

        if (password.equals(Container.getLoginedMember().getPassword())) {
            System.out.print("<알림> 정말 탈퇴하시겠습니까? (y/n)입력 ▶ ");
            String yesOrNo = Container.getSc().nextLine().trim();
            if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {

                memberService.exit(Container.getLoginedMember().getUserId());

                System.out.println("\n◈◈◈◈◈ 회원 탈퇴가 정상처리 되었습니다 ◈◈◈◈◈\n");
                this.memberService.logout();
                System.out.println("<알림> 로그아웃 되었습니다.");
            } else if (yesOrNo.equals("n") || yesOrNo.equals("N")) {
                System.out.println("<알림> 회원 탈퇴를 취소하셨습니다.");
            } else {
                System.out.println("<알림> y,Y 또는 n,N을 입력해주셔야 합니다.");
            }
        } else {
            System.out.println("<알림> 비밀번호를 잘못 입력했습니다.");
        }
    }
    public void info() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        this.memberService.info(Container.getLoginedMember().getUserId());
    }
    public void findId() {

    }
    public void findPw() {

    }
    public void modifyPw() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
    }
}