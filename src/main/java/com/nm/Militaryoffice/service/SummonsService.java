package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Summons;
import com.nm.Militaryoffice.repository.SummonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummonsService {
    private final SummonsRepository summonsRepository;

    public SummonsService(@Autowired SummonsRepository summonsRepository) {
        this.summonsRepository = summonsRepository;
    }

    public List<Summons> getSummonsByConscriptId(Long conscriptId) {
        return summonsRepository.getSummonsByConscriptId(conscriptId);
    }
}
