package org.example.confirm;

import java.util.List;

public class ConfirmService {
    ConfirmRepository confirmRepository;
    public ConfirmService() {
        confirmRepository = new ConfirmRepository();
    }
    public void vacation(String date, String reason, int memberId, int deptId) {
        confirmRepository.vacation(date,reason,memberId,deptId);
    }
    public void bizTrip(String date, String place, int memberId, int deptId) {
        confirmRepository.bizTrip(date,place,memberId,deptId);
    }
    public void terminate(String date, String reason, int memberId, int deptId) {
        confirmRepository.terminate(date,reason,memberId,deptId);
    }
    public List<Confirm> findByAll() {
        return confirmRepository.findByAll();
    }
    public List<Confirm> findByList() {
        return confirmRepository.findByList();
    }
    public Confirm confirmFindById(int id) {
        return confirmRepository.confirmFindById(id);
    }
    public void adminConfirmAccept(int confirmNumber) {
        confirmRepository.adminConfirmAccept(confirmNumber);
    }
    public void adminConfirmDeny() {

    }
}
