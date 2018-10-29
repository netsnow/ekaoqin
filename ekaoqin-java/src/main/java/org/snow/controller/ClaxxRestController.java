package org.snow.controller;

import io.swagger.annotations.ApiParam;
import org.snow.model.business.Claxx;
import org.snow.model.business.Notice;
import org.snow.service.ClaxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaxxRestController {

    @Autowired
    private ClaxxService claxxService;

    @RequestMapping(path = "/claxx", method = RequestMethod.GET)
    public List<Claxx> getAllClaxxes() {
        return claxxService.getAllClaxxes();
    }

    @RequestMapping(path = "/claxx", method = RequestMethod.POST)
    public Boolean addClaxx(
        @RequestBody Claxx claxx
    ) {
        return claxxService.addClaxx(claxx);
    }

    @RequestMapping(path = "/claxx/{id}", method = RequestMethod.PUT)
    public Boolean updateClaxxById(
        @PathVariable Long id,
        @RequestBody Claxx claxx
    ) {
        return claxxService.updateClaxxById(id,claxx);
    }

    @RequestMapping(path = "/claxx/{id}", method = RequestMethod.DELETE)
    public Boolean deleteClaxxById(
        @PathVariable Long id
    ) {
        return claxxService.deleteClaxxById(id);
    }

    @RequestMapping(path = "/claxx/search", method = RequestMethod.POST)
    public List<Claxx> searchClaxxes(
        @RequestBody Claxx claxx) {

        return claxxService.searchClaxxes(claxx);
    }

}
