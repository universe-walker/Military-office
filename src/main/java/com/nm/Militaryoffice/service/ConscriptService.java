package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.dao.ConscriptDao;
import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.repository.ConscriptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConscriptService {
    private final ConscriptDao conscriptDao;
    private final ConscriptRepository conscriptRepository;

    public ConscriptService(ConscriptDao conscriptDao, ConscriptRepository conscriptRepository) {
        this.conscriptDao = conscriptDao;
        this.conscriptRepository = conscriptRepository;
    }

    public int addConscript(Conscript conscript) {
        return conscriptDao.insertConscript(conscript);
    }

    public List<Conscript> listConscript() {
        return conscriptDao.listConscripts();
    }

    public Optional<Conscript> getConscriptById(Long id) {
        return conscriptDao.getConscriptById(id);
    }

    public List<Conscript> getConscriptsThatEnlistment(Conscription conscription) {
        return conscriptRepository.getConscriptsThatEnlistment(conscription);
    }

    public Boolean updateConscript(Conscript conscript) {
        var res = conscriptRepository.updateConscript(conscript);
        System.out.println(res);
        return res  == 1;
    }
}
