package com.nm.Militaryoffice.service;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.repository.ConscriptRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SearchService {
    
    private final ConscriptRepository conscriptRepository;

    public SearchService(@Autowired ConscriptRepository conscriptRepository) {
        this.conscriptRepository = conscriptRepository;
    }

    enum FindBy {
        SURNAME("surname"),
        NUMBER("number"),
        BIRTHDAY("birthday");

        final String value;

        FindBy(String value) {
            this.value = value;
        }
    }

    public List<Conscript> search(@NotNull final String findBy, @NotNull final String searchValue) {
        List<Conscript> results = null;
        final var formatter = new SimpleDateFormat("dd.MM.yyyy");
        switch (findBy) {
            case "surname" -> results = searchBySurname(searchValue);
            case "number" -> {
                    if (searchByNumber(searchValue).isPresent()) {
                        results =  List.of(searchByNumber(searchValue).get());
                        break;
                    }
                    return List.of();
            }
            case "birthday" -> {
                try {
                    results = searchByBirthday(formatter.parse(searchValue));
                } catch (ParseException exc) {
                    results = List.of();
                }
            }
        }
        return results;
    }

    public String getFindByRussian(String findBy) {
        String result = "";
        switch(findBy) {
            case "number" -> {
                result = "Номер приписного";
            }
            case "birthday" -> {
                result = "День рождение";
            }
            case "surname" -> {
                result = "Фамилия";
            }
        }
        return result;
    }

    public List<Conscript> searchBySurname(String surname) {
        return conscriptRepository.findConscriptBySurname(surname);
    }

    public Optional<Conscript> searchByNumber(String number) {
        return conscriptRepository.findConscriptByNumberPripisnoe(number);
    }

    public List<Conscript> searchByBirthday(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyyy.MM.dd", "dd.MM.yyyy"}) Date birthday) {
        return conscriptRepository.findConscriptByBirthday(birthday);
    }
}
