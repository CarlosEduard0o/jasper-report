/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.generate.report;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author carlosrosa
 */
public class GerarRelatorio {

    final static private String url = "jdbc:mysql://your-database-ip:your-database-port/dbname";
    final static private String user = "user";
    final static private String password = "password";
    final static private String sql = "SELECT * FROM dbname;";

    public void gerar() throws ClassNotFoundException, JRException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorio/relatorio.jrxml"));
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            Map parametros = new HashMap();
            parametros.put("titulo", "Relatorio");      
            JasperPrint print = JasperFillManager.fillReport(report, parametros, jrRS);
            String downloadPath = System.getProperty("user.home");
            JasperExportManager.exportReportToPdfFile(print, downloadPath+"/Downloads/relatorio.pdf");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
