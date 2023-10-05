import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class MyGarage implements Garage{
    private TreeSet<Car> carsByVelocity;
    private HashMap<Owner, HashSet<Car>> carsOfOwner;
    private HashMap<String, HashSet<Car>> carsOfBrand;

    private HashMap<Integer, Owner> ownerOfCar;

    private HashMap<Integer, Car> carWithId;

    private TreeSet<Car> carsByPower = new TreeSet<>(new SortByPower());

    public MyGarage() {
        carsByVelocity = new TreeSet<Car>(new SortByVelocity());
        carsOfOwner = new HashMap<Owner, HashSet<Car>>();
        carsOfBrand = new HashMap<String, HashSet<Car>>();
        ownerOfCar = new HashMap<Integer, Owner>();
        carWithId = new HashMap<Integer, Car>();
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return carsOfOwner.keySet();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        List<Car> topCars = new ArrayList<>();
        var topCar = carsByVelocity.first();
        for (int i = 0; i < 3; i++) {
            if (topCar == null) {
                break;
            }
            topCars.add(topCar);
            topCar = carsByVelocity.higher(topCar);
        }
        return topCars;
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return carsOfBrand.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        var fakeCar = new Car(MAX_VALUE, "", "", -1, power, -1);
        return carsByPower.tailSet(fakeCar);
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return carsOfOwner.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        if (carsOfBrand.get(brand) == null)
            return 0;
        var ownersOfBrand = new HashSet<Owner>();
        for (var car : carsOfBrand.get(brand)) {
             ownersOfBrand.add(ownerOfCar.get(car.getCarId()));

        }
        int ageSum = 0;
        for (var owner : ownersOfBrand) {
            ageSum += owner.getAge();
        }
        int ownersNumber = ownersOfBrand.size();
        return ageSum / ownersNumber;
    }

    @Override
    public int meanCarNumberForEachOwner() {
        int ownersNumber = carsOfOwner.size();
        int carsNumber = carsByVelocity.size();
        if (carsNumber == 0)
            return 0;
        return carsNumber / ownersNumber;
    }

    @Override
    public Car removeCar(int carId) {
        var car = carWithId.remove(carId);
        var owner = ownerOfCar.remove(carId);
        carsByVelocity.remove(car);
        carsOfOwner.get(owner).remove(car);
        if (carsOfOwner.get(owner).isEmpty()) {
            carsOfOwner.remove(owner);
        }
        carsOfBrand.get(car.getBrand()).remove(car);
        if (carsOfBrand.get(car.getBrand()).isEmpty()) {
            carsOfBrand.remove(car.getBrand());
        }
        carsByPower.remove(car);
        return car;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        carsByVelocity.add(car);
        if (!carsOfOwner.containsKey(owner)) {
            carsOfOwner.put(owner, new HashSet<Car>());
        }
        carsOfOwner.get(owner).add(car);
        if (!carsOfBrand.containsKey(car.getBrand())) {
            carsOfBrand.put(car.getBrand(), new HashSet<Car>());
        }
        carsOfBrand.get(car.getBrand()).add(car);
        ownerOfCar.put(car.getCarId(), owner);
        carWithId.put(car.getCarId(), car);

        carsByPower.add(car);
    }
}
