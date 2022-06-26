import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            ReloadAll.Reload();
        } catch (IOException e) {
            System.out.println("");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
        Registry.display();
    }
}

class Person {
    private String code;
    private String name;
    private String sexuality;
    private int age;
    private long wallet;

    public Person(String code, String name, String sexuality, int age, int wallet) {
        this.code = code;
        this.name = name;
        this.sexuality = sexuality;
        this.age = age;
        this.wallet = wallet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getSexuality() {
        return sexuality;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }
}

class Land {
    private String registrationCode;
    private String ownerCode;
    private String address;
    private LocalDate tradeDate;
    private long worth;

    public Land(String registrationCode, String ownerCode, String address, LocalDate tradeDate, long worth) {
        this.registrationCode = registrationCode;
        this.ownerCode = ownerCode;
        this.address = address;
        this.tradeDate = tradeDate;
        this.worth = worth;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public long getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }
}


abstract class Account {
    private String number;
    private String ownerCode;
    private int amount;
    private LocalDate foundDate;
    private int minusScore;

    public Account(String number, String ownerCode, int amount, LocalDate foundDate, int minusScore) {
        this.number = number;
        this.ownerCode = ownerCode;
        this.amount = amount;
        this.foundDate = foundDate;
        this.minusScore = minusScore;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public int getMinusScore() {
        return minusScore;
    }

    public void setMinusScore(int minusScore) {
        this.minusScore = minusScore;
    }
}

class ActiveAccount extends Account {
    private String checkBook;
    private String card;

    public ActiveAccount(String number, String ownerCode, int amount, LocalDate foundDate, int minusScore, String checkBook, String card) {
        super(number, ownerCode, amount, foundDate, minusScore);
        this.checkBook = checkBook;
        this.card = card;
    }

    public String getCheckBook() {
        return checkBook;
    }

    public void setCheckBook(String checkBook) {
        this.checkBook = checkBook;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}

class SavingsAccount extends Account {
    private String card;

    public SavingsAccount(String number, String ownerCode, int amount, LocalDate foundDate, int minusScore, String card) {
        super(number, ownerCode, amount, foundDate, minusScore);
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}

class DepositAccount extends Account {
    private String type;
    private double bonus;
    private int duration;

    public DepositAccount(String number, String ownerCode, int amount, LocalDate foundDate, int minusScore, String type) throws InvalidTypeException {
        super(number, ownerCode, amount, foundDate, minusScore);
        this.type = type;
        this.bonus = setBonus(type);
        this.duration = setDuration(type);
    }

    private double setBonus(String type) throws InvalidTypeException {
        if (type.equals("short")) return .1;
        if (type.equals("long")) return .3;
        if (type.equals("special")) return .5;
        else throw new InvalidTypeException();
    }

    private int setDuration(String type) throws InvalidTypeException {
        if (type.equals("short")) return 10;
        if (type.equals("long")) return 30;
        if (type.equals("special")) return 50;
        else throw new InvalidTypeException();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

class InvalidTypeException extends Exception {
    public InvalidTypeException() {
        super("Invalid account type.");
    }
}

class Registry {
    static Scanner cin = new Scanner(System.in);
    static ArrayList<Person> people = new ArrayList<>();

    static void display() {
        System.out.println("1. Register\n2. Edit\n3. Delete\n4. Reload\n5. Exit");
        short n = cin.nextShort();
        switch (n) {
            case 1 -> Register();
            case 2 -> {
                System.out.println("Enter the code:");
                String code = cin.next();
                Edit(code);
            }
            case 3 -> {
                System.out.println("Enter the code:");
                String code = cin.next();
                Delete(code);
            }
            case 4 -> {
                try {
                    System.out.println("Enter the code:");
                    String code = cin.next();
                    Reload(code);
                } catch (IOException e) {
                    System.out.println("IOex");
                    ;
                } catch (ParseException e) {
                    System.out.println("Pars ex");
                }
            }
            case 5 -> System.exit(0);
            default -> {
                System.out.println("What do you mean?");
                display();
            }
        }
    }

    static void Register() {

        System.out.println("Enter the name:");
        String name = cin.next();
        System.out.println("Enter the code:");
        String code = cin.next();
        System.out.println("Enter the Age:");
        int age = cin.nextInt();
        System.out.println("Enter the sexuality:");
        String sexuality = cin.next();
        System.out.println("Enter the wallet value:");
        int wallet = cin.nextInt();

        Person person = new Person(code, name, sexuality, age, wallet);
        people.add(person);
        Files.PrintPerson(person);
        System.out.println("Person is registered.");
        display();

    }

    static void Edit(String code) {
        Person p = FindPerson.findPerson(code);
        if (p == null) {
            System.out.println("User not found");
            display();
        }
        System.out.println("1. Name\n2. Code\n3. Age\n4. Sexuality\n5. Wallet Value\n6. Exit");
        short n = cin.nextShort();
        switch (n) {
            case 1 -> {
                System.out.println("Enter new name:");
                String name = cin.next();
                assert p != null;
                p.setName(name);
                System.out.println("Name changed");
                Edit(code);
            }
            case 2 -> {
                System.out.println("Enter new code:");
                String newCode = cin.next();
                assert p != null;
                p.setCode(newCode);
                System.out.println("Code changed");
                Edit(code);
            }
            case 3 -> {
                System.out.println("Enter new age:");
                int age = cin.nextInt();
                assert p != null;
                p.setAge(age);
                System.out.println("Age changed");
                Edit(code);
            }
            case 4 -> {
                System.out.println("Enter new Sexuality:");
                String sex = cin.next();
                assert p != null;
                p.setSexuality(sex);
                System.out.println("Sexuality changed");
                Edit(code);
            }
            case 5 -> {
                System.out.println("Enter new Wallet value:");
                int wallet = cin.nextInt();
                assert p != null;
                p.setWallet(wallet);
                System.out.println("Wallet value changed");
                Edit(code);
            }
            case 6 -> {
                assert p != null;
                Files.PrintPerson(p);
                display();
            }
            default -> {
                System.out.println("What do you mean?");
                Edit(code);
            }
        }

    }

    static void Delete(String code) {
        if (FindPerson.findPerson(code) == null) {
            System.out.println("User not found");
            display();
        } else {
            people.remove(FindPerson.findPerson(code));
            System.out.println("User was successfully removed.");
            display();
        }

    }

    static void Reload(String code) throws IOException, ParseException {

        if (FindPerson.findPerson(code) != null) {
            System.out.println("We already have this user.");
            display();
        }


        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\user\\Desktop\\MainProject\\People\\" + code + ".json"));

        JSONObject jo = (JSONObject) obj;

        String firstName = (String) jo.get("Name");
        String sex = (String) jo.get("Sexuality");
        long wallet = (long) jo.get("Wallet Value");
        long age = (long) jo.get("Age");

        Person person = new Person(code, firstName, sex, (int) age, (int) wallet);
        people.add(person);
        System.out.println("User " + firstName + " with code " + code + " reloaded");
        display();
    }
}

class Files {
    static void PrintPerson(Person person) {
        JSONObject json = new JSONObject();
        json.put("Name", person.getName());
        json.put("Code", person.getCode());
        json.put("Age", person.getAge());
        json.put("Sexuality", person.getSexuality());
        json.put("Wallet Value", person.getWallet());
        String name = person.getCode() + ".json";
        try {
            PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\user\\Desktop\\MainProject\\People\\" + name));
            out.write(json.toString());
            out.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    static void PrintLand(Land land) {

    }
}

class FindPerson {
    static Person findPerson(String code) {
        for (Person p :
                Registry.people) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }
}

class LandRegistery {
    static Scanner cin = new Scanner(System.in);
    static ArrayList<Person> people = new ArrayList<>();

    static void display() {
        System.out.println("1. Register\n2. Edit\n3. Delete\n4. Reload\n5. Exit");
        short n = cin.nextShort();
        switch (n) {
            case 1 -> Register();
            case 2 -> {
                System.out.println("Enter the code:");
                String code = cin.next();
                Edit(code);
            }
            case 3 -> {
                System.out.println("Enter the code:");
                String code = cin.next();
                Delete(code);
            }
            case 4 -> {
                try {
                    System.out.println("Enter the code:");
                    String code = cin.next();
                    Reload(code);
                } catch (IOException e) {
                    System.out.println("IOex");
                    ;
                } catch (ParseException e) {
                    System.out.println("Pars ex");
                }
            }
            case 5 -> System.exit(0);
            default -> {
                System.out.println("What do you mean?");
                display();
            }
        }
    }

    static void Register() {

        System.out.println("Enter the name:");
        String name = cin.next();
        System.out.println("Enter the code:");
        String code = cin.next();
        System.out.println("Enter the Age:");
        int age = cin.nextInt();
        System.out.println("Enter the sexuality:");
        String sexuality = cin.next();
        System.out.println("Enter the wallet value:");
        int wallet = cin.nextInt();

        Person person = new Person(code, name, sexuality, age, wallet);
        people.add(person);
        Files.PrintPerson(person);
        System.out.println("Person is registered.");
        display();

    }

    static void Edit(String code) {
        Person p = FindPerson.findPerson(code);
        if (p == null) {
            System.out.println("User not found");
            display();
        }
        System.out.println("1. Name\n2. Code\n3. Age\n4. Sexuality\n5. Wallet Value\n6. Exit");
        short n = cin.nextShort();
        switch (n) {
            case 1 -> {
                System.out.println("Enter new name:");
                String name = cin.next();
                assert p != null;
                p.setName(name);
                System.out.println("Name changed");
                Edit(code);
            }
            case 2 -> {
                System.out.println("Enter new code:");
                String newCode = cin.next();
                assert p != null;
                p.setCode(newCode);
                System.out.println("Code changed");
                Edit(code);
            }
            case 3 -> {
                System.out.println("Enter new age:");
                int age = cin.nextInt();
                assert p != null;
                p.setAge(age);
                System.out.println("Age changed");
                Edit(code);
            }
            case 4 -> {
                System.out.println("Enter new Sexuality:");
                String sex = cin.next();
                assert p != null;
                p.setSexuality(sex);
                System.out.println("Sexuality changed");
                Edit(code);
            }
            case 5 -> {
                System.out.println("Enter new Wallet value:");
                int wallet = cin.nextInt();
                assert p != null;
                p.setWallet(wallet);
                System.out.println("Wallet value changed");
                Edit(code);
            }
            case 6 -> {
                assert p != null;
                Files.PrintPerson(p);
                display();
            }
            default -> {
                System.out.println("What do you mean?");
                Edit(code);
            }
        }

    }

    static void Delete(String code) {
        if (FindPerson.findPerson(code) == null) {
            System.out.println("User not found");
            display();
        } else {
            people.remove(FindPerson.findPerson(code));
            System.out.println("User was successfully removed.");
            display();
        }

    }

    static void Reload(String code) throws IOException, ParseException {

        if (FindPerson.findPerson(code) != null) {
            System.out.println("We already have this user.");
            display();
        }


        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\user\\Desktop\\MainProject\\People\\" + code + ".json"));

        JSONObject jo = (JSONObject) obj;

        String firstName = (String) jo.get("Name");
        String sex = (String) jo.get("Sexuality");
        long wallet = (long) jo.get("Wallet Value");
        long age = (long) jo.get("Age");

        Person person = new Person(code, firstName, sex, (int) age, (int) wallet);
        people.add(person);
        System.out.println("User " + firstName + " with code " + code + " reloaded");
        display();
    }
}


class ReloadAll {
    static void Reload() throws IOException, ParseException {
        List<String> results = new ArrayList<>();
        File[] files = new File("C:\\Users\\user\\Desktop\\MainProject\\People").listFiles();
        for (File file :
                files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        for (int i = 0; i < results.size(); i++) {
            Object obj = new JSONParser().parse(new FileReader("C:\\Users\\user\\Desktop\\MainProject\\People\\" + results.get(i)));

            JSONObject jo = (JSONObject) obj;

            String firstName = (String) jo.get("Name");
            String sex = (String) jo.get("Sexuality");
            String code = (String) jo.get("Code");
            long wallet = (long) jo.get("Wallet Value");
            long age = (long) jo.get("Age");

            Person person = new Person(code, firstName, sex, (int) age, (int) wallet);
            Registry.people.add(person);
            System.out.println("User " + firstName + " with code " + code + " reloaded.");
        }
    }
}
