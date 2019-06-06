package pl.milosz.medbase;

public class Medication {
    private String name;
    private String dose;
    private String manufacturer;

    public Medication(String name, String dose, String manufacturer) {
        this.name = name;
        this.dose = dose;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
