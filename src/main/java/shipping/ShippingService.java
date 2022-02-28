package shipping;

import java.util.*;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(transportable -> transportable.getWeight() >= weight)
                .filter(transportable -> transportable.isBreakable() == breakable)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new HashMap<>();
        for (Transportable transportable : packages) {
            int count = result.computeIfAbsent(transportable.getDestinationCountry(), k -> 0);
            result.put(transportable.getDestinationCountry(), count + 1);
        }
        return result;
    }


    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream()
                .filter(InternationalPackage.class::isInstance)
                .sorted(Comparator.comparingInt(t -> ((InternationalPackage) t).getDistance()))
                .toList();
    }

    public List<Transportable> getPackages() {
        return Collections.unmodifiableList(packages);
    }
}
