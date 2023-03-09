package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.model.Summons;
import com.nm.Militaryoffice.model.SummonsInfo;
import com.nm.Militaryoffice.repository.ConscriptRepository;
import com.nm.Militaryoffice.repository.SummonsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Service
public class SummonsService {
    private final SummonsRepository summonsRepository;
    private final ConscriptRepository conscriptRepository;

    private final ReportService reportService;

    public List<Summons> getSummonsByConscriptId(Long conscriptId) {
        return summonsRepository.getSummonsByConscriptId(conscriptId);
    }

    public void generateSummons(SummonsInfo summonsInfo, Conscription conscription) {
        var conscripts = conscriptRepository.getAllConscriptThatFits(summonsInfo);

        List<Summons> summonsList = conscripts.stream()
                .map(conscript -> new Summons(
                        0,
                        conscription.getConscription_id(),
                        conscript.getId(),
                        new Date(),
                        "Гражданину %s %s года рождения Явиться в назначенный день для прохождения медицинской комиссии"
                                .formatted(conscript.getFullname(), conscript.getDateOfBirth()),
                        "Михайлов Сергей Васильевич",
                        summonsInfo.getDateTo(),
                        conscript.getPlaceOfResidence(),
                        false,
                        false
                )).toList();
                try {
                    reportService.createSummonsReport(summonsList);
                } catch (IOException exc) {
                    return;
                }
    }
}
