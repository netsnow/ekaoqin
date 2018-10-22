package org.snow.controller;

import com.alibaba.fastjson.JSONObject;
import org.snow.form.DisciplineRespond;
import org.snow.form.EntryLogRespond;
import org.snow.form.MockTdRequest;
import org.snow.model.business.Discipline;
import org.snow.service.DisciplineService;
import org.snow.service.EntryLogService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisciplineRestController {

    @Autowired
    private DisciplineService disciplineService;

    @RequestMapping(path = "/discipline", method = RequestMethod.GET)
    public List<DisciplineRespond> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @RequestMapping(path = "/discipline", method = RequestMethod.POST)
    public Boolean addDiscipline(
        @RequestBody Discipline discipline
    ) {
        return disciplineService.addDiscipline(discipline);
    }
}
