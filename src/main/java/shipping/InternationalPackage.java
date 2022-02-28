package shipping;

public class InternationalPackage implements Transportable {

    private static final int TRANSPORT_PRICE = 1_200;

    private int weight;

    private boolean breakable;

    private String destinationCountry;

    private int distance;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        return (breakable ? 2 * TRANSPORT_PRICE : TRANSPORT_PRICE)
               + 10 * distance;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}
