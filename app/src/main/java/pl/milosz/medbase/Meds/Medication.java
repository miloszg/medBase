package pl.milosz.medbase.Meds;
/**
 * Obiekt reprezentujący lek pobierany przez użytkownika
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Medication {
    private String name;
    private String dose;
    private String manufacturer;
    private int drawable;

    public Medication(String name, String dose, String manufacturer, int drawable) {
        this.name = name;
        this.dose = dose;
        this.manufacturer = manufacturer;
        this.drawable=drawable;
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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
