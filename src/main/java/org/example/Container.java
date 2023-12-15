package org.example;

import org.example.db.DBConnection;
import org.example.member.Member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Container {
    private static Scanner sc;
    private static Member loginedMember;
    private static DBConnection dbConnection;
    public static void initSc() {
        sc = new Scanner(System.in);
    }
    public static void closeSc() {
        sc.close();
    }
    public static Scanner getSc() {
        return sc;
    }
    public static void setLoginedMember(Member member) {
        Container.loginedMember = member;
    }
    public static Member getLoginedMember() {
        return Container.loginedMember;
    }
    public static DBConnection getDBConnection () {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }
    public static String nowDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();

        return format.format(time);
    }
}
