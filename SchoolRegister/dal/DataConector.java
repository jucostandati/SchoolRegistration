package SchoolRegister.dal;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author caule
 */
public class DataConector {

    //Método responsável para a ligação com a base de dados
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //Chamar o driver importado
        String driver = "com.mysql.cj.jdbc.Driver"; //"com.mysql.jdbc.Driver";
    //As informações da base de dados
        String url = "jdbc:mysql://localhost/registo_escolar";
        String user = "root";
        String password = "";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //Erro durante 
           //System.out.println(e);
           return null;
        }
    }
}
