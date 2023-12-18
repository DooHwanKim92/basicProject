package org.example;

import org.example.db.DBConnection;
import org.example.Container;
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
        System.out.println("          '회원가입' '로그인' '로그아웃'");
        System.out.println("       'ID찾기' 'PW찾기' 'PW변경' '회원탈퇴'");
        System.out.println("          '나의정보' '근태조회' '근태변경'");
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
                case "회원가입": // 로그아웃 검증
                    memberController.join();
                    break;
                case "로그인": // 로그아웃 검증
                    memberController.logIn();
                    break;
                case "로그아웃": // 로그인 검증
                    memberController.logOut();
                    break;
                case "회원탈퇴": // 로그인 검증
                    memberController.exit();
                    break;
                case "ID찾기": // 로그인 검증
                    memberController.findId();
                    break;
                case "PW찾기": // 로그아웃 검증
                    memberController.findPw();
                    break;
                case "PW변경": // 로그인 검증
                    memberController.modifyPw();
                    break;
                case "나의정보": // 로그인 검증
                    memberController.info();
                    break;
                case "근태변경": // 로그인 검증
                    stateController.modify();
                    break;
                case "근태조회": // 로그인 검증
                    System.out.print("직원별/부서별을 선택해주세요 ('직원' 또는 '부서' 입력) : ");
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