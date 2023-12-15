package org.example.state;

import org.example.Container;

public class StateController {
    StateService stateService;
    public StateController() {
        stateService = new StateService();
    }
    public void modify() {
        System.out.println("현재 근태 상태는 ["+Container.getLoginedMember()+"] 입니다.");
        System.out.print("변경할 근태 상태명 입력 ▶ ");
        String modifyState = Container.getSc().nextLine().trim();

        stateService.modify();

        System.out.println("현재 근태 상태가 변경되었습니다.");
    }
    public void listByMember() {
        System.out.println("특정직원/전체직원을 선택해주세요 ('특정' 또는 '전체' 입력) : ");
        String listMemberSelect = Container.getSc().nextLine().trim();
        if (listMemberSelect.equals("특정")) {
            System.out.print("직원 이름을 입력해주세요 : ");
            String memberName = Container.getSc().nextLine().trim();
            stateService.listByMember(memberName);
        } else if (listMemberSelect.equals("전체")) {
            stateService.listByMemberAll();
        }
    }
    public void listByDept() {
        System.out.println("특정부서/전체부서를 선택해주세요 ('특정' 또는 '전체' 입력) : ");
        String listDeptSelect = Container.getSc().nextLine().trim();
        if (listDeptSelect.equals("특정")) {
            System.out.println("========== DH컴퍼니 부서 현황 ==========");
            // 부서 명단 출력
            System.out.println("======================================");
            System.out.print("부서 이름을 입력해주세요 : ");
            String deptName = Container.getSc().nextLine().trim();
            stateService.listByDept(deptName);
        } else if (listDeptSelect.equals("전체")) {
            stateService.listByDeptAll();
        }
    }
}