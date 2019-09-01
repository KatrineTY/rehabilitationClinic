package com.javaschool.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javaschool.entities.Event;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class CommonReport {
    private final static List<String> COLUMNS = Arrays.asList("Date", "Kind of treatment",
            "Name of treatment", "Dose", "Building", "Ward", "Status", "Comment");
    private final static int COUNT_OF_COLUMNS = COLUMNS.size();

    public void buildPdfDocument(String patientName, LocalDate startDate, LocalDate endDate,
                                 List<Event> events, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));

            document.open();
            document.add(new Paragraph("Patient: " + patientName));
            document.add(new Paragraph(String.format("Date period from: %s to: %s",
                    startDate.toString().replace("T", " "),
                    endDate.toString().replace("T", " "))));
            PdfPTable table = new PdfPTable(COUNT_OF_COLUMNS);
            table.setWidths(new float[]{3, 3, 4, 1, 1, 1, 2, 3});
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            addTableHeader(table);
            addRows(table, events);

            document.add(table);
            document.close();

            log.info("Report created");
            Path file = Paths.get("report.pdf");
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename=report.pdf");
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException e) {
            log.error("Cannot write report into document", e);
        } catch (FileNotFoundException e) {
            log.error("Cannot write report into file", e);
        } catch (IOException e) {
            log.error("Cannot add report into response", e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }

    private void addTableHeader(PdfPTable table) {
        COLUMNS.forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRows(PdfPTable table, List<Event> events) {
        events.forEach(event -> {
            table.addCell(event.getDate().toString().replace("T", " "));
            table.addCell(event.getType().getKind());
            table.addCell(event.getType().getName());
            table.addCell(event.getDose());
            table.addCell(event.getBuilding());
            table.addCell(String.valueOf(event.getWard()));
            table.addCell(event.getStatus());
            table.addCell(event.getComment());
        });
    }

}