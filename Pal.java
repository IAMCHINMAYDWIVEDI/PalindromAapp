import java.util.HashMap;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class pca {

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

    // Minimal new class added for UC4
    static class Room {
        String type;
        double price;
        String amenities;

        public Room(String type, double price, String amenities) {
            this.type = type;
            this.price = price;
            this.amenities = amenities;
        }

        public void displayDetails() {
            System.out.println("Room Type : " + type);
            System.out.println("Price : " + price);
            System.out.println("Amenities : " + amenities);
        }
    }
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void displayRequest() {
            System.out.println("Guest Name : " + guestName);
            System.out.println("Requested Room Type : " + roomType);
        }
    }
    public static boolean isPalindromeDeque(String str) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            deque.addLast(c);
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }
        // UC8: Linked List Node
    static class Node {
        char data;
        Node next;

        public Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // UC8: Linked List based palindrome checker
    public static boolean isPalindromeLinkedList(String str) {
        Node head = null;
        Node tail = null;

        for (char c : str.toCharArray()) {
            Node newNode = new Node(c);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Find middle using fast and slow pointer
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node prev = null;
        Node curr = slow;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        // Compare both halves
        Node left = head;
        Node right = prev;
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay");
        System.out.println("Version : 4.0");
        System.out.println("Initializing centralized room inventory...");

        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Standard", 10);
        inventory.addRoomType("Deluxe", 5);
        inventory.addRoomType("Suite", 0);

        inventory.displayInventory();

        System.out.println("\nChecking availability for Deluxe: "
                + inventory.getAvailability("Deluxe"));

        System.out.println("\nUpdating Deluxe rooms to 4...");
        inventory.updateAvailability("Deluxe", 4);

        inventory.displayInventory();

        // UC4 added with minimal structural change
        Room standard = new Room("Standard", 2500.0, "AC, WiFi, TV");
        Room deluxe = new Room("Deluxe", 4000.0, "AC, WiFi, TV, Mini Bar");
        Room suite = new Room("Suite", 7000.0, "AC, WiFi, TV, Mini Bar, Jacuzzi");

        Room[] rooms = {standard, deluxe, suite};

        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (inventory.getAvailability(room.type) > 0) {
                room.displayDetails();
                System.out.println("Available Count : " + inventory.getAvailability(room.type));
                System.out.println();
         Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Amit", "Deluxe"));
        bookingQueue.add(new Reservation("Priya", "Standard"));
        bookingQueue.add(new Reservation("Rahul", "Suite"));

        System.out.println("Booking Requests in Queue (First-Come-First-Served):");
        for (Reservation request : bookingQueue) {
            request.displayRequest();
            System.out.println();
        }

        System.out.println("Inventory after request intake (unchanged):");
        inventory.displayInventory();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a string to check palindrome using Deque: ");
        String input = sc.nextLine();

        if (isPalindromeDeque(input)) {
            System.out.println(input + " is a palindrome.");
        } else {
            System.out.println(input + " is not a palindrome.");
        }

        sc.close();
    }
}
