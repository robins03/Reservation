import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;

public class Reservation {
    private static final String[] places = {
        "Jaipur",
        "Thanjavur",
        "Mumbai",
        "Tiruchirappalli",
        "Coimbatore"
    };

    private static final String[] placesInIndia = {
        "Delhi",
        "Hyderabad",
        "Ahmedabad",
        "Chennai",
        "Goa"
    };

    public static BasicDetails ReservationForm() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("   Login Registration Form");
        System.out.println("-----------------------------------------");

        BasicDetails bd = new BasicDetails();
        System.out.println("Please Fill in Your Basic Details:");

        System.out.print("First Name: ");
        bd.firstName = in.next();

        System.out.print("Last Name: ");
        bd.lastName = in.next();

        while (true) {
            System.out.print("Phone Number (10 digits): ");
            String phoneNumber = in.next();
            if (phoneNumber.matches("\\d{10}")) {
                bd.phoneNumber = phoneNumber;
                break;
            } else {
                System.out.println("Please enter a valid 10-digit phone number.");
            }
        }

        while (true) {
            System.out.print("Your Email Address: ");
            String email = in.next();
            if (isEmailValid(email)) {
                bd.email = email;
                break;
            } else {
                System.out.println("Please enter a valid email address.");
            }
        }

        System.out.print("Your Age: ");
        bd.age = in.nextInt();

        while (true) {
            System.out.print("Select Your Gender (1. Male | 2. Female | 3. Others): ");
            Integer n = in.nextInt();
            if (n == 1) {
                bd.gender = "MALE";
                break;
            } else if (n == 2) {
                bd.gender = "FEMALE";
                break;
            } else if (n == 3) {
                bd.gender = "Others";
                break;
            } else {
                System.out.println("Please Enter a Valid Option.");
            }
        }

        System.out.println("\n");

        Hashtable<Integer, String> trains = new Hashtable<Integer, String>();
        trains.put(1, "Uzhavan Express");
        trains.put(2, "Chennai Express");
        trains.put(3, "Chendur Superfast Express");
        trains.put(4, "Chozhan Superfast Express");
        trains.put(5, "Tejas Express");

        System.out.println("Available Trains:");
        for (Map.Entry<Integer, String> entry : trains.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        while (true) {
            System.out.print("Enter Train Number: ");
            bd.trainNumber = in.nextInt();
            if (bd.trainNumber <= 5 && bd.trainNumber >= 1) {
                break;
            }
            System.out.println("Please Enter a Valid Train Number.");
        }
        System.out.println("You've Chosen to Travel on: " + trains.get(bd.trainNumber));
        bd.train = trains.get(bd.trainNumber);
        System.out.println("\n");

        System.out.print("Available Classes: 1. 2S 2. 1AC 3. 2AC 4. 3AC 5. SL\nSelect Your Class: ");
        bd.classType = in.nextInt();
        System.out.println("\n");

        // Capture current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        bd.dateOfJourney = dateTimeFormat.format(calendar.getTime());

        System.out.println("From (Select a place in Tamil Nadu):");
        for (int i = 0; i < places.length; i++) {
            System.out.println((i + 1) + ". " + places[i]);
        }
        int fromChoice = in.nextInt();
        bd.from = places[fromChoice - 1];

        System.out.println("To (Select a place in India):");
        for (int i = 0; i < placesInIndia.length; i++) {
            System.out.println((i + 1) + ". " + placesInIndia[i]);
        }
        int toChoice = in.nextInt();
        bd.to = placesInIndia[toChoice - 1];

        System.out.println("\nRegistration Complete. Here are Your Details:");
        System.out.println("-----------------------------------------------");
        System.out.println("First Name: " + bd.firstName);
        System.out.println("Last Name: " + bd.lastName);
        System.out.println("Phone Number: " + bd.phoneNumber);
        System.out.println("Email Address: " + bd.email);
        System.out.println("Age: " + bd.age);
        System.out.println("Gender: " + bd.gender);
        System.out.println("Train Number: " + bd.trainNumber);
        System.out.println("Selected Train: " + bd.train);
        System.out.println("Class: " + bd.classType);
        System.out.println("Date and Time Of Journey: " + bd.dateOfJourney);
        System.out.println("From: " + bd.from);
        System.out.println("To: " + bd.to);
        System.out.println("");

        return bd;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> loginids = new ArrayList<>();
        Hashtable<String, String> d = new Hashtable<String, String>();
        Hashtable<String, BasicDetails> dd = new Hashtable<String, BasicDetails>();

        boolean accountCreated = false;

        while (true) {
            System.out.println("\n");
            System.out.println("    Online Train Reservation System");
            System.out.println("-----------------------------------------");

            if (!accountCreated) {
                System.out.println("         1. Create Account\n         2. Login\nSelect Option:");
            } else {
                System.out.println("         1. Login\nSelect Option:");
            }

            Integer n = in.nextInt();

            if (!accountCreated && n == 1) {
                System.out.print("Create a User ID: ");
                String id = in.next();
                String pass;

                while (true) {
                    System.out.print("Create a Password (8-15 characters, 1 capital, 1 special character): ");
                    pass = in.next();

                    if (isPasswordValid(pass)) {
                        break;
                    } else {
                        System.out.println("Please Enter a Valid Password that Meets the Criteria.");
                    }
                }

                loginids.add(id);
                d.put(id, pass);
                accountCreated = true;
                System.out.println("Your Account Has Been Created Successfully.");
            } else if (n == 1 || (accountCreated && n == 2)) {
                while (true) {
                    System.out.println("Enter Your Login ID:");
                    String loginid = in.next();

                    if (loginids.contains(loginid)) {
                        System.out.println("Enter Your Password:");
                        String password = in.next();

                        if (d.get(loginid).equals(password) && isPasswordValid(password)) {
                            System.out.println("");
                            System.out.println("   You are Now Inside Your User Dashboard");
                            System.out.println("-----------------------------------------");
                            System.out.println("         1. Reservation Form\n         2. Cancellation Form");
                            System.out.print("Select Option: ");

                            Integer c = in.nextInt();

                            if (c == 1) {
                                BasicDetails reservation = ReservationForm();
                                dd.put(loginid + "" + password, reservation);
                                System.out.println("Reservation Confirmed.");
                            } else if (c == 2) {
                                try {
                                    BasicDetails a = dd.get(loginid + "" + password);

                                    System.out.println("");
                                    System.out.println("   Your Journey Trip Details");
                                    System.out.println("-----------------------------------------");
                                    System.out.println("First Name: " + a.firstName);
                                    System.out.println("Last Name: " + a.lastName);
                                    System.out.println("Phone Number: " + a.phoneNumber);
                                    System.out.println("Email Address: " + a.email);
                                    System.out.println("Age: " + a.age);
                                    System.out.println("Gender: " + a.gender);
                                    System.out.println("Train Number: " + a.trainNumber);
                                    System.out.println("Class: " + a.classType);
                                    System.out.println("Date and Time Of Journey: " + a.dateOfJourney);
                                    System.out.println("From: " + a.from);
                                    System.out.println("To: " + a.to);
                                    System.out.println("--------------------------------------------");
                                    System.out.println("Do You Want to Remove the Data?\n1. Yes\n2. No");
                                    Integer option = in.nextInt();

                                    if (option == 1) {
                                        dd.remove(loginid + "" + password);
                                        System.out.println("Journey Successfully Cancelled.");
                                    } else {
                                        System.out.println("Cancellation Failed.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("You Do Not Have Any Active Trips.");
                                }
                            }
                            break;
                        } else {
                            System.out.println("Please Enter Correct Password.");
                            break;
                        }
                    } else {
                        System.out.println("Login ID is not available. Please Enter a Valid ID or Create an Account.");
                        break;
                    }
                }
            } else {
                System.out.println("Please Enter a Valid Option.");
            }
        }
    }

    public static boolean isPasswordValid(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[!@#$%^&*()])(.{8,15})$";
        return Pattern.matches(pattern, password);
    }

    public static boolean isEmailValid(String email) {
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(pattern, email);
    }
}

class BasicDetails {
    public String firstName, lastName, email, phoneNumber, gender, dateOfJourney, from, to;
    public Integer trainNumber, age, classType;
    public Object train;
}