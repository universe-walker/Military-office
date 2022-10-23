package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.PripisnoeSvidetelstvo;
import com.nm.Militaryoffice.repository.PripisnoeSvidetelstvoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PripisnoeSvidetelstvoService {
    private final PripisnoeSvidetelstvoRepository pripisnoeSvidetelstvoRepository;

    public PripisnoeSvidetelstvoService(PripisnoeSvidetelstvoRepository pripisnoeSvidetelstvoRepository) {
        this.pripisnoeSvidetelstvoRepository = pripisnoeSvidetelstvoRepository;
    }

    public Optional<PripisnoeSvidetelstvo> findBySeriesAndNumber(String seriesAndNumber) {
        return pripisnoeSvidetelstvoRepository.findBySeriesAndNumber(seriesAndNumber);
    }
}
