/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package example.generate.report;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author carlosrosa
 */
public class GenerateReport {



    public static void main(String[] args) throws JRException  {
        GerarRelatorio relatorioService = new GerarRelatorio();
        try {
            relatorioService.gerar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
