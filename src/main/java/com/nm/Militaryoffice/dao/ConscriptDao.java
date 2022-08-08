package com.nm.Militaryoffice.dao;

import com.nm.Militaryoffice.model.Conscript;

import java.util.List;
import java.util.Optional;

public interface ConscriptDao {
    List<Conscript> listConscripts();
    int insertConscript(Conscript conscript);
    int updateConscript(Conscript conscript);
    Optional<Conscript> getConscriptById(Long id);
}
