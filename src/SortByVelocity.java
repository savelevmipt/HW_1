import java.util.Comparator;

public class SortByVelocity implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        var answer = (int) Math.signum(car2.getMaxVelocity() - car1.getMaxVelocity());
        if (answer != 0) {
            return answer;
        }
        return (int) Math.signum(car2.getCarId() - car1.getCarId());
    }
}
