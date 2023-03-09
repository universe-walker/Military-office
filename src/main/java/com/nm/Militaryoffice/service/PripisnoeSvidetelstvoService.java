package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.PripisnoeSvidetelstvo;
import com.nm.Militaryoffice.repository.PripisnoeSvidetelstvoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PripisnoeSvidetelstvoService {
    private final PripisnoeSvidetelstvoRepository pripisnoeSvidetelstvoRepository;

    public PripisnoeSvidetelstvoService(PripisnoeSvidetelstvoRepository pripisnoeSvidetelstvoRepository) {
        this.pripisnoeSvidetelstvoRepository = pripisnoeSvidetelstvoRepository;
    }

    public Optional<PripisnoeSvidetelstvo> findBySeriesAndNumber(String seriesAndNumber) {
        return pripisnoeSvidetelstvoRepository.findBySeriesAndNumber(seriesAndNumber);
    }

    protected String generateSeriesAndNumber() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            boolean isGenerateSymbol = new Random().nextBoolean();
            if (isGenerateSymbol) {
                result.append((char) new Random().nextInt('А' + 30));
            } else {
                result.append(new Random().nextInt(0, 9));
            }
        }

        return result.toString();
    }

    public void createPripisnoeSvidetelstvo(PripisnoeSvidetelstvo pripisnoeSvidetelstvo, long conscriptId) {
        String seriesAndNumber = generateSeriesAndNumber();
        pripisnoeSvidetelstvoRepository.save(pripisnoeSvidetelstvo, seriesAndNumber, conscriptId);
    }
}
