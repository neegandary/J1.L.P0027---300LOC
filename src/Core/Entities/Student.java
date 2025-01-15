package Core.Entities;

import Utilities.DataValidatation;
import Utilities.Constant;
import Utilities.Algorithm;

public class Student {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private int tuitionFee;

    public Student(String id, String name, String phone, String email, String mountainCode) throws Exception {
        setId(id);
        setName(name);
        setPhoneNumber(phone);
        setEmail(email);
        setMountainCode(mountainCode);
        setTuitionFee(phone);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setId(String id) throws Exception {
        if (!DataValidatation.checkStringWithFormat(id.toUpperCase(), Constant.ID_PATTERN)) {
            throw new Exception("Id is invalid. Please enter again.");
        }
        this.id = id;
    }

    public void setName(String name) throws Exception {
        if (!DataValidatation.checkStringWithFormat(name, Constant.NAME_PATTERN)) {
            throw new Exception("Name must be from 2 to 20 characters.");
        }
        this.name = Algorithm.toTitleCase(name);
    }

    public void setPhoneNumber(String phone) throws Exception {
        if (!DataValidatation.checkStringWithFormat(phone,
                Constant.PHONE_PATTERN)) {
            throw new Exception("Phone number is invalid");
        }
        this.phone = phone;
    }

    public void setEmail(String email) throws Exception {
        if (!DataValidatation.checkStringWithFormat(email, Constant.EMAIL_PATTERN)) {
            throw new Exception("Email is invalid");
        }
        this.email = email;
    }

    public void setMountainCode(String mountainCode) throws Exception {

        if (!DataValidatation.checkMatchCode(mountainCode, Constant.validMountainCode)) {
            throw new Exception("Code is invalid");
        }
        this.mountainCode = mountainCode;
    }

    public void setTuitionFee(String phone) throws Exception {
        if (Constant.whitelistPhone.contains(phone.substring(0, 3))) {
            this.tuitionFee = 3900000;
            return;
        }
        this.tuitionFee = 6000000;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %d", id, name, email, phone, mountainCode, tuitionFee);
    }
}
