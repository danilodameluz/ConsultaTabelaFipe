package br.com.alura.tabelafipe.models;

public class Vehicle {
    private String value;
    private String brand;
    private String model;
    private int year;
    private String fuel;

    public Vehicle(VehiclesData vehiclesData) {
        this.value = vehiclesData.value().replace("R$ ","");
        this.brand = vehiclesData.brand();
        this.model = vehiclesData.model();
        this.year = Integer.parseInt(vehiclesData.year());
        this.fuel = vehiclesData.fuel();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "valor=R$ " + value +
                ", marca= " + brand + '\'' +
                ", modelo= " + model + '\'' +
                ", ano= " + year +
                ", combustivel= " + fuel + '\'' +
                '}';
    }
}
