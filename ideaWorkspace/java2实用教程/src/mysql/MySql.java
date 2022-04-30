package mysql;
import java.sql.*;
/**
 * @author 86187
 */
public class MySql {

    public static void main(String[] args){
        Connection con  = null;
        Statement sql;
        ResultSet rs;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println("wu");
        }
        String url = "jdbc:mysql://localhost:3306/malajava?useSSl=true";
        String user = "root";
        String password = "123456";
        try {
            con = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            System.out.println("未连接");
        }
        try{
            String s = new String("select name from malajava.student where id > 1 ");
            sql = con.createStatement();
            rs = sql.executeQuery(s);
            while (rs.next()){
                //int id = rs.getInt(1);
                String name = rs.getString(1);
                //String time = rs.getString(3);
                //System.out.print(id + "  " );
                System.out.print(name + " ");
                //System.out.print(time + " ");
                System.out.println(" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
