package org.snow.service;

import org.snow.form.DisciplineRespond;
import org.snow.model.business.Discipline;

import java.util.List;

public interface DisciplineService {
    List<DisciplineRespond> getAllDisciplines();

    Boolean addDiscipline(Discipline discipline);
}
