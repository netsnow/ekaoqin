package org.snow.service.impl;

import org.snow.dao.jpa.ClaxxRepository;
import org.snow.model.business.Claxx;
import org.snow.service.ClaxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("claxxService")
public class ClaxxServiceImpl implements ClaxxService {

    @Autowired
    private ClaxxRepository claxxRepository;

    @Override
    public List<Claxx> getAllClaxxes() {

        Iterable<Claxx> geted = claxxRepository.findAll();
        List<Claxx> list = new ArrayList<Claxx>();
        geted.forEach(single -> {
            if (!single.getIsDeleted()) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public Boolean addClaxx(Claxx claxx) {
        claxxRepository.save(claxx);
        return true;
    }

    @Override
    public Boolean updateClaxxById(Long claxxId, Claxx claxx) {
        claxx.setId(claxxId);
        claxxRepository.save(claxx);
        return true;
    }

    @Override
    public Boolean deleteClaxxById(Long claxxId) {
        Optional<Claxx> claxx = claxxRepository.findById(claxxId);
        claxx.get().setIsDeleted(true);
        claxxRepository.save(claxx.get());
        return true;
    }
}
