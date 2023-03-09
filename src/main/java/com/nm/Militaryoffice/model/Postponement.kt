//package com.nm.Militaryoffice.model
//
//import org.springframework.format.annotation.DateTimeFormat
//import java.util.*
//import javax.validation.constraints.FutureOrPresent
//import javax.validation.constraints.NotEmpty
//import javax.validation.constraints.NotNull
//import javax.validation.constraints.Positive
//
//data class Postponement(
//        var postponement_id: Int?,
//        @NotNull @Positive var id: Long,
//        @NotEmpty var paragraph_of_document: String,
//        @NotNull
//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = ["yyyy.MM.dd"])
//        @FutureOrPresent
//        var postponement_start_date: Date,
//        @NotNull
//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = ["yyyy.MM.dd"])
//        @FutureOrPresent
//        var postponement_end_date: Date
//) {
//        constructor() : this(
//                0,
//                0,
//                "",
//                Date(),
//                Date()
//        )
//}