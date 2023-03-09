package com.nm.Militaryoffice.service

import com.nm.Militaryoffice.model.Education
import com.nm.Militaryoffice.repository.EducationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class EducationService(@Autowired private val educationRepository: EducationRepository) {
    fun getEducationsByConscriptId(conscriptId: Long): List<Education>? {
        return educationRepository.getAllEducationsByConscriptId(conscriptId)
    }

    fun createEducation(education: Education, conscriptId: Long) {
        educationRepository.createEducation(education, conscriptId)
    }
}