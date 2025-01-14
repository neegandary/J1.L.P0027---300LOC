package Core.Entities;

public class Mountain {
    private String mountainCode;
    private String mountain;
    private String province;
    private String description;

    public Mountain(String mountainCode, String mountain, String province, String description) {
        this.mountainCode = mountainCode;
        this.mountain = mountain;
        this.province = province;
        this.description = description;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public String getMountain() {
        return mountain;
    }

    public String getProvince() {
        return province;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", mountainCode, mountain, province, description);
    }
}
