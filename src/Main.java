import java.sql.Connection;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) {

        try(final Connection conn = DBUtil.connection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
