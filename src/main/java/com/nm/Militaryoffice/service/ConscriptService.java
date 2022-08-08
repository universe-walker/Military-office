package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.dao.ConscriptDao;
import com.nm.Militaryoffice.model.Conscript;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConscriptService {
    private final ConscriptDao conscriptDao;


    public ConscriptService(ConscriptDao conscriptDao) {
        this.conscriptDao = conscriptDao;
    }

    public void addConscript(Conscript conscript) {
        int result = conscriptDao.insertConscript(conscript);
    }

    public List<Conscript> listConscript() {
        return conscriptDao.listConscripts();
    }

    public Optional<Conscript> getConscriptById(Long id) {
        return conscriptDao.getConscriptById(id);
    }
}
