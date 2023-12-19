package org.example;

import org.example.db.DBConnection;
import org.example.member.MemberController;
import org.example.state.StateController;


public class App {
    MemberController memberController;
    StateController stateController;
    SystemController systemController;
    public App() {
        DBConnection.DB_NAME = "proj1";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDBConnection().connect();

        memberController = new MemberController();
        stateController = new StateController();
        systemController = new SystemController();
    }
    public void run() {
        System.out.println("◆◇◆◇◆◇◆◇ DH컴퍼니 WSMS에 오신 것을 환영합니다 ◆◇◆◇◆◇◆◇");
        System.out.println("                < 명령어 목록 >");
        System.out.println("           회원가입 / 로그인 / 로그아웃");
        System.out.println("        ID찾기 / PW찾기 / PW변경 / 회원탈퇴");
        System.out.println("  나의정보 / 직원정보 / 근태조회 / 근태변경 / 근무시간");
        System.out.println("          명령어 다시 보기 : '명령어' 입력");
        System.out.println("◆◇◆◇◆◇◆◇ Working State Management System ◆◇◆◇◆◇◆◇◆");


        while (true) {
            System.out.print("명령어 입력 ▶ ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "종료":
                    systemController.exit();
                    return;
                case "명령어":
                    systemController.commandList();
                    break;
                case "회원가입":
                    memberController.join();
                    break;
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
                case "직원정보":
                    memberController.memberInfo();
                    break;
                case "근태변경":
                    stateController.modify();
                    break;
                case "근태조회":
                    stateController.list();
                    break;
                case "근무시간":
                    stateController.workingSumTime();
                    break;
            }
        }
    }
}

// 자 오늘 할 일은 무어냐
// 학원에서 작업하던거 이어서
// 초급프로젝트 완성 시키고, test, 오류, 예외사항 자잘한 것들 detail 살리고 잡고 ㅇㅇ 디버깅