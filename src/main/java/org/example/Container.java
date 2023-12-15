package org.example;

import org.example.member.Member;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static Member member;
    public static void initSc() {
        sc = new Scanner(System.in);
    }
    public static Scanner getSc() {
        return sc;
    }
    public static Member getLoginedMember() {
        return Container.member;
    }
}
