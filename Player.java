import java.util.Scanner ;
import java.util.Random;
import java.util.ArrayList;

public class Player {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static String name ;
    static int level = 1;
    static int xp = 0;
    static final int maxXP = 100;
    int trainingCount = 0 ;
    int explorationCount = 0 ;

    ArrayList<String> badges = new ArrayList<>();

    static ArrayList<String> inventory = new ArrayList<>();

    Player(String name) {
        this.name = name ;
    }

    static void gainXP(int amount) {
        xp += amount;
        if (xp >= maxXP) {
            level++;
            xp -= maxXP;
            System.out.println("You leveled up! Now you're level " + level + "!");
        }
    }

    static void addItem(String item) {
        inventory.add(item);
        System.out.println("You found a " + item + "!");
    }

    static void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty. ");
        } else {
            System.out.println("🎒 Your Inventory: ");
            for (int i = 0 ; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i));
            }
        }
    }

    void checkAchievements() {
        if (trainingCount >= 5 && !badges.contains("Fitness Freak")) {
            badges.add("Fitness Freak");
            System.out.println("🏅 You earned the 'Fitness Freak' badge!");
        }

        if (explorationCount >= 3 && !badges.contains("Explorer")) {
            badges.add("Explorer");
            System.out.println("🧭 You earned the 'Explorer' badge!");
        }

        if (level >= 3 && !badges.contains("Level Up Master")) {
            badges.add("Level Up Master");
            System.out.println("⚡ You earned the 'Level Up Master' badge!");
        }
    }

    static String displayStats() {
        return "Name: " + name + " | Level: " + level + " | XP: " + xp + " xp";
    }

    public static void main(String[] args) {

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Player player = new Player(name);

        String[] possibleItems = {"Potion of Healing 💗", "Mana Gem 💎", "Scroll of Knowledge 🎓", "Scroll of Summoning 📜", "Ancient Key 🔑", "Binoculars 🥽", "Torch of Enlightenment 🔦"};

        int choice;

        do {
            System.out.println("Welcome, " + name + " the Adventurer!");
            System.out.println("Choose your action:");
            System.out.println(" 1. Train ");
            System.out.println(" 2. Explore ");
            System.out.println(" 3. View Stats ");
            System.out.println(" 4. Exit  ");
            System.out.println(" 5. View Inventory ");
            System.out.println(" 6. View Badges  ");

            choice = sc.nextInt();

            if (choice == 1){
                player.gainXP(10);
                player.trainingCount++ ;
                System.out.println("You trained hard! +10 XP.");
                player.checkAchievements();
            } else if (choice == 2) {
                player.gainXP(5);
                player.explorationCount++ ;
                String item = possibleItems[random.nextInt(possibleItems.length)] ;
                player.addItem(item);
                player.checkAchievements();
            } else if (choice == 3) {
                System.out.println(player.displayStats());
            } else if (choice == 5) {
                player.showInventory();
            } else if (choice == 6) {
                if (player.badges.isEmpty()) {
                    System.out.println("No badges yet. Keep going!");
                } else {
                    System.out.println("🏆 Badges Earned: ");
                    for (String badge: player.badges) {
                        System.out.println("- "+ badge) ;
                    }
                }
            }

        } while (choice != 4) ;

        System.out.println("Goodbye, Adventurer!") ;
    }

}
