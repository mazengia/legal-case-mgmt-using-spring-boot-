package com.enatbanksc.casemanagementsystem.case_management.JasperReport;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.ForeclosureRepository;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationRepository;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationServiceImpl;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final SimpleReportFiller filler;

    private final SimpleReportExporter exporter;
    private final LitigationRepository litigationRepository;
    private final ForeclosureRepository foreclosureRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\mtesfa\\Desktop\\Report";
        Collection<Litigation> litigations = (Collection<Litigation>) litigationRepository.findAll();
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(litigations);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "mtesfa");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\litigations.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\litigations.pdf");
        }
        return "report generated in path : " + path;
    }
    public void litigation(String reportType, OutputStream outputStream) {

        Map<String, Object> parameters;
        String fileName = null;
        parameters = fillParameters("created by", "mtesfa");
        fileName = "Attended Shareholders";

        filler.setReportFileName("report.jrxml");
        filler.compileReport();

        filler.setParameters(parameters);
        List<Litigation> litigations = new ArrayList<>();
        litigationRepository.findAll().iterator().forEachRemaining(litigations::add);
        filler.setDataSource(litigations);
        reportMaker(reportType, outputStream, fileName);

    }
    public void foreclosure(String reportType, OutputStream outputStream) {

        Map<String, Object> parameters;
        String fileName = null;
        parameters = fillParameters("created by", "mtesfa");
        fileName = "Attended Shareholders";

        filler.setReportFileName("report.jrxml");
        filler.compileReport();

        filler.setParameters(parameters);
        System.out.println(foreclosureRepository.report());
//        System.out.println("foreclosureRepository.findAllByDeletedIsFalseAndEnabledIsTrue()");
        List<Foreclosure> foreclosures = new ArrayList<>();
        foreclosureRepository.report().iterator().forEachRemaining(foreclosures::add);
        filler.setDataSource(foreclosures);
        reportMaker(reportType, outputStream, fileName);

    }

    private Map<String, Object> fillParameters(String... values) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("TITLE", values[0]);
        parameters.put("SUB_TITLE", values[1]);
        return parameters;
    }
    private void reportMaker(String reportType, OutputStream outputStream, String fileName) {
        filler.fillReport();

        exporter.setJasperPrint(filler.getJasperPrint());

        switch (reportType) {
            case "pdf":
                exporter.exportToPdf(fileName, outputStream);
                     break;
            case "csv":
                exporter.exportToCsv(fileName, outputStream);
                break;
            case "xlsx":
                exporter.exportToXlsx(fileName, fileName, outputStream);
                break;
            default:
                exporter.exportToHtml(fileName, outputStream);
                break;
        }
    }

}