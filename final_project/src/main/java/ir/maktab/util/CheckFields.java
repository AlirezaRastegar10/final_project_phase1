package ir.maktab.util;


import ir.maktab.entity.Duty;
import ir.maktab.entity.Expert;
import ir.maktab.entity.UnderDuty;
import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.exceptions.*;
import ir.maktab.exceptions.FileNotFoundException;
import ir.maktab.service.DutyService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.UnderDutyService;
import org.apache.commons.validator.routines.EmailValidator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.time.LocalDateTime;

public class CheckFields {
    private final DutyService dutyService = new DutyService();
    private final UnderDutyService underDutyService = new UnderDutyService();
    private final ExpertService expertService = new ExpertService();

    public void nameValidation(String name, String fieldName) {
        if (name.length() < 3) {
            throw new InvalidNameException(fieldName + " should be more than 2 character.");
        } else if (!name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-])*$")) {
            throw new InvalidNameException(fieldName + " can not have number and sign(!,@,#,%,...).");
        }
    }

    public void emailValidation(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException("the email entered is not valid.");
        }
    }

    public void passwordValidation(String password) {
        if (password.length() != 8) {
            throw new InvalidPasswordException("the length of the password must be 8.");
        } else if (!password.matches("^(?=.*?\\d)(?=.*?[a-zA-Z])[a-zA-Z\\d]+$")) {
            throw new InvalidPasswordException("the password must contain letters and numbers.");
        }
    }

    public byte[] imageValidation(String url) {
        File file = new File(url);
        if (!file.exists()) {
            throw new FileNotFoundException("no files found at the requested url");
        } else if (!file.getName().endsWith(".jpg")){
            throw new FileNotJPEGException("the photo format is not jpg.");
        } else if (file.length() > 307200) {
            throw new BigFileException("the size of the photo is more than 300 kb.");
        }
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        try {
            File newFile = new File("H:\\maktab83\\final_project\\final_project\\src\\main\\java\\ir\\maktab\\images\\" + file.getName());
            ImageIO.write(bufferedImage, "jpg", newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.getData();
    }

    public void dutyIdValidation(Long id) {
        Duty duty = dutyService.findById(id);
        if (duty == null) {
            throw new DutyNotFoundException("there is no duty with this ID.");
        }
    }

    public void underDutyIdValidation(Long id) {
        UnderDuty underDuty = underDutyService.findById(id);
        if (underDuty == null) {
            throw new UnderDutyNotFoundException("there is no under duty with this ID.");
        }
    }

    public void proposedPriceValidation(Long proposedPrice, Long underDutyId) {
        UnderDuty underDuty = underDutyService.findById(underDutyId);
        if (proposedPrice < underDuty.getBasePrice()) {
            throw new LessProposedPriceException("your bid price is lower than the base price.");
        }
    }

    public void descriptionValidation(String description) {
        if (!description.matches("^[a-zA-Z\\d _.,-]{2,}$")) {
            throw new InvalidDescriptionException("the description is not suitable.");
        }
    }

    public void addressValidation(String address) {
        if (!address.matches("^[a-zA-Z\\d _.,-]{2,}$")) {
            throw new InvalidAddressException("the entered address is not correct.");
        }
    }

    public void dateAndTimeValidation(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        if (localDateTime.isBefore(now)) {
            throw new DateAndTimeException("the entered date is less than today's date.");
        }
    }

    public void expertIdValidation(Long id, ExpertStatus expertStatus) {
        Expert expert = expertService.findByStatusAndId(id, expertStatus);
        if (expert == null) {
            throw new ExpertNotFoundException("no experts were found with this ID.");
        }
    }
}
