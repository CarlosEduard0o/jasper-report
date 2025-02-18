/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.generate.report;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author carlosrosa
 */
public class GerarGrafico {

    public void gerar() throws ClassNotFoundException, JRException {

        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(10, "Vendas", "Janeiro");
            dataset.addValue(20, "Vendas", "Fevereiro");
            dataset.addValue(30, "Vendas", "Marcio");
            dataset.addValue(40, "Vendas", "Abril");
            dataset.addValue(50, "Vendas", "Maio");
            
            JFreeChart graficoDeBarras = ChartFactory.createBarChart(
                    "Vendas Mensais",
                    "Mês",
                    "Vendas",
                    dataset);

            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Janeiro", 10);
            pieDataset.setValue("Fevereiro", 20);
            pieDataset.setValue("Marcio", 30);
            pieDataset.setValue("Abril", 40);
            pieDataset.setValue("Maio", 50);
            
            JFreeChart graficoDePizza = ChartFactory.createPieChart(
                    "Vendas Mensais", 
                    pieDataset);
            
            ByteArrayOutputStream baosBarChart = new ByteArrayOutputStream();
            BufferedImage bufferedImageBarChart = graficoDeBarras.createBufferedImage(800, 600);
            ChartUtilities.writeBufferedImageAsPNG(baosBarChart, bufferedImageBarChart);
            
            ByteArrayOutputStream baosPieChart = new ByteArrayOutputStream();
            BufferedImage bufferedImagePieChart = graficoDePizza.createBufferedImage(800, 600);
            ChartUtilities.writeBufferedImageAsPNG(baosPieChart, bufferedImagePieChart);
            
            InputStream barChartStream = new ByteArrayInputStream(baosBarChart.toByteArray());
            InputStream pieChartStream = new ByteArrayInputStream(baosPieChart.toByteArray());
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("GraficoDeBarras", barChartStream);
            parametros.put("GraficoDePizza", pieChartStream);
            
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorio/relatorioGrafico.jrxml"));
            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
            String downloadPath = System.getProperty("user.home");
            JasperExportManager.exportReportToPdfFile(print, downloadPath + "/Downloads/relatorioGrafico.pdf");
            
        } catch (IOException ex) {
            Logger.getLogger(GerarGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
