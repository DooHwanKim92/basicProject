package org.example.confirm;

import org.example.Container;
import org.example.member.Member;

import java.util.List;

public class ConfirmController {
    ConfirmService confirmService;

    public ConfirmController() {
        confirmService = new ConfirmService();
    }

    public void confirm() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }

        if (Container.getLoginedMember().getPosition().equals("대표")) {
            System.out.println("<알림> 관리자 결재함으로 이동합니다.");
            while(true) {
                System.out.println("====================== 결재 서류 목록 ======================");
                System.out.println("【 문서번호 / 결재종류 / 신청자 / 직급 /  소속부서 / 신청날짜】");
                List<Confirm> confirmList = confirmService.findByList();
                for (Confirm confirm : confirmList) {
                    System.out.println("【 " + confirm.getId() + " / " + confirm.getTitle() + " / " + confirm.getName() + " / " + confirm.getPosition() + " / " + confirm.getDeptName() + " / " + confirm.getModifiedDate() + "】");
                }
                System.out.println("===========================================================");
                System.out.print("결재하실 문서번호를 입력해주세요 : ");
                int confirmNumber = Container.getSc().nextInt();
                Container.getSc().nextLine();

                Confirm confirm = confirmService.confirmFindById(confirmNumber);

                System.out.println("[ "+confirmNumber + "번 결재 서류 정보 ]");
                System.out.println("=======================");
                System.out.println("결재 종류 : "+ confirm.getTitle());
                System.out.println("결재 사유 : "+ confirm.getReason());
                System.out.println("결재 신청자 : "+ confirm.getName()+" "+confirm.getPosition());
                System.out.println("=======================");

                System.out.print(confirmNumber + "번 결재문서를 결재하시겠습니까? (승인/반려/보류)입력 : ");
                String choice = Container.getSc().nextLine().trim();

                if (choice.equals("승인")) {
                    System.out.println("<알림> 결재를 승인하였습니다.");
                    confirmService.adminConfirmAccept(confirmNumber);
                } else if (choice.equals("반려")) {
                    System.out.println("<알림> 결재를 반려하였습니다.");
                    confirmService.adminConfirmDeny();
                } else if (choice.equals("보류")) {
                    System.out.println("<알림> 결재를 보류하였습니다.");
                    return;
                } else {
                    System.out.println("<알림> '승인' '반려' '보류' 중 하나를 입력해주세요.");
                }
            }
        }

        System.out.println("결재하실 문서 유형을 선택해주세요");
        System.out.println("====================");
        System.out.println(" 1. 휴가 신청\n 2. 출장 신청\n 3. 퇴직 신청");
        System.out.println("====================");
        while (true) {
            System.out.print("(결재)문서유형 번호 입력 : ");
            String choice = Container.getSc().nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("휴가 희망일자와 사유를 입력해주세요.");

                System.out.print("(휴가)희망일자 : ");
                String date = Container.getSc().nextLine().trim();
                System.out.print("(휴가)희망사유 : ");
                String reason = Container.getSc().nextLine().trim();
                System.out.print("제출하시겠습니까? (y/n)입력 : ");
                String yesOrNo = Container.getSc().nextLine().trim();
                if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
                    System.out.println("결재 취소");
                    return;
                }
                System.out.println("<휴가>\n희망일자 : " + date + "\n희망사유 : " + reason + "\n신청자 : " + Container.getLoginedMember().getName() + " " + Container.getLoginedMember().getPosition());

                confirmService.vacation(date, reason, Container.getLoginedMember().getId(), Container.getLoginedMember().getDeptId());

                System.out.println("<알림> 결재가 정상 처리되었습니다.");
                break;
            } else if (choice.equals("2")) {
                System.out.println("출장 기간, 장소를 입력해주세요.");

                System.out.print("(출장)기간 : ");
                String date = Container.getSc().nextLine().trim();
                System.out.print("(출장)장소 : ");
                String place = Container.getSc().nextLine().trim();
                System.out.print("제출하시겠습니까? (y/n)입력 : ");
                String yesOrNo = Container.getSc().nextLine().trim();
                if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
                    System.out.println("결재 취소");
                    return;
                }
                System.out.println("<출장>\n출장기간 : " + date + "\n출장장소 : " + place + "\n신청자 : " + Container.getLoginedMember().getName() + " " + Container.getLoginedMember().getPosition());

                confirmService.bizTrip(date, place, Container.getLoginedMember().getId(), Container.getLoginedMember().getDeptId());

                System.out.println("<알림> 결재가 정상 처리되었습니다.");
                break;
            } else if (choice.equals("3")) {
                System.out.println("퇴직 희망일자와 사유를 입력해주세요.");

                System.out.print("(퇴직)희망일자 : ");
                String date = Container.getSc().nextLine().trim();
                System.out.print("(퇴직)희망사유 : ");
                String reason = Container.getSc().nextLine().trim();
                System.out.print("제출하시겠습니까? (y/n)입력 : ");
                String yesOrNo = Container.getSc().nextLine().trim();
                if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
                    System.out.println("결재 취소");
                    return;
                }
                System.out.println("<퇴직>\n희망일자 : " + date + "\n희망사유 : " + reason + "\n신청자 : " + Container.getLoginedMember().getName() + " " + Container.getLoginedMember().getPosition());

                confirmService.terminate(date, reason, Container.getLoginedMember().getId(), Container.getLoginedMember().getDeptId());

                System.out.println("<알림> 결재가 정상 처리되었습니다.");
                break;
            } else {
                System.out.println("<알림> 문서유형에 맞는 번호를 입력해주세요.");
            }
        }

    }
}
