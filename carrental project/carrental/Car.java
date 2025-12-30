package carrental;

public class Car {

    private  int registerNumber;
    private  String brand;
    private String model;      
    private int year;
    private double rentalPrice;




    public Car(int registerNumber ,String brand,String model,int year,double rentalPrice){

        this.registerNumber=registerNumber;
        this.brand=brand;
        this.model=model;
        this.year=year;
        this.rentalPrice=rentalPrice;

    }

public int getRegisterNumber() {
    return registerNumber;
}

public void setRegisterNumber(int registerNumber) {
    this.registerNumber = registerNumber;
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

public double getRentalPrice() {
    return rentalPrice;
}

public void setRentalPrice(double rentalPrice) {
    this.rentalPrice = rentalPrice;
}

}