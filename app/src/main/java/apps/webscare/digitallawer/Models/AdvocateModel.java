package apps.webscare.digitallawer.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvocateModel {

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}