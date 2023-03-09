package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.CommissionDecision;
import com.nm.Militaryoffice.repository.CommissionDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionDecisionService {
    public CommissionDecisionService(
            @Autowired CommissionDecisionRepository commissionDecisionRepository
    ) {
        this.commissionDecisionRepository = commissionDecisionRepository;
    }

    private final CommissionDecisionRepository commissionDecisionRepository;

    public List<CommissionDecision> getCommissionDecisionsByConscriptId(final Long conscriptId) {
        return commissionDecisionRepository.getCommissionDecisionByConscriptId(conscriptId);
    }

    public void createCommissionDecision(CommissionDecision decision, long conscriptId) {
        commissionDecisionRepository.save(decision, conscriptId);
    }

}
