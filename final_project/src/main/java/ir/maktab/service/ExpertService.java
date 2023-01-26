package ir.maktab.service;


import ir.maktab.entity.Expert;
import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.repository.ExpertRepository;

import java.util.List;

public class ExpertService {

    private final ExpertRepository expertRepository = new ExpertRepository();

    public void create(Expert expert) {
        expertRepository.create(expert);
    }

    public Expert findById(Long id) {
        return expertRepository.findById(id);
    }

    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    public void delete(Long id) {
        expertRepository.delete(id);
    }

    public List<Expert> findByStatus(ExpertStatus status) {
        return expertRepository.getExpertsByStatus(status);
    }

    public Expert findByStatusAndId(Long id, ExpertStatus status) {
        return expertRepository.getExpertsByStatusAndId(id, status);
    }

    public Expert findByUserId(Long id) {
        return expertRepository.getExpertByUserId(id);
    }
}
