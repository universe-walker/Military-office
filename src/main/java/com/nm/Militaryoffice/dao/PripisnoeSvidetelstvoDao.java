package com.nm.Militaryoffice.dao;

import com.nm.Militaryoffice.model.PripisnoeSvidetelstvo;

import java.util.Optional;

public interface PripisnoeSvidetelstvoDao {

    public Long save(PripisnoeSvidetelstvo pripisnoeSvidetelstvo,
                     String series_and_number,
                     long conscriptId);
    public boolean update(PripisnoeSvidetelstvo pripisnoeSvidetelstvo);
    public Optional<PripisnoeSvidetelstvo> findBySeriesAndNumber(String seriesAndNumber);
}
