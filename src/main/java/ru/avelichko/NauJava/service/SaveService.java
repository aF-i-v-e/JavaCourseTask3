package ru.avelichko.NauJava.service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.model.AccountReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SaveService {

    private final String successfulMessage = "Отчёт успешно сохранён в ";
    private final String unsuccessfulMessage = "Ошибка при сохранении отчёта в ";

    public Path getAccountReportPath(Path dirPath, AccountReport accountReport) {
        return dirPath.resolve(accountReport.getAccountReportId().toString() + ".pdf");
    }

    public String save(Path dirPath, AccountReport accountReport) {
        String accountReportSavePath = getAccountReportPath(dirPath, accountReport).toString();
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return unsuccessfulMessage + accountReportSavePath;
            }
        }
        Boolean isSuccess = saveToPdf(accountReport, accountReportSavePath);
        String message = isSuccess ? successfulMessage : unsuccessfulMessage;
        message += accountReportSavePath;
        return message;
    }

    public Boolean saveToPdf(AccountReport report, String pathToSave) {
        try {
            PdfWriter writer = new PdfWriter(pathToSave);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            // Шрифты по умолчанию не поддерживают кириллицу,
            // поэтому был скачан arial.
            String fontPath = Paths.get(System.getProperty("user.dir"))
                    .resolve("src").resolve("main").resolve("resources")
                    .resolve("fonts").resolve("arialmt.ttf").toString();
            PdfFont font = PdfFontFactory.createFont(fontPath, com.itextpdf.io.font.PdfEncodings.IDENTITY_H);

            document.add(new Paragraph("Отчёт").setFont(font).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Уникальный идентификатор отчёта: " + report.getAccountReportId()).setFont(font));
            document.add(new Paragraph("Начало периода: " + report.getDateStart()).setFont(font));
            document.add(new Paragraph("Конец периода: " + report.getDateEnd()).setFont(font));
            document.add(new Paragraph("Сумма: " + report.getTotalSum()).setFont(font));
            document.add(new Paragraph("Категория отчёта: " + report.getCategoryInfo()).setFont(font));
            document.add(new Paragraph("Дополнительная информация: " + report.getDopInfo()).setFont(font));

            document.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
