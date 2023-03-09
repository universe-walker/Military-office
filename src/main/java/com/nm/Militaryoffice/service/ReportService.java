package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.model.Summons;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    public final ConscriptService conscriptService;
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final ConscriptionService conscriptionService;

    public ReportService(@Autowired ConscriptService conscriptService,
                         @Autowired ConscriptionService conscriptionService) {
        this.conscriptService = conscriptService;
        this.conscriptionService = conscriptionService;
    }


    public String getDataForReport(Conscription conscription) {
        var conscripts = conscriptService.getConscriptsThatEnlistment(conscription);

        return conscripts
                .stream()
                .map(conscript -> "%s %s".formatted(
                        conscript.getFullname(),
                        conscript.getDateOfBirth()
                ))
                .collect(Collectors.joining());
    }

    public int getCount(Conscription conscription) {
        var conscripts = conscriptService.getConscriptsThatEnlistment(conscription);
        return conscripts.size();
    }

    public void createReport(Conscription conscription) throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream outputStream = new FileOutputStream(new File("report-%s.docx".formatted(new Date())));

        XWPFParagraph paragraph = document.createParagraph();
        var run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText("Отчет от %s".formatted(dateFormat.format(new Date())));
        run.addBreak();
        run.setText("Выбранный призыв: %s / %s".formatted(dateFormat.format(conscription.getStart_date()),
                dateFormat.format(conscription.getEnd_date())));
        run.addBreak();
        run.setText("Количество призывников по плану: %s".formatted( conscription.getQuantity_plan()));
        run.addBreak();
        run.setText("Призваны на текущий момент: %s".formatted(getCount(conscription)));
        run.addBreak();
        run.setText("Призваны:");
        run.addBreak();
        run.addBreak();
        for (var conscript : conscriptService.getConscriptsThatEnlistment(conscription)) {
            run.setText("%s, %s".formatted(conscript.getFullname(), dateFormat.format(conscript.getDateOfBirth())));
            run.addBreak();
        }
        run.setText("_____________________________________________________");
        run.addBreak();

        document.write(outputStream);
        outputStream.close();
    }

    public void createSummonsReport(List<Summons> summonsList) throws IOException {
        XWPFDocument document;
        FileOutputStream outputStream;
        try {
            document = new XWPFDocument();
            outputStream = new FileOutputStream(new File("generated-summons-%s.docx".formatted(new Date())));

        } catch (FileNotFoundException exc) {
            return;
        }

        XWPFParagraph paragraph = document.createParagraph();
        var run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        summonsList.forEach(
                summons -> {
                    run.setText("%s".formatted(summons.getWho_made_summons()));
                    run.addBreak();
                    run.setText(summons.getContent());
                    run.addBreak();
                    run.setText("Дата явки: %s".formatted(dateFormat.format(summons.getAppearance_datetime())));
                    run.addBreak();
                    run.addBreak();
                    run.addBreak();
                    run.setText("Дата создания: %s".formatted(dateFormat.format(summons.getCreation_date())));
                    run.addBreak();
                    run.setText("_____________________________________________________");
                    run.addBreak();
                }
        );

        document.write(outputStream);
        outputStream.close();
    }
}
