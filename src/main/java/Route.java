public class Route {
    private static int nextRouteID = 100;
    private int routeID;
    private double distance;
    private int numberOfStops;

    public Route(double distance, int numberOfStops){
        this.routeID= nextRouteID++;
        this.distance = distance;
        this.numberOfStops = numberOfStops;
    }

    public int getRouteID() {
        return routeID;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    @Override
    public String toString(){
        return routeID+" - "+distance+" - "+numberOfStops;
    }
}
