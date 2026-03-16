public class PalindromeCheckerApp {

        // UC3 addition: centralized inventory using HashMap
    static class RoomInventory {
        private HashMap<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
        }

        public void addRoomType(String roomType, int count) {
            inventory.put(roomType, count);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void updateAvailability(String roomType, int newCount) {
            if (inventory.containsKey(roomType)) {
                inventory.put(roomType, newCount);
            } else {
                System.out.println("Room type " + roomType + " not found in inventory.");
            }
        }

        public void displayInventory() {
            System.out.println("Current Room Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Palindrome Checker Management System");
        System.out.println("Version : 1.0");
        System.out.println("System initialized successfully.");
    
     String word = "madam";
        String reverse = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reverse = reverse + word.charAt(i);
        }

        if (word.equals(reverse)) {
            System.out.println(word + " is a palindrome.");
        } else {
            System.out.println(word + " is not a palindrome.");
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string for recursive palindrome check: ");
        String input = sc.nextLine();

        if (isPalindromeRecursive(input, 0, input.length() - 1)) {
            System.out.println(input + " is a palindrome.");
        } else {
            System.out.println(input + " is not a palindrome.");
        }

        System.out.println("\nInitializing centralized room inventory...");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Standard", 10);
        inventory.addRoomType("Deluxe", 5);
        inventory.addRoomType("Suite", 2);

        inventory.displayInventory();

        System.out.println("\nChecking availability for Deluxe: " +
                inventory.getAvailability("Deluxe"));

        System.out.println("\nUpdating Deluxe rooms to 4...");
        inventory.updateAvailability("Deluxe", 4);

        inventory.displayInventory();
    }
}
