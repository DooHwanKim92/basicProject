package org.example.state;

import org.example.Container;
import org.example.member.Member;
import org.example.member.MemberService;

import java.time.Duration;
import java.util.InputMismatchException;
import java.util.List;

public class StateController {
    StateService stateService;
    MemberService memberService;

    public StateController() {
        stateService = new StateService();
        memberService = new MemberService();
    }

    public void list() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        System.out.print("직원별/부서별을 선택해주세요 ('직원' 또는 '부서' 입력) : ");
        String listCommand = Container.getSc().nextLine().trim();
        if (listCommand.equals("직원")) {
            this.listByMember();
        } else if (listCommand.equals("부서")) {
            this.listByDept();
        }
    }

    public void modify() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        Member member = stateService.stateFindByUserName(Container.getLoginedMember().getName());
        System.out.println("현재 근태 상태는 [" + member.getState() + "] 입니다.");
        System.out.println("변경 가능한 근태");
        System.out.println("=============");
        System.out.println(" 1. 출근\n 2. 회의\n 3. 교육\n 4. 출장\n 5. 휴가\n 6. 퇴근");
        System.out.println("=============");
        System.out.print("변경할 근태 번호 입력 : ");
        int modifyNumber = Container.getSc().nextInt();
        Container.getSc().nextLine();

        if (modifyNumber == Container.getLoginedMember().getStateId()) {
            System.out.println("<알림> 현재 근태와 같은 상태로 변경할 수 없습니다.");
            return;
        }

        stateService.modifyState(modifyNumber);

        State state = stateService.stateFindById(modifyNumber);

        System.out.println("<알림> 현재 근태가 [" + state.getState() + "]으로 변경되었습니다.");
    }

    public void listByMember() {
        System.out.print("특정직원/전체직원을 선택해주세요 ('특정' 또는 '전체' 입력) : ");
        String listMemberSelect = Container.getSc().nextLine().trim();
        if (listMemberSelect.equals("특정")) {

            System.out.println("검색 방식을 선택해주세요.\n 1. 이름 검색\n 2. 사번 검색");
            while (true) {
                System.out.print("선택('1'또는 '2'입력) : ");
                String choice = Container.getSc().nextLine().trim();
                if (choice.equals("1")) {
                    System.out.print("(직원정보)이름 입력 : ");
                    String memberName = Container.getSc().nextLine().trim();

                    if (stateService.memberFindByUserName(memberName) == null) {
                        System.out.println("<알림> 존재하지 않는 사용자입니다.");
                        return;
                    }

                    Member member = stateService.memberFindByUserName(memberName);
                    System.out.println("[" + member.getName() + " " + member.getPosition() + "]님의 현재 근태는 [" + member.getState() + "]입니다.");
                    break;
                } else if (choice.equals("2")) {
                    try {
                        System.out.print("(직원정보)사번 입력 : ");
                        int memberId = Container.getSc().nextInt();
                        Container.getSc().nextLine();
                        Member member = stateService.memberFindById(memberId);

                        if (member == null) {
                            System.out.println("<알림> 존재하지 않는 사용자입니다.");
                            return;
                        }

                        System.out.println("[" + member.getName() + " " + member.getPosition() + "]님의 현재 근태는 [" + member.getState() + "]입니다.");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("<알림> 사번은 숫자만 입력이 가능합니다.");
                        Container.getSc().nextLine();
                        return;
                    }
                } else {
                    System.out.println("<알림> 숫자 '1' 또는 '2'를 입력해주세요.");
                }
            }

        } else if (listMemberSelect.equals("전체")) {
            List<Member> memberList = memberService.findByAll();
            System.out.println("   ----------- DH 컴퍼니 직원 근태 현황 -----------");
            System.out.println(" 【 사원번호 / 이름 / 부서 / 직급 / 근태 / 변경시간 】 ");
            System.out.println("  ------------------------------------------------");
            for (Member member : memberList) {
                System.out.println(" 【 " + member.getId() + " / " + member.getName() + " / " + member.getDeptName() + " / " + member.getPosition() + " / " + member.getState() + " / " + member.getModifiedDate() + " 】");
            }
            System.out.println("  ------------------------------------------------");
        }
    }

    public void listByDept() {
        System.out.println("부서별 ID 번호");
        System.out.println("=============");
        System.out.println(" 1. 총무팀\n 2. 기획팀\n 3. 영업팀\n 4. R&D본부\n 5. 서비스본부\n 6. 유통팀\n 7. 생산팀");
        System.out.println("=============");
        System.out.print("부서 ID를 입력해주세요 : ");
        int deptId = Container.getSc().nextInt();
        Container.getSc().nextLine();
        List<Member> memberList = stateService.findByDept(deptId);
        Member deptMember = stateService.stateGroupByDept(deptId);
        System.out.println("   ------------[" + deptMember.getDeptName() + "] 근태 현황------------");
        System.out.println(" 【 사원번호 / 이름 / 직급 / 근태 / 변경시간 】 ");
        System.out.println("  ------------------------------------------");
        for (Member member : memberList) {
            System.out.println(" 【 " + member.getId() + " / " + member.getName() + " / " + member.getPosition() + " / " + member.getState() + " / " + member.getModifiedDate() + " 】");
        }
        System.out.println("  ------------------------------------------");
    }

    public void workingSumTime() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        System.out.println("---------- 근무시간 조회 ----------");
        System.out.println("검색 방식을 선택해주세요.\n 1. 이름 검색\n 2. 사번 검색\n 3. 전체 직원");
        while (true) {
            System.out.print("선택(숫자 입력) : ");
            String choice = Container.getSc().nextLine().trim();
            if (choice.equals("1")) {
                System.out.print("(근무시간)이름 입력 : ");
                String memberName = Container.getSc().nextLine().trim();

                Member member = stateService.memberFindByUserName(memberName);

                if (member == null) {
                    System.out.println("<알림> 존재하지 않는 사용자입니다.");
                    return;
                }
                Duration duration = memberService.workingSumTime(memberName);

                long hour = duration.toHours();
                long minute = (duration.toMinutes()%60);
                long sixtyminute = (duration.toMinutes()/60);

                System.out.println("[" + member.getName() + " " + member.getPosition() + "]님의 일일 근무 시간은 [" + (hour+sixtyminute) + " 시간 " + minute + " 분] 입니다.");
                System.out.println("---------------------------------");
                break;
            } else if (choice.equals("2")) {
                System.out.print("(근무시간)사번 입력 : ");
                int memberId = Container.getSc().nextInt();
                Container.getSc().nextLine();

                Member member = stateService.memberFindById(memberId);
                if (member == null) {
                    System.out.println("<알림> 존재하지 않는 사용자입니다.");
                    return;
                }

                Duration duration = memberService.workingSumTime(memberId);
                long hour = duration.toHours();
                long minute = (duration.toMinutes()%60);
                long sixtyminute = (duration.toMinutes()/60);

                System.out.println("[" + member.getName() + " " + member.getPosition() + "]님의 일일 근무 시간은 [" + (hour+sixtyminute) + " 시간 " + minute + " 분] 입니다.");
                System.out.println("---------------------------------");
                break;
            } else if (choice.equals("3")) {

                List<Member> memberList = memberService.findByAll();
                System.out.println("  ---------- 전직원 근무시간 현황 -----------");
                System.out.println(" 【 사원번호 / 이름 / 부서 / 직급 / 근무시간 】 ");
                System.out.println("  ------------------------------------------");
                for (Member member : memberList) {
                    Duration duration = memberService.workingSumTime(member.getId());
                    long hour = duration.toHours();
                    long minute = (duration.toMinutes()%60);
                    long sixtyminute = (duration.toMinutes()/60);

                    System.out.println(" 【 " + member.getId() + " / " + member.getName() + " / " + member.getDeptName() + " / " + member.getPosition() + " / " + (hour+sixtyminute) + " 시간 " + minute + " 분 】");
                }
                System.out.println("  ------------------------------------------");
                break;
            }
            else {
                System.out.println("<알림> 숫자 '1~3' 중 하나를 입력해주세요.");
            }
        }
    }
}