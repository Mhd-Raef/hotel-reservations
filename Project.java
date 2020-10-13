package CS3230_Project;

import CS3230_Project.Booking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Project {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

//		to read from the file
        File myObj = new File("C:\\Users\\HP\\IdeaProjects\\training\\src\\CS3230_Project\\the hotel.txt");
        Scanner myReader = new Scanner(myObj);

        String mainInput;

        ArrayList <Booking> booking = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String[] inputs =(myReader.nextLine()).split("\t");
            Booking b = new Booking(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]);
            booking.add(b);
        }
        do {
            if (!booking.isEmpty()) {
                display(booking);
            }
            System.out.println("Welcome in our hotel! Please choose one of the options:\nEnter 1 to add booking\nEnter 2 to delete booking\nEnter 3 to edit booking\n"
                    + "Enter 4 to search about booking\nEnter 0 for exit");
            mainInput = input.next();

            String addingInput;
            String deleteInput;
            String editInput;
            String searchInput;

            switch(mainInput) {

                case "1": {
//			ADD BOOKINGS
                    do {
                        System.out.println("This booking is just for the next month.. ");
                        System.out.println("Enter first name: ");
                        String name = input.next();
                        System.out.println("Number of residents: must be 1-9 ");
                        String numRes = input.next();
                        System.out.println("Check-in date: must be 1-31: ");
                        String checkInDateInp = input.next();
                        System.out.println("Check-out date: must be 1-31: ");
                        String checkOutDateInp = input.next();
                        System.out.println("Room number: must be 1-500: ");
                        String roomNum = input.next();

                        if(booking.size() != 0) {
                            boolean flag = true;
                            if (Integer.parseInt(checkInDateInp)>Integer.parseInt(checkOutDateInp)) {
                                System.out.println("Sorry, check-out is before check-in date, or you have entered outrange day");
                                flag = false;
                                break;
                            }
                            else
                            {	for (int i = 0; i < booking.size(); i++) {
                                if (roomNum.equals(booking.get(i).getRoomNum())) {
                                    if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(checkInDateInp)
                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(checkInDateInp)) {
                                        System.out.println("Sorry, check-in date is overlapping with booking number "+(i+1));
                                        flag = false;
                                        break;
                                    } else if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(checkOutDateInp)
                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(checkOutDateInp)) {
                                        System.out.println("Sorry, check-out date is overlapping with booking number "+(i+1));
                                        flag = false;
                                        break;
                                    } else if ( (Integer.parseInt(booking.get(i).getCheckOutDate())) >= Integer.parseInt(checkInDateInp)
                                            && (Integer.parseInt(booking.get(i).getCheckInDate())) <= Integer.parseInt(checkOutDateInp)) {
                                        System.out.println("Sorry, check-out or check-in date is overlapping with booking number "+(i+1));
                                        flag = false;
                                        break;
                                    }
                                }
                            }
                            }
                            if (flag) {
                                Booking b = new Booking(name, numRes, checkInDateInp, checkOutDateInp, roomNum);
                                booking.add(b);
                                fileWriter(booking);
                            }
                        }
                        else
                        {
                            if (Integer.parseInt(checkInDateInp)<Integer.parseInt(checkOutDateInp)) {
                                Booking b = new Booking(name, numRes, checkInDateInp, checkOutDateInp, roomNum);
                                booking.add(b);
                                fileWriter(booking);
                            }
                            else {
                                System.out.println("Sorry, dates are not valid");
                            }
                        }

                        display(booking);
                        System.out.println("Do want to add another booking? anything = yes / 0 = no ");
                        addingInput = input.next();


                    } while (!addingInput.equals("0"));
                    break;

                }
                case "2": {
//			DELETE BOOKINGS
                    do {
                        if (!booking.isEmpty()) {
                            System.out.println("Enter 1 to delete by name\nEnter 2 to delete by room number\nEnter 3 to delete by date");
                            String typeInput = input.next();

                            switch(typeInput) {

                                case "1":
//						DELETE BY NAME
                                    System.out.println("Please enter the key name: ");
                                    String keyName = input.next();
                                    ArrayList <Integer> matchNames = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getName().equals(keyName)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchNames.add(i);
                                        }
                                    }
                                    if (!(matchNames.isEmpty())) {
                                        System.out.println("Choose the number of booking to delete it: ");
                                        int numDelete = input.nextInt();
                                        if ((matchNames.contains(numDelete-1))) {
                                            booking.remove(numDelete-1);
                                        }
                                        else
                                            System.out.println("Please choose one of matched bookings");
                                    }
                                    else
                                        System.out.println("Your key name is not found");

                                    fileWriter(booking);

                                    if (!booking.isEmpty()) {
                                        display(booking);
                                    }

                                    break;
                                case "2":
//					DELETE BY ROOM NUMBER
                                    System.out.println("Please enter the key room number: ");
                                    String keyRoomNum = input.next();
                                    ArrayList <Integer> matchRoom = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getRoomNum().equals(keyRoomNum)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchRoom.add(i);
                                        }
                                    }
                                    if (!(matchRoom.isEmpty())) {
                                        System.out.println("Choose the number of booking to delete it: ");
                                        int numDelete = input.nextInt();
                                        if (matchRoom.contains(numDelete-1)) {
                                            booking.remove(numDelete-1);
                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings");
                                    }
                                    else
                                        System.out.println("Your key room number is not found");

                                    fileWriter(booking);

                                    if (!booking.isEmpty()) {
                                        display(booking);
                                    }

                                    break;
                                case "3":
//					DELETE BY DATES
                                    System.out.println("Please enter the key check-in date: ");
                                    String keyCheckIn = input.next();
                                    System.out.println("Please enter the key check-out date: ");
                                    String keyCheckOut = input.next();

                                    ArrayList <Integer> matchCheckIn = new ArrayList<>();
                                    ArrayList <Integer> matchCheckOut = new ArrayList<>();

                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if ((booking.get(i).getCheckInDate().equals(keyCheckIn)) && (booking.get(i).getCheckOutDate().equals(keyCheckOut))) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchCheckIn.add(i);
                                            matchCheckOut.add(i);
                                        }
                                    }

                                    if ( (!(matchCheckIn.isEmpty())) || (!(matchCheckOut.isEmpty()))) {
                                        System.out.println("Choose the number of booking to delete it: ");
                                        int numDelete = input.nextInt();
                                        if (matchCheckIn.contains(numDelete-1) && matchCheckOut.contains(numDelete-1)) {
                                            booking.remove(numDelete-1);
                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings");
                                    }
                                    else
                                        System.out.println("Your key Check-in and check-out date(together) are not found");

                                    fileWriter(booking);

                                    if (!booking.isEmpty()) {
                                        display(booking);
                                    }

                                    break;
                                default:
                                    System.out.println("You have entered a wrong value");
                            }

                        }
                        else
                            System.out.println("Sorry, there are no bookings to delete..");

                        System.out.print("Do you want to delete another booking? anything = yes / 0 = no ");
                        deleteInput = input.next();

                    } while(!deleteInput.equals("0"));
                    break;
                }
                case "3": {
//				EDIT BOOKINGS
                    do {
                        if (!booking.isEmpty()) {
                            System.out.println("Enter 1 to edit name\nEnter 2 to edit number of residents\nEnter 3 to edit check-in date"
                                    + "\nEnter 4 to edit check-out date\nEnter 5 to edit room number");
                            String typeInput = input.next();

                            switch(typeInput) {
                                case "1":
//							EDIT NAME
                                    System.out.println("Please enter the key name: ");
                                    String keyName = input.next();
                                    ArrayList <Integer> matchNames = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getName().equals(keyName)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchNames.add(i);
                                        }
                                    }
                                    if (!(matchNames.isEmpty())) {
                                        System.out.println("Choose the number of booking to edit it: ");
                                        int numEdit = input.nextInt();
                                        if (matchNames.contains(numEdit-1)) {
                                            System.out.println("Enter the new name:");
                                            String newName = input.next();
                                            booking.get(numEdit-1).setName(newName);

                                        }
                                        else
                                            System.out.println("Please choose one of matched bookings");
                                    }
                                    else
                                        System.out.println("Your key name is not found");

                                    fileWriter(booking);
                                    display(booking);

                                    break;
                                case "2":
//					EDIT NUMBER OF RESIDENTS
                                    System.out.println("Please enter the key number of residents: ");
                                    String keyNumRes = input.next();
                                    ArrayList <Integer> matchNumRes = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getNumRes().equals(keyNumRes)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchNumRes.add(i);
                                        }
                                    }
                                    if (!(matchNumRes.isEmpty())) {
                                        System.out.println("Choose the number of booking to edit it: ");
                                        int numEdit = input.nextInt();
                                        if (matchNumRes.contains(numEdit-1)) {
                                            System.out.println("Enter the new number of residents:");
                                            String newRes = input.next();

                                            if (Integer.parseInt(newRes)>=1 && Integer.parseInt(newRes)<=9)
                                                booking.get(numEdit-1).setNumRes(newRes);
                                            else
                                                System.out.println("Please enter a appropriate number");

                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings.");
                                    }
                                    else
                                        System.out.println("Your key number of residents is not found.");

                                    fileWriter(booking);
                                    display(booking);

                                    break;
                                case "3":
//					EDIT CHECK-IN DATE
                                    System.out.println("Please enter the key check-in date: ");
                                    String keyCheckIn = input.next();
                                    ArrayList <Integer> matchCheckIn = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getCheckInDate().equals(keyCheckIn)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchCheckIn.add(i);
                                        }
                                    }
                                    if ( (!(matchCheckIn.isEmpty()))) {
                                        System.out.println("Choose the number of booking to edit it: ");
                                        int numEdit = input.nextInt();
                                        if (matchCheckIn.contains(numEdit-1)) {
                                            System.out.println("Enter the new check-in date: ");
                                            String newCheckIn = input.next();
                                            boolean flag = true;
                                            if (Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())<Integer.parseInt(newCheckIn)) {
                                                System.out.println("Sorry, check-out will be before check-in date, or you have entered outrange day");
                                                flag = false;
                                                break;
                                            }
                                            else
                                            {	for (int i = 0; i < booking.size(); i++) {
                                                if (booking.get(i).getRoomNum().equals(booking.get(numEdit-1).getRoomNum())) {
                                                    if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(newCheckIn)
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(newCheckIn)) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }

                                                    else if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }
                                                    else if ( (Integer.parseInt(booking.get(i).getCheckOutDate())) >= Integer.parseInt(newCheckIn)
                                                            && (Integer.parseInt(booking.get(i).getCheckInDate())) <= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }
                                                }
                                            }
                                                if (flag)
                                                    booking.get(numEdit-1).setCheckInDate(newCheckIn);
                                            }
                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings.");
                                    }
                                    else
                                        System.out.println("Your key Check-in date is not found.");

                                    fileWriter(booking);
                                    display(booking);

                                    break;
                                case "4":
//					EDIT CHECK-OUT DATE
                                    System.out.println("Please enter the key check-out date: ");
                                    String keyCheckOut = input.next();
                                    ArrayList <Integer> matchCheckOut = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getCheckOutDate().equals(keyCheckOut)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchCheckOut.add(i);
                                        }
                                    }
                                    if (!(matchCheckOut.isEmpty())) {
                                        System.out.println("Choose the number of booking to edit it: ");
                                        int numEdit = input.nextInt();
                                        if (matchCheckOut.contains(numEdit-1)) {
                                            System.out.println("Enter the new check-out date: ");
                                            String newCheckOut = input.next();
                                            boolean flag = true;
                                            if (Integer.parseInt(booking.get(numEdit-1).getCheckInDate())>Integer.parseInt(newCheckOut)) {
                                                System.out.println("Sorry, check-out will be before check-in date, or you have entered outrange day");
                                                flag = false;
                                                break;
                                            }
                                            else
                                            {	for (int i = 0; i < booking.size(); i++) {
                                                if (booking.get(i).getRoomNum().equals(booking.get(numEdit-1).getRoomNum())) {
                                                    if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }
                                                    else if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(newCheckOut)
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(newCheckOut)) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }
                                                    else if ( (Integer.parseInt(booking.get(i).getCheckOutDate())) >= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())
                                                            && (Integer.parseInt(booking.get(i).getCheckInDate())) <= Integer.parseInt(newCheckOut)) {
                                                        System.out.println("Sorry, you cannot edit booking in this period because the room has been reserved to booking number "+(i+1));
                                                        flag = false;
                                                        break;
                                                    }
                                                }
                                            }
                                                if (flag)
                                                    booking.get(numEdit-1).setCheckOutDate(newCheckOut);

                                            }
                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings.");
                                    }
                                    else
                                        System.out.println("Your key Check-in date is not found.");

                                    fileWriter(booking);
                                    display(booking);

                                    break;
                                case "5":
//						EDIT ROOM NUMBER
                                    System.out.println("Please enter the key room number: ");
                                    String keyRoomNum = input.next();
                                    ArrayList <Integer> matchRoomNum = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getRoomNum().equals(keyRoomNum)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchRoomNum.add(i);
                                        }
                                    }
                                    if (!(matchRoomNum.isEmpty())) {
                                        System.out.println("Choose the number of booking to edit it: ");
                                        int numEdit = input.nextInt();
                                        if (matchRoomNum.contains(numEdit-1)) {
                                            System.out.println("Enter the new room number:");
                                            String newRoom = input.next();

                                            boolean flag =true;
                                            if (!(Integer.parseInt(newRoom)>=1 && Integer.parseInt(newRoom)<=500)) {
                                                System.out.println("Please enter a appropriate number");
                                                flag=false;
                                                break;
                                            }
                                            else	{ 	for (int i = 0; i < booking.size(); i++) {
                                                if (booking.get(i).getRoomNum().equals(newRoom)) {
                                                    if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this room because it has been reserved in same period to booking number "+(i+1));
                                                        flag =false;
                                                        break;
                                                    }
                                                    else if (Integer.parseInt(booking.get(i).getCheckInDate()) <= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())
                                                            && Integer.parseInt(booking.get(i).getCheckOutDate()) >= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this room because it has been reserved in same period to booking number "+(i+1));
                                                        flag =false;
                                                        break;
                                                    }
                                                    else if ( (Integer.parseInt(booking.get(i).getCheckOutDate())) >= Integer.parseInt(booking.get(numEdit-1).getCheckInDate())
                                                            && (Integer.parseInt(booking.get(i).getCheckInDate())) <= Integer.parseInt(booking.get(numEdit-1).getCheckOutDate())) {
                                                        System.out.println("Sorry, you cannot edit booking in this room because it has been reserved in same period to booking number "+(i+1));
                                                        flag =false;
                                                        break;
                                                    }
                                                }
                                            }
                                                if(flag)
                                                    booking.get(numEdit-1).setRoomNum(newRoom);
                                            }
                                        }
                                        else
                                            System.out.println("Please choose one of  matched bookings");
                                    }
                                    else
                                        System.out.println("Your key room number is not found.");

                                    fileWriter(booking);
                                    display(booking);
                                    break;
                                default:
                                    System.out.println("You have entered a wrong value");

                            }
                        }
                        else
                            System.out.println("Sorry, there are no bookings to edit..");

                        System.out.print("Do you want to edit another booking? anything = yes / 0 = no ");
                        editInput = input.next();

                    } while (!editInput.equals("0"));

                    break;
                }
                case "4": {
//			SEARCH ABOUT BOOKINGS
                    do {
                        if (!booking.isEmpty()) {
                            System.out.println("Enter 1 to search by name\nEnter 2 to search by number of residents\nEnter 3 to search by check-in date"
                                    +"\nEnter 4 to search by check-out date\nEnter 5 to search by room number");
                            String typeInput = input.next();

                            switch(typeInput) {

                                case "1":
//							SEARCH ABOUT NAME
                                    System.out.println("Please enter the key name: ");
                                    String keyName = input.next();
                                    ArrayList <Integer> matchNames = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getName().equals(keyName)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchNames.add(i);
                                        }
                                    }
                                    if (matchNames.isEmpty())
                                        System.out.println("Your key name is not found");

                                    break;
                                case "2":
//				SEARCH ABOUT NUMBER OF RESIDENTS
                                    System.out.println("Please enter the key number of residents: ");
                                    String keyNumRes = input.next();
                                    ArrayList <Integer> matchRes = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getNumRes().equals(keyNumRes)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchRes.add(i);
                                        }

                                    }
                                    if (matchRes.isEmpty())
                                        System.out.println("Your key number of residents is not found");


                                    break;
                                case "3":
//					SEARCH ABOUT CHECK-IN DATE
                                    System.out.println("Please enter the key check-in date: ");
                                    String keyCheckIn = input.next();
                                    ArrayList <Integer> matchCheckIn = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if ((booking.get(i).getCheckInDate().equals(keyCheckIn)) ) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchCheckIn.add(i);
                                        }
                                    }
                                    if ( matchCheckIn.isEmpty())
                                        System.out.println("Your key Check-in date is not found");

                                    break;
                                case "4":
//					SEARCH ABOUT CHECK-OUT DATE
                                    System.out.println("Please enter the key check-out date: ");
                                    String keyCheckOut = input.next();
                                    ArrayList <Integer> matchCheckOut = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getCheckOutDate().equals(keyCheckOut) ) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchCheckOut.add(i);
                                        }
                                    }
                                    if (matchCheckOut.isEmpty())
                                        System.out.println("Your key Check-in date is not found");

                                    break;
                                case "5":
//				SEARCH ABOUT ROOM NUMBER
                                    System.out.println("Please enter the key room number: ");
                                    String keyRoomNum = input.next();
                                    ArrayList <Integer> matchRoom = new ArrayList<>();
                                    System.out.println("Result");
                                    for (int i = 0; i < booking.size(); i++) {
                                        if (booking.get(i).getRoomNum().equals(keyRoomNum)) {
                                            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ booking.get(i).getName()
                                                    + "\nnumber of Residents: "+ booking.get(i).getNumRes()
                                                    + "\nCheck-in day: "+ booking.get(i).getCheckInDate()
                                                    + "\nCheck-out day: "+ booking.get(i).getCheckOutDate()
                                                    + "\nRoom number: "+ booking.get(i).getRoomNum());
                                            System.out.println("**********");
                                            matchRoom.add(i);
                                        }
                                    }
                                    if (matchRoom.isEmpty())
                                        System.out.println("Your key room number is not found");

                                    break;
                                default:
                                    System.out.println("You have entered a wrong value");
                            }
                        }
                        else
                            System.out.println("Sorry, there are no bookings to search about..");

                        System.out.print("Do you want to search about another booking? anything = yes / 0 = no ");
                        searchInput = input.next();

                    } while (!searchInput.equals("0"));
                    break;
                }
                default: {
                        if(!mainInput.equals("0"))
                            System.out.println("You have entered a wrong value");
                }
            }
        } while (!mainInput.equals("0"));
        System.out.print("Thank you. We wish you better days... ");
    }
    public static void display(ArrayList <Booking> arr) {
        System.out.println("Current Bookings: ");
        for (int i = 0; i < arr.size(); i++)
            System.out.println("**********\nBooking no. "+(i+1)+"\nName: "+ arr.get(i).getName()
                    + "\nnumber of Residents: "+ arr.get(i).getNumRes()
                    + "\nCheck-in day: "+ arr.get(i).getCheckInDate()
                    + "\nCheck-out day: "+ arr.get(i).getCheckOutDate()
                    + "\nRoom number: "+ arr.get(i).getRoomNum());
        System.out.println("**********");

    }
    public static void fileWriter(ArrayList <Booking> arr) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\HP\\IdeaProjects\\training\\src\\CS3230_Project\\the hotel.txt");
        PrintWriter myWriter = new PrintWriter(fileWriter);
        for (Booking booking : arr)
            myWriter.println(booking.getName() + "\t" + booking.getNumRes()
                    + "\t" + booking.getCheckInDate() + "\t" + booking.getCheckOutDate()
                    + "\t" + booking.getRoomNum());
        myWriter.flush();
        myWriter.close();
    }
}

