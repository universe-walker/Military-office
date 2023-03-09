package com.nm.Militaryoffice.controller

import com.nm.Militaryoffice.model.*
import com.nm.Militaryoffice.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@Controller
@RequestMapping("/conscript")
class ConscriptController(
    @Autowired private val conscriptService: ConscriptService,
    @Autowired private val educationService: EducationService,
    @Autowired private val pripisnoeService: PripisnoeSvidetelstvoService,
    @Autowired private val postponementService: PostponementService,
    @Autowired private val summonsService: SummonsService,
    @Autowired private val commissionDecisionService: CommissionDecisionService
) {
    @GetMapping("/{conscriptId}")
    fun getConscriptInfo(
        @PathVariable conscriptId: Long,
        model: Model
    ): String {
        val conscript: Conscript = conscriptService.getConscriptById(conscriptId).get()
        model["conscript"] = conscript
        val conscriptEducation = educationService.getEducationsByConscriptId(conscriptId)
        // проверить
        model["educations"] = conscriptEducation ?: listOf(Education())
        val pripisnoeSvidetelstvo: PripisnoeSvidetelstvo? = if (!conscript.seriesAndNumber.isNullOrEmpty()) {
            pripisnoeService.findBySeriesAndNumber(conscript.seriesAndNumber).get()
        } else null
        model["pripisnoe"] = pripisnoeSvidetelstvo ?: PripisnoeSvidetelstvo()
        val postponements = postponementService.getPostponements(conscriptId)

        model["postponements"] = postponements ?: listOf(Postponement())

        model["summons"] = summonsService.getSummonsByConscriptId(conscriptId)

        model["commissionDecisions"] = commissionDecisionService.getCommissionDecisionsByConscriptId(conscriptId)

        return "conscript"
    }

    @GetMapping("/create")
    fun showCreateConscript(conscript: Conscript): String = "createConscript"

    @PostMapping("/create", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun addConscript(
        @Valid conscript: Conscript,
        result: BindingResult,
        model: Model
    ) : String {
        if (result.hasErrors()) return "createConscript"
        val conscriptId = conscriptService.addConscript(conscript).toLong()
        return "redirect:/conscript/${conscriptId}"
    }

    @GetMapping("{conscriptId}/create/education")
    fun showAddEducation(
        @PathVariable conscriptId: Long,
        model: Model
    ) : String {
        try {
            model["conscript"] = conscriptService.getConscriptById(conscriptId).get()
        } catch (e: java.util.NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Нет такого призывника")
        }
        model["education"] = Education()
        return "createEducation"
    }

    @PostMapping(
        "{conscriptId}/create/education",
        consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    )
    fun addEducation(
        @Valid education: Education,
        result: BindingResult,
        @PathVariable conscriptId: Long
    ) : String {
        if (result.hasErrors()) return "createEducation"
        educationService.createEducation(education, conscriptId)

        return "redirect:/conscript/${conscriptId}"
    }

    @GetMapping("{conscriptId}/create/pripisnoe")
    fun showAddPripisnoeSvidetelstvo(@PathVariable conscriptId: Long, model: Model): String {
        model.addAttribute("pripisnoe", PripisnoeSvidetelstvo())
        return "createPripisnoeSvidetelstvo"
    }

    @PostMapping(
        "{conscriptId}/create/pripisnoe",
        consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun addPripisnoeSvidetelstvo(
        @Valid pripisnoe: PripisnoeSvidetelstvo,
        result: BindingResult,
        @PathVariable conscriptId: Long,
        model: Model
    ): String {
        if (result.hasErrors()) {
            return "createPripisnoeSvidetelstvo"
        }
        pripisnoeService.createPripisnoeSvidetelstvo(pripisnoe, conscriptId)


        return "redirect:/conscript/${conscriptId}"
    }

    @GetMapping("/{conscriptId}/update")
    fun updateBaseInfo(@PathVariable conscriptId: Long, model: Model): String {
        val conscript = conscriptService.getConscriptById(conscriptId)?.get()
        model["conscript"] = conscript ?: Conscript()
        return "updateConscript"
    }

    @PostMapping("/{conscriptId}/update")
    fun updateBaseInfo(@Valid conscript: Conscript,
                       result: BindingResult,
                       @PathVariable conscriptId: Long): String {
        if (result.hasErrors()) return "updateConscript"

        println(conscriptService.updateConscript(conscript))

        return "redirect:/conscript/${conscriptId}"
    }
}