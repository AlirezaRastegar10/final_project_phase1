package ir.maktab.service;



import ir.maktab.entity.UnderDuty;
import ir.maktab.repository.UnderDutyRepository;

import java.util.List;

public class UnderDutyService {

    private final UnderDutyRepository underDutyRepository = new UnderDutyRepository();

    public void create(UnderDuty underDuty) {
        underDutyRepository.create(underDuty);
    }

    public UnderDuty findById(Long id){
        return underDutyRepository.findById(id);
    }

    public List<UnderDuty> findAll(){
        return underDutyRepository.findAll();
    }

    public void delete(Long id){
        underDutyRepository.delete(id);
    }

    public List<UnderDuty> findAllByDutyId(Long id) {
        return underDutyRepository.getUnderDutiesByDutyId(id);
    }

    public UnderDuty findByDutyIdAndName(Long id, String name) {
        return underDutyRepository.getUnderDutyByDutyIdAndName(id, name);
    }
}
