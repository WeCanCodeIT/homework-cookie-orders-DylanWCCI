package cookies;

import java.util.*;
import java.util.stream.Collectors;

public class OrderApp {
    public static void main(String[] args) {
        MasterOrder ordersMasterList = new MasterOrder();
        Scanner scanner = new Scanner(System.in);
        List<Cookie> availableCookies = Arrays.asList(new Cookie("Tagalongs"), new Cookie("Thin Mints"), new Cookie("Samoas"));

        while(true) {
            System.out.println("Available cookies:");
            for(int i=0; i<availableCookies.size(); i++) {
                System.out.println((i+1) + ". " + availableCookies.get(i).getVariety());
            }
            System.out.print("Select a cookie (or type 'done' to finish): ");
            String variety = scanner.nextLine();
            if(variety.equals("done")) {
                break;
            }

            System.out.print("How many boxes of " + variety + " do you want? ");
            int numBoxes = scanner.nextInt();
            scanner.nextLine();

            ordersMasterList.addOrder(new CookieOrder(new Cookie(variety), numBoxes));

            System.out.println("Added " + numBoxes + " boxes of " + variety + " to your order.");
        }

        List<CookieOrder> orders = ordersMasterList.getOrdersMap().values().stream().flatMap(List::stream).collect(Collectors.toList());

        System.out.println("\nYour order:");
        for (CookieOrder order : orders) {
            System.out.println("Variety: " + order.getCookie().getVariety() + ", Boxes: " + order.getNumBoxes());
        }
        System.out.println("Total boxes: " + ordersMasterList.getTotalBoxes());

        System.out.print("\nDo you want to remove a cookie variety from your order? (yes/no): ");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("yes")) {
            System.out.print("Enter the variety you want to remove: ");
            String varietyToRemove = scanner.nextLine();
            System.out.print("Enter the number of boxes you want to remove: ");
            int boxesToRemove = scanner.nextInt();
            scanner.nextLine(); 
            ordersMasterList.removeVariety(varietyToRemove, boxesToRemove);
            System.out.println("Removed " + boxesToRemove + " boxes of " + varietyToRemove + " from your order.");
        }

        orders = ordersMasterList.getOrdersMap().values().stream().flatMap(List::stream).collect(Collectors.toList());

        System.out.println("\nYour updated order:");
        for (CookieOrder order : orders) {
            System.out.println("Variety: " + order.getCookie().getVariety() + ", Boxes: " + order.getNumBoxes());
        }
        System.out.println("Total boxes: " + ordersMasterList.getTotalBoxes());

        scanner.close();
    }
}
