package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Postponement;
import com.nm.Militaryoffice.repository.PostponementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostponementService {
    private final PostponementRepository postponementRepository;

    PostponementService(@Autowired PostponementRepository postponementRepository) {
        this.postponementRepository = postponementRepository;
    }

    public List<Postponement> getPostponements(Long conscriptId) {
        return postponementRepository.getPostponementsByConscriptId(conscriptId);
    }

    public void createPostponement(Long conscriptId, Postponement postponement) {
        postponementRepository.createPostponement(conscriptId, postponement);
    }

}
