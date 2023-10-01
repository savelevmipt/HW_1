// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        var garage = new MyGarage();
        var car = new Car(221, "Lada", "Granta", 150, 100500, 1);
        var owner = new Owner("Alexandr", "Savelev", 21);
        var owner2 = new Owner("Vladimir", "Putin", 71);
        var car1 = new Car(999, "Lada", "Granta", 160, 100500, 1);
        var car2 = new Car(111, "Lada", "Sedan", 500, 999999, 2);
        garage.addNewCar(car, owner);
        garage.addNewCar(car1, owner);
        garage.addNewCar(car2, owner2);
        System.out.println(car1.equals(car));
        var Ladas = garage.allCarsOfBrand("Lada");
        System.out.println(Ladas.size());
        for (var lada : Ladas) {
            System.out.println(lada.getCarId());
        }
        var top = garage.topThreeCarsByMaxVelocity();
        System.out.println(top.size());
        for (var auto : top) {
            System.out.print(auto.getBrand());
            System.out.println(auto.getMaxVelocity());
        }
        System.out.println(garage.meanCarNumberForEachOwner());
        System.out.println(garage.meanOwnersAgeOfCarBrand("Lada"));
        for (var auto : garage.carsWithPowerMoreThan(100500)) {
            System.out.println(auto.getPower());
        }
        garage.removeCar(111);
        for (var lada : garage.allCarsOfBrand("Lada")) {
            System.out.println(lada.getCarId());
        }


    }

}