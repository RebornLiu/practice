import java.sql.*;

public class MetaInfo {

    public static void main(String[] args) {
        String db_url = "jdbc:mysql://localhost:3306/reborn?" +
                "user=root&password=1qaz@WSX&useUnicode=true&characterEncoding=UTF8";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection(db_url);
            DatabaseMetaData metaData = cn.getMetaData();

            String[] types = {"TABLE"};
            ResultSet resultSet = metaData.getTables(null, null, "%", types);

            while (resultSet.next()) {
                String tablename = resultSet.getString("TABLE_NAME");
                System.out.println("table:" + tablename);
                getTableInfo(metaData, tablename);

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void getTableInfo(DatabaseMetaData metaData, String table) throws SQLException {
        ResultSet resultSet = metaData.getColumns(null, null, table, null);
        while (resultSet.next()) {
            String name = resultSet.getString("COLUMN_NAME");
            String type = resultSet.getString("TYPE_NAME");
            int size = resultSet.getInt("COLUMN_SIZE");

            System.out.println("col:" + name + " type:" + type + " size:" + size);
        }
    }
}
