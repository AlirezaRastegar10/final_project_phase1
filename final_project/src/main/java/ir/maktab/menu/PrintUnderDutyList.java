package ir.maktab.menu;


import ir.maktab.entity.Expert;
import ir.maktab.entity.UnderDuty;
import ir.maktab.exceptions.UnderDutyNotFoundException;
import ir.maktab.service.ExpertService;
import ir.maktab.service.UnderDutyService;

import java.util.List;
import java.util.Set;

public class PrintUnderDutyList {
    private final UnderDutyService underDutyService = new UnderDutyService();
    private final ExpertService expertService = new ExpertService();
    public void getUnderDuties(Long id) {
        List<UnderDuty> list = underDutyService.findAllByDutyId(id);
        if (list == null) {
            throw new UnderDutyNotFoundException("there are no under duties.");
        } else {
            System.out.println("________________________________________________________________________________________________________");
            System.out.println("| Id  |         Name        |    Base Price    |          Explanation          |         Parent        |");
            System.out.println("________________________________________________________________________________________________________");
            for (UnderDuty underDuty : list) {
                System.out.printf("|  %-3s|   %-18s|   %-15s| %-30s|   %-18s|%n",
                        underDuty.getId(),
                        underDuty.getName(),
                        underDuty.getBasePrice(),
                        underDuty.getExplanation(),
                        underDuty.getDuty().getName());
                System.out.println("________________________________________________________________________________________________________");
            }
        }
    }

    public void getUnderDutyByExpertId(Long expertId) {
        Expert expert = expertService.findById(expertId);
        Set<UnderDuty> set = expert.getUnderDutySet();
        if (set == null) {
            throw new UnderDutyNotFoundException("there are no under duties.");
        } else {
            System.out.println("________________________________________________________________________________________________________");
            System.out.println("| Id  |         Name        |    Base Price    |          Explanation          |         Parent        |");
            System.out.println("________________________________________________________________________________________________________");
            for (UnderDuty underDuty : set) {
                System.out.printf("|  %-3s|   %-18s|   %-15s| %-30s|   %-18s|%n",
                        underDuty.getId(),
                        underDuty.getName(),
                        underDuty.getBasePrice(),
                        underDuty.getExplanation(),
                        underDuty.getDuty().getName());
                System.out.println("________________________________________________________________________________________________________");
            }
        }
    }

    public void getUnderDutiesExperts(Long id) {
        UnderDuty underDuty = underDutyService.findById(id);
        if (underDuty.getExpertSet().isEmpty()) {
            throw new UnderDutyNotFoundException("there are no under duties.");
        } else {
            System.out.println("__________________________________");
            System.out.println("| Id  |         Full Name        |");
            System.out.println("__________________________________");
            for (Expert expert : underDuty.getExpertSet()) {
                System.out.printf("|  %-3s|   %-23s|%n",
                        expert.getId(),
                        expert.getUser().getFirstname() + " " + expert.getUser().getLastname());
                System.out.println("__________________________________");
            }
        }
    }
}
