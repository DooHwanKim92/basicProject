package org.example;

import org.example.member.MemberController;
import org.example.state.StateController;

public class App {
    MemberController memberController;
    StateController stateController;
    App() {
        memberController = new MemberController();
        stateController = new StateController();
    }
    public void run() {
        System.out.println("◆◇◆◇◆◇◆◇ DH컴퍼니 WSMS에 오신 것을 환영합니다 ◆◇◆◇◆◇◆◇");
        System.out.println("◆◇◆◇◆◇◆◇ Working State Management System ◆◇◆◇◆◇◆◇");

        while (true) {
            System.out.print("명령어 입력 ▶ ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "회원가입":
                    memberController.join();
                    break;
                case "종료":
                    System.out.println("        ◆◇◆◇◆◇◆◇ WSMS를 종료합니다 ◆◇◆◇◆◇◆◇");
                    return;
                case "로그인":
                    memberController.logIn();
                    break;
                case "로그아웃":
                    memberController.logOut();
                    break;
                case "회원탈퇴":
                    memberController.exit();
                    break;
                case "ID찾기":
                    memberController.findId();
                    break;
                case "PW찾기":
                    memberController.findPw();
                    break;
                case "PW변경":
                    memberController.modifyPw();
                    break;
                case "나의정보":
                    memberController.info();
                    break;
                case "근태변경":
                    stateController.modify();
                    break;
                case "근태조회":
                    System.out.println("직원별/부서별을 선택해주세요 ('직원' 또는 '부서' 입력) : ");
                    String listCommand = Container.getSc().nextLine().trim();
                    if (listCommand.equals("직원")) {
                        stateController.listByMember();
                    } else if (listCommand.equals("부서")) {
                        stateController.listByDept();
                    }
                    break;
            }
        }
    }
}