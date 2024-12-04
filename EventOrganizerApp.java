import java.util.ArrayList;
import java.util.Scanner;

public class EventOrganizerApp {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Aplikasi Event Organizer ===");
            System.out.println("1. Tambah Acara");
            System.out.println("2. Lihat Semua Acara");
            System.out.println("3. Urutkan Acara Berdasarkan Nama (Selection Sort)");
            System.out.println("4. Urutkan Acara Berdasarkan Tanggal (Merge Sort)");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (choice) {
                case 1 -> tambahAcara();
                case 2 -> lihatAcara();
                case 3 -> urutkanBerdasarkanNama();
                case 4 -> urutkanBerdasarkanTanggal();
                case 5 -> System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 5);
    }

    public static void tambahAcara() {
        System.out.print("Masukkan nama acara: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan tanggal acara (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Masukkan deskripsi acara: ");
        String description = scanner.nextLine();
        System.out.print("Masukkan lokasi acara: ");
        String location = scanner.nextLine();

        events.add(new Event(name, date, description, location));
        System.out.println("Acara berhasil ditambahkan!");
    }

    public static void lihatAcara() {
        if (events.isEmpty()) {
            System.out.println("Tidak ada acara.");
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i));
            }
        }
    }

    public static void urutkanBerdasarkanNama() {
        for (int i = 0; i < events.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(j).getName().compareToIgnoreCase(events.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            Event temp = events.get(minIndex);
            events.set(minIndex, events.get(i));
            events.set(i, temp);
        }
        System.out.println("Acara diurutkan berdasarkan nama.");
    }

    public static void urutkanBerdasarkanTanggal() {
        events = mergeSort(events);
        System.out.println("Acara diurutkan berdasarkan tanggal.");
    }

    public static ArrayList<Event> mergeSort(ArrayList<Event> list) {
        if (list.size() <= 1) {
            return list;
        }
        int mid = list.size() / 2;
        ArrayList<Event> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Event> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    public static ArrayList<Event> merge(ArrayList<Event> left, ArrayList<Event> right) {
        ArrayList<Event> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getDate().compareTo(right.get(j).getDate()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }
}

class Event {
    private final String name;
    private final String date;
    private final String description;
    private final String location;

    public Event(String name, String date, String description, String location) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Tanggal: " + date + ", Deskripsi: " + description + ", Lokasi: " + location;
    }
}