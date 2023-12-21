package org.example.confirm;

import org.example.Container;
import org.example.member.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfirmRepository {
    public void vacation(String date, String reason, int memberId, int deptId) {
        String sql = String.format("INSERT INTO confirm set title = '휴가', date = '%s', reason = '%s', memberId =%d, deptId=%d, createdDate = NOW(), modifiedDate = NOW();", date, reason,memberId,deptId);

        Container.getDBConnection().insert(sql);
    }
    public void bizTrip(String date, String place, int memberId, int deptId) {
        String sql = String.format("INSERT INTO confirm set title = '출장', date = '%s', reason = '%s', memberId =%d, deptId=%d, createdDate = NOW(), modifiedDate = NOW();", date, place,memberId,deptId);

        Container.getDBConnection().insert(sql);
    }
    public void terminate(String date, String reason, int memberId, int deptId) {
        String sql = String.format("INSERT INTO confirm set title = '퇴직', date = '%s', reason = '%s', memberId =%d, deptId=%d, createdDate = NOW(), modifiedDate = NOW();", date, reason,memberId,deptId);

        Container.getDBConnection().insert(sql);
    }
    public List<Confirm> findByAll() {
        List<Confirm> confirmList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("SELECT * FROM confirm;");

        for (Map<String, Object> row : rows) {
            Confirm confirm = new Confirm(row);
            confirmList.add(confirm);
        }
        return confirmList;
    }
    public List<Confirm> findByList() {
        List<Confirm> confirmList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("SELECT\n" +
                "confirm.id,\n" +
                "confirm.title,\n" +
                "`dept`.deptName ,\n" +
                "`member`.name,\n" +
                "`member`.`position`,\n" +
                "confirm.reason,\n" +
                "confirm.date,\n" +
                "confirm.reason,\n" +
                "confirm.memberId,\n" +
                "confirm.deptId,\n" +
                "confirm.createdDate,\n" +
                "confirm.modifiedDate\n" +
                "FROM confirm\n" +
                "INNER JOIN `member`\n" +
                "ON confirm.memberId = `member`.id \n" +
                "INNER JOIN `dept`\n" +
                "ON confirm.deptId = `dept`.id;");

        for (Map<String, Object> row : rows) {
            Confirm confirm = new Confirm(row);
            confirmList.add(confirm);
        }
        return confirmList;
    }
    public Confirm confirmFindById(int id) {
        List<Confirm> confirmList = this.findByList();
        for (Confirm confirm : confirmList) {
            if (id==confirm.getId()) {
                return confirm;
            }
        }
        return null;
    }
    public void adminConfirmAccept(int confirmNumber) {
        String sql = String.format("delete from confirm where id = '%s'", confirmNumber);

        Container.getDBConnection().delete(sql);
    }
    public void adminConfirmDeny() {

    }
}