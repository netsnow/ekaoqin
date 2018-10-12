package org.snow.service.impl;

import org.snow.dao.jpa.DictRepository;
import org.snow.model.business.Dict;
import org.snow.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dictService")
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Override
    public List<Dict> searchByType(String type) {
        Iterable<Dict> geted = dictRepository.findByType(type);
        List<Dict> list = new ArrayList<Dict>();
        geted.forEach(single -> {
            list.add(single);
        });
        return list;
    }
}
