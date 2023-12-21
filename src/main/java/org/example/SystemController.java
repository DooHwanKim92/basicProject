package org.example;

public class SystemController {
    public void exit() {
        System.out.println("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇ WSMS를 종료합니다 ◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
    }
    public void commandList() {
        System.out.println("=================================================");
        System.out.println("                < 명령어 목록 >");
        System.out.println("           회원가입 / 로그인 / 로그아웃");
        System.out.println("        ID찾기 / PW찾기 / PW변경 / 회원탈퇴");
        System.out.println("        메일발송 / 결재 / 나의정보 / 직원정보");
        System.out.println("           근태조회 / 근태변경 / 근무시간");
        System.out.println("          명령어 다시 보기 : '명령어' 입력");
        System.out.println("=================================================");
    }
    public void sendEmail() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }

        System.out.println("===== <주의> NAVER 메일만 사용이 가능합니다 =====");

        System.out.print("(메일발송)수신자 이메일 주소 입력 : ");
        String emailAddress = Container.getSc().nextLine().trim();
        System.out.print("(메일발송)메일 제목 입력 : ");
        String title = Container.getSc().nextLine().trim();
        System.out.print("(메일발송)메일 내용 입력 : ");
        String text = Container.getSc().nextLine().trim();

        SendMail.naverMailSend(emailAddress,title,text);
    }
}