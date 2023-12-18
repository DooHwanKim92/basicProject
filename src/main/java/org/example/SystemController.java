package org.example;

public class SystemController {
    public void exit() {
        System.out.println("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇ WSMS를 종료합니다 ◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
    }
    public void commandList() {
        System.out.println("=================================================");
        System.out.println("                < 명령어 목록 >");
        System.out.println("          '회원가입' '로그인' '로그아웃'");
        System.out.println("           '회원탈퇴' 'ID찾기' 'PW찾기'");
        System.out.println("          '나의정보' '근태변경' '근태조회'");
        System.out.println("          명령어 다시 보기 : '명령어' 입력");
        System.out.println("=================================================");
    }
}
