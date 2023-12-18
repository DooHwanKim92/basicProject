package org.example.state;

import org.example.Container;
import org.example.member.Member;
import org.example.member.MemberRepository;

public class StateController {
    StateService stateService;
    MemberRepository memberRepository;
    public StateController() {
        stateService = new StateService();
        memberRepository = new MemberRepository();
    }
    public void modify() {
        Member member = memberRepository.stateFindByUserName(Container.getLoginedMember().getName());
        System.out.println("현재 근태 상태는 ["+member.getState()+"] 입니다.");
        System.out.println("변경 가능한 근태");
        System.out.println("=============");
        System.out.println(" 1. 출근\n 2. 회의\n 3. 교육\n 4. 출장\n 5. 휴가\n 6. 퇴근");
        System.out.println("=============");
        System.out.print("변경할 근태 번호명 입력 : ");
        int modifyNumber = Container.getSc().nextInt();
        Container.getSc().nextLine();

        stateService.modifyState(modifyNumber);
        State state = stateService.stateFindById(modifyNumber);

        System.out.println("<알림> 현재 근태 상태가 ["+state.getState()+"]으로 변경되었습니다.");
    }
    public void listByMember() {
        System.out.print("특정직원/전체직원을 선택해주세요 ('특정' 또는 '전체' 입력) : ");
        String listMemberSelect = Container.getSc().nextLine().trim();
        if (listMemberSelect.equals("특정")) {
            System.out.print("직원 이름을 입력해주세요 : ");
            String memberName = Container.getSc().nextLine().trim();

            if(memberRepository.memberFindByUserName(memberName)==null) {
                System.out.println("<알림> 존재하지 않는 사용자입니다.");
                return;
            }

            Member member = memberRepository.stateFindByUserName(memberName);
            System.out.println("["+ memberName + "]님의 현재 근태 상태는 ["+ member.getState()+"] 입니다.");
            stateService.listByMember(memberName);
        } else if (listMemberSelect.equals("전체")) {
            stateService.listByMemberAll();
        }
    }
    public void listByDept() {
        System.out.print("특정부서/전체부서를 선택해주세요 ('특정' 또는 '전체' 입력) : ");
        String listDeptSelect = Container.getSc().nextLine().trim();
        if (listDeptSelect.equals("특정")) {
            System.out.println("부서별 ID 번호");
            System.out.println("=============");
            System.out.println(" 1. 총무팀\n 2. 기획팀\n 3. 영업팀\n 4. R&D본부\n 5. 서비스본부\n 6. 유통팀\n 7. 생산팀");
            System.out.println("=============");
            System.out.print("부서 ID를 입력해주세요 : ");
            String deptName = Container.getSc().nextLine().trim();
            stateService.listByDept(deptName);
        } else if (listDeptSelect.equals("전체")) {
            stateService.listByDeptAll();
        }
    }
}