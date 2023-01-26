package ir.maktab.menu;

import ir.maktab.entity.Expert;
import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.exceptions.ExpertNotFoundException;
import ir.maktab.service.ExpertService;

import java.util.List;

public class PrintExpertList {
    private final ExpertService expertService = new ExpertService();

    public void getNewExpert() {
        List<Expert> list = expertService.findByStatus(ExpertStatus.NEW);
        if (list == null) {
            throw new ExpertNotFoundException("there are no new expert.");
        } else {
            System.out.println("______________________________________________________________________________");
            System.out.println("| Id  |    Status    |    Validity    |   Score   |         Full Name        |");
            System.out.println("______________________________________________________________________________");
            for (Expert expert : list) {
                System.out.printf("|  %-3s|  %-12s|  %-14s| %-10s|   %-23s|%n",
                        expert.getId(),
                        expert.getStatus(),
                        expert.getValidity(),
                        expert.getScore(),
                        expert.getUser().getFirstname() + " " + expert.getUser().getLastname());
                System.out.println("______________________________________________________________________________");
            }
        }
    }

    public void getAcceptedExpert() {
        List<Expert> list = expertService.findByStatus(ExpertStatus.ACCEPTED);
        if (list == null) {
            throw new ExpertNotFoundException("there are no accepted expert.");
        } else {
            System.out.println("______________________________________________________________________________");
            System.out.println("| Id  |    Status    |    Validity    |   Score   |         Full Name        |");
            System.out.println("______________________________________________________________________________");
            for (Expert expert : list) {
                System.out.printf("|  %-3s|  %-12s|  %-14s| %-10s|   %-23s|%n",
                        expert.getId(),
                        expert.getStatus(),
                        expert.getValidity(),
                        expert.getScore(),
                        expert.getUser().getFirstname() + " " + expert.getUser().getLastname());
                System.out.println("______________________________________________________________________________");
            }
        }
    }
}
