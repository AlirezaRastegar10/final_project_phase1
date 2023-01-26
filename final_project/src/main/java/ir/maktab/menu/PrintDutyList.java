package ir.maktab.menu;

import ir.maktab.entity.Duty;
import ir.maktab.exceptions.DutyNotFoundException;
import ir.maktab.service.DutyService;


import java.util.List;

public class PrintDutyList {

    private final DutyService dutyService = new DutyService();

    public void getAllDuty() {
        List<Duty> list = dutyService.findAll();
        if (list == null) {
            throw new DutyNotFoundException("there are no duties.");
        } else {
            System.out.println("_____________________________");
            System.out.println("| Id  |         Name        |");
            System.out.println("_____________________________");
            for (Duty duty : list) {
                System.out.printf("|  %-3s|   %-18s|%n",
                        duty.getId(),
                        duty.getName());
                System.out.println("_____________________________");
            }
        }
    }
}
