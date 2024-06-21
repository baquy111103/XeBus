import java.util.HashMap;
import java.util.Map;

public class AssignmentTable {
    private Driver driver;
    private Map<Route,Integer> assignments;

    public AssignmentTable(Driver driver){
        this.driver = driver;
        this.assignments = new HashMap<>();
    }

    public void assignRoute(Route route, int numberOfRounds){
        assignments.put(route, numberOfRounds);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Map<Route, Integer> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Route, Integer> assignments) {
        this.assignments = assignments;
    }

    public double calculateTotalDistance() {
        return assignments.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getDistance() * entry.getValue())
                .sum();
    }
    @Override
    public String toString() {
        return "AssignmentTable{" +
                "driver=" + driver +
                ", assignments=" + assignments +
                '}';
    }
}
