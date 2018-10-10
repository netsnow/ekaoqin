package org.snow.service;

import org.snow.model.business.Claxx;

import java.util.List;

public interface ClaxxService {

    List<Claxx> getAllClaxxes();

    Boolean addClaxx(Claxx claxx);

    Boolean updateClaxxById(Long claxxId, Claxx claxx);

    Boolean deleteClaxxById(Long claxxId);
}
