import java.util.Comparator;

public class SortByPower implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        var answer = (int) Math.signum(car1.getPower() - car2.getPower());
        if (answer != 0) {
            return answer;
        }
        return (int) Math.signum(car1.getCarId() - car2.getCarId());
    }
}
