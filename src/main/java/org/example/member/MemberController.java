package org.example.member;

import org.example.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void join() {
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }
        String userId;
        String checkedPassword;
        while (true) {
            System.out.print("(회원가입)ID 입력 : ");
            userId = Container.getSc().nextLine().trim();
            boolean isDuplicated = true;

            Member member = memberService.memberFindByUserId(userId);

            if (member != null) {
                System.out.println("<알림> 중복 아이디가 존재합니다.");
                isDuplicated = false;
            }
            if (isDuplicated) break;
        }


        while (true) {
            System.out.print("(회원가입)PW 입력 : ");
            String password = Container.getSc().nextLine().trim();
            System.out.print("(회원가입)PW 확인 : ");
            String checkPassword = Container.getSc().nextLine().trim();

            if (!password.equals(checkPassword)) {
                System.out.println("<알림> 비밀번호 확인이 맞지 않습니다.");
                continue;
            }
            checkedPassword = password;
            break;
        }

        System.out.print("(회원가입)이름 입력 : ");
        String name = Container.getSc().nextLine().trim();

        System.out.println("부서별 ID 번호");
        System.out.println("=============");
        System.out.println(" 1. 총무팀\n 2. 기획팀\n 3. 영업팀\n 4. R&D본부\n 5. 서비스본부\n 6. 유통팀\n 7. 생산팀");
        System.out.println("=============");
        System.out.print("(회원가입)부서번호 입력 : ");
        int deptId = Container.getSc().nextInt();

        Container.getSc().nextLine();

        System.out.print("(회원가입)이메일 입력 : ");
        String email = Container.getSc().nextLine().trim();

        System.out.print("(회원가입)생년월일 입력(YYYY.MM.DD) : ");
        String birthDate = Container.getSc().nextLine().trim();

        memberService.join(userId, checkedPassword, name, deptId, email, birthDate);

        System.out.println("[" + userId + "] 님 회원가입 성공 !!");
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

        if (!email.equals(Container.getLoginedMember().getEmail())) {
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
        Member member = this.memberService.info(Container.getLoginedMember().getName());

        System.out.println("==========================");
        System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n7. 근태 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptName(),member.getPosition(),member.getRegDate(),member.getState());
        System.out.println("==========================");
    }

    public void memberInfo() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        System.out.println("검색 방식을 선택해주세요.\n 1. 이름 검색\n 2. 사번 검색");
        while(true) {
            System.out.print("선택('1'또는 '2'입력) : ");
            String choice = Container.getSc().nextLine().trim();
            if (choice.equals("1")) {
                System.out.print("(직원정보)이름 입력 : ");
                String memberName = Container.getSc().nextLine().trim();
                Member member = this.memberService.info(memberName);
                System.out.println("==========================");
                System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n7. 근태 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptName(),member.getPosition(),member.getRegDate(),member.getState());
                System.out.println("==========================");
                break;
            } else if (choice.equals("2")) {
                System.out.print("(직원정보)사번 입력 : ");
                int memberId = Container.getSc().nextInt();
                Container.getSc().nextLine();
                Member member = this.memberService.info(memberId);
                System.out.println("==========================");
                System.out.printf("1. 이름 : %s\n2. 사번 : %d\n3. 이메일주소 : %s\n4. 소속부서 : %s\n5. 직급 : %s\n6. 입사일자 : %s\n7. 근태 : %s\n",member.getName(),member.getId(),member.getEmail(),member.getDeptName(),member.getPosition(),member.getRegDate(),member.getState());
                System.out.println("==========================");
                break;
            } else {
                System.out.println("<알림> 숫자 '1' 또는 '2'를 입력해주세요.");
            }
        }
    }
    public void findId() {
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }
        System.out.print("(ID찾기)사용자 이름 입력 : ");
        String userName = Container.getSc().nextLine().trim();

        Member member = this.memberService.memberFindByUserName(userName);

        if (member == null) {
            System.out.println("<알림> 존재하지 않는 사용자입니다.");
            return;
        }

        System.out.print("(ID찾기)이메일 주소 입력 : ");
        String userEmail = Container.getSc().nextLine().trim();

        if (!userEmail.equals(member.getEmail())) {
            System.out.println("<알림 > 이메일이 일치하지 않습니다.");
            return;
        }

        System.out.println(userName + "님의 ID는 [" + member.getUserId() + "] 입니다.");

    }

    public void findPw() {
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }
        System.out.print("(PW찾기)사용자 ID 입력 : ");
        String userId = Container.getSc().nextLine().trim();

        Member member = this.memberService.memberFindByUserId(userId);

        if (member == null) {
            System.out.println("<알림> 존재하지 않는 사용자입니다.");
            return;
        }

        System.out.print("(ID찾기)이메일 주소 입력 : ");
        String userEmail = Container.getSc().nextLine().trim();

        if (!userEmail.equals(member.getEmail())) {
            System.out.println("<알림 > 이메일이 일치하지 않습니다.");
            return;
        }

        System.out.println(userId + "님의 PW는 [" + member.getPassword() + "] 입니다.");

    }

    public void modifyPw() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }

        String modifyPassword;
        String modifyCheckPassword;

        System.out.print("(PW변경)기존 PW 입력 : ");
        String password = Container.getSc().nextLine().trim();

        if (!password.equals(Container.getLoginedMember().getPassword())) {
            System.out.println("<알림> 비밀번호를 잘못 입력했습니다.");
            return;
        }

        System.out.print("(PW변경)기존 PW 확인 : ");
        String checkPassword = Container.getSc().nextLine().trim();

        if (!password.equals(checkPassword)) {
            System.out.println("<알림> 비밀번호 확인을 잘못 입력했습니다.");
            return;
        }

        while (true) {
            System.out.print("(PW변경)변경 PW 입력 : ");
            modifyPassword = Container.getSc().nextLine().trim();
            System.out.print("(PW변경)변경 PW 확인 : ");
            modifyCheckPassword = Container.getSc().nextLine().trim();

            if (!modifyPassword.equals(modifyCheckPassword)) {
                System.out.println("<알림> 비밀번호 확인을 잘못 입력했습니다.");
                continue;
            }
            break;
        }

        this.memberService.modifyPw(modifyPassword);

        System.out.println("<알림> 비밀번호 변경 완료!!");
        this.memberService.logout();
        System.out.println("<알림> 로그아웃 되었습니다 변경된 PW로 로그인 해주세요.");

    }
}