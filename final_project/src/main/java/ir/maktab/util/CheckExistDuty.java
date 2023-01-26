package ir.maktab.util;

import ir.maktab.entity.Duty;
import ir.maktab.exceptions.DutyExistException;
import ir.maktab.service.DutyService;

public class CheckExistDuty {
    private final DutyService dutyService = new DutyService();

    public void isExist(String name) {
        Duty duty = dutyService.findByName(name);
        if (duty != null) {
            throw new DutyExistException("a duty already exists with this name.");
        }
    }
}
