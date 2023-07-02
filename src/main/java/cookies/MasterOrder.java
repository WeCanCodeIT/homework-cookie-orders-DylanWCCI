package cookies;

import java.util.*;
public class MasterOrder {
    private Map<String, List<CookieOrder>> ordersMap;

    public MasterOrder() {
        this.ordersMap = new HashMap<>();
    }

    public Map<String, List<CookieOrder>> getOrdersMap() {
        return Collections.unmodifiableMap(this.ordersMap);
    }

    public void addOrder(CookieOrder order) {
        this.ordersMap.computeIfAbsent(order.getCookie().getVariety(), k -> new ArrayList<>()).add(order);

    }

    public int getTotalBoxes() {
        return this.ordersMap.values().stream().flatMap(Collection::stream).mapToInt(CookieOrder::getNumBoxes).sum();
    }

    public void removeVariety(String variety, int numBoxesToRemove) {
        List<CookieOrder> ordersOfVariety = this.ordersMap.get(variety);
        
        if (ordersOfVariety != null) {
            int remainingBoxes = numBoxesToRemove;
            
            Iterator<CookieOrder> iterator = ordersOfVariety.iterator();
            while (iterator.hasNext() && remainingBoxes > 0) {
                CookieOrder order = iterator.next();
                if (order.getNumBoxes() <= remainingBoxes) {
                    remainingBoxes -= order.getNumBoxes();
                    iterator.remove();
                } else {
                    order.setNumBoxes(order.getNumBoxes() - remainingBoxes);  
                    remainingBoxes = 0;
                }
            }
            
            if (ordersOfVariety.isEmpty()) {
                this.ordersMap.remove(variety);
            }
        } else {
            System.out.println("There's no order with this variety: " + variety);
        }
    }

    public int getVarietyBoxes(String variety) {
        return this.ordersMap.getOrDefault(variety, Collections.emptyList()).stream().mapToInt(CookieOrder::getNumBoxes).sum();
    }
}
