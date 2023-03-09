package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.repository.ConscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class ConscriptionService {
    protected final ConscriptionRepository conscriptionRepository;

    public ConscriptionService(ConscriptionRepository conscriptionRepository) {
        this.conscriptionRepository = conscriptionRepository;
    }

    public Conscription getConscription(Long conscriptionId) {
        return conscriptionRepository.getConscription(conscriptionId);
    }
    public List<Conscription> getAllConscription() {
        return conscriptionRepository.getAllConscriptions();
    }
}
