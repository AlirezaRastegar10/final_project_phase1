package ir.maktab.service;

import ir.maktab.entity.Duty;
import ir.maktab.repository.DutyRepository;

import java.util.List;

public class DutyService {

    private final DutyRepository dutyRepository = new DutyRepository();

    public void create(Duty duty) {
        dutyRepository.create(duty);
    }

    public Duty findById(Long id){
        return dutyRepository.findById(id);
    }

    public List<Duty> findAll(){
        return dutyRepository.findAll();
    }

    public void delete(Long id){
        dutyRepository.delete(id);
    }

    public Duty findByName(String name) {
        return dutyRepository.getDutyByName(name);
    }
}
