package org.snow.service;


import org.snow.model.business.Dict;

import java.util.List;

public interface DictService {
    public List<Dict> searchByType(String type);
}
