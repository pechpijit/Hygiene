package com.tong.hygiene.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProfile {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("numberHN")
    @Expose
    private Integer numberHN;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("idCard")
    @Expose
    private String idCard;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("AppointmentToCheck")
    @Expose
    private String appointmentToCheck;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getNumberHN() {
        return numberHN;
    }

    public void setNumberHN(Integer numberHN) {
        this.numberHN = numberHN;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppointmentToCheck() {
        return appointmentToCheck;
    }

    public void setAppointmentToCheck(String appointmentToCheck) {
        this.appointmentToCheck = appointmentToCheck;
    }
}
