package ir.maktab.util;

import ir.maktab.entity.UnderDuty;
import ir.maktab.exceptions.UnderDutyExistException;
import ir.maktab.service.UnderDutyService;

public class CheckExistUnderDuty {
    private final UnderDutyService underDutyService = new UnderDutyService();

    public void isExist(Long dutyId, String name) {
        UnderDuty underDuty = underDutyService.findByDutyIdAndName(dutyId, name);
        if (underDuty != null) {
            throw new UnderDutyExistException("an under duty already exists with this name in this duty.");
        }
    }
}
