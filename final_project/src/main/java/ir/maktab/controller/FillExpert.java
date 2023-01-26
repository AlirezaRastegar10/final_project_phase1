package ir.maktab.controller;

import ir.maktab.entity.Expert;
import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.util.SetFields;

public class FillExpert {
    private final SetFields setFields = new SetFields();

    public Expert getExpert() {
        byte[] imageUrl = setFields.setExpertImage();
        return new Expert(ExpertStatus.NEW, imageUrl, 50_000L, 0);
    }
}
