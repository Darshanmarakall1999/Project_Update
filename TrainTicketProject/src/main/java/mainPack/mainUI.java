package mainPack;

import Adminpack.Adminmoduleimpl;
import PessengerPack.Passenger;
import TicketPack.TicketOpImpl;
import TrainPack.Train;
import TrainPack.TrainOpImpl;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class mainUI {

    public static void main(String[] args) {
        TrainOpImpl TOI = new TrainOpImpl();
        TicketOpImpl gPrice = new TicketOpImpl();
        DateComparison dc = new DateComparison();
        Adminmoduleimpl admin=new Adminmoduleimpl();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Book Ticket ");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.println("Enter your choice : ");
            int choice=sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("enter the train no between 1001 to 1006 ");
                    int train_no = sc.nextInt();
                    if (train_no >= 1001 && train_no <= 1006) {
                        Date date = null;
                        System.out.println("enter date in yyyy/MM/dd format: ");
                        String d = sc.next();
                        try {
                            date = new SimpleDateFormat("yyyy/MM/dd").parse(d);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (dc.dateCom(date)) {
                            System.out.println("enter the number of passengers");
                            int pNo = sc.nextInt();
                            double price = 0;
                            Passenger ps = null;
                            while (pNo != 0) {
                                System.out.println("enter the passenger name");
                                String pName = sc.next();
                                System.out.println("enter passenger age:");
                                int age = sc.nextInt();
                                System.out.println("enter gender M/F");
                                char gen = sc.next().charAt(0);
                                ps = new Passenger(pName, age, gen);
                                price = gPrice.calcPassengerFare(train_no, age, gen);
                                gPrice.calculateTotalTicketPrice(ps, price);
                                pNo--;

                            }
                            gPrice.generateTicket(train_no, d, price, ps);
                            gPrice.writeTicket();
                        } else {
                            System.out.println("Date must me after your journey date. Your journey ends here");

                        }
                    } else {
                        System.out.println("Train with given no doesn't exist\n");
                    }
                    System.out.println(" \n\nIf you want to book another ticket press 1 or press 3 for exit\n");
                    break;
                }
                case 2: {
                    System.out.println("Welcome to the admin page!!!!!!");
                    while (true) {
                        System.out.println("1.Add New Train");
                        System.out.println("2.Delete a Train");
                        System.out.println("3.Get all Trains");
                        System.out.println("4.Get a Train");
                        System.out.println("5.Update Train");
                        System.out.println("6.Exit");
                        System.out.println("Enter your choice:");
                        int ch1 = sc.nextInt();
                        switch (ch1) {
                            case 1: {
                                while (true) {
                                    System.out.println("Enter the details of the Train:");
                                    int t_no = sc.nextInt();
                                    String t_name = sc.next();
                                    String t_source = sc.next();
                                    String t_dest = sc.next();
                                    sc.nextLine();
                                    double t_price = sc.nextDouble();
                                    Train train = new Train(t_no, t_name, t_source, t_dest, t_price);
                                    System.out.println(admin.AddATrain(train));
                                    System.out.println("If we want to continue adding the train press Y otherwise N:");
                                    char ch = sc.next().charAt(0);
                                    if (ch == 'Y')
                                        continue;
                                    else
                                        break;
                                }
                                break;
                            }
                            case 2: {
                                while (true) {
                                    System.out.println("Enter the Train number which you want to delete:");
                                    int num = sc.nextInt();
                                    System.out.println(admin.DeleteATrain(num));
                                    System.out.println("If we want to continue deleting the train press Y otherwise N:");
                                    char ch = sc.next().charAt(0);
                                    if (ch == 'Y')
                                        continue;
                                    else
                                        break;
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<Train> blist = admin.getAllTrains();
                                for (Train t : blist) {
                                    System.out.println(t);
                                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                                }
                                break;
                            }
                            case 4: {
                                System.out.println("Enter the train number to search:");
                                int number = sc.nextInt();
                                System.out.println("------------------------------------------------------------------------------------------------------------");
                                System.out.println(admin.GetATrain(number));
                                System.out.println("------------------------------------------------------------------------------------------------------------");
                                break;
                            }
                            case 5: {
                                while (true) {
                                    System.out.println("1.To update the Train name");
                                    System.out.println("2.To update the Source");
                                    System.out.println("3.To update the Destination");
                                    System.out.println("4.To update the Ticket price");
                                    System.out.println("5.Exit");
                                    System.out.println("Enter your choice:");
                                    int ch2 = sc.nextInt();
                                    switch (ch2) {
                                        case 1: {
                                            System.out.println("Enter the train number:");
                                            int number = sc.nextInt();
                                            System.out.println("Enter the New train name:");
                                            String str = sc.next();
                                            System.out.println(admin.UpdateAName(number, str));
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter the train number:");
                                            int number = sc.nextInt();
                                            System.out.println("Enter the New Source:");
                                            String str = sc.next();
                                            System.out.println(admin.UpdateASource(number, str));
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Enter the train number:");
                                            int number = sc.nextInt();
                                            System.out.println("Enter the New Destination:");
                                            String str = sc.next();
                                            System.out.println(admin.UpdateADest(number, str));
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Enter the train number:");
                                            int number = sc.nextInt();
                                            System.out.println("Enter the New Ticket price:");
                                            double str = sc.nextDouble();
                                            System.out.println(admin.UpdateATrainPrice(number, str));
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Exited.....");
                                        }

                                    }
                                    if (ch2 == 5) {
                                        break;
                                    }
                                }
                                break;
                            }
                            case 6: {
                                System.out.println("Bye.......");
                                System.out.println("-----------------------------------------------------");
                            }
                        }
                        if (ch1 == 6) {
                            break;
                        }

                    }
                    break;
                }

                    case 3: {
                        sc.close();
                        System.out.println("Thank you for visiting......");
                        System.out.println("-----------------------------------------------------");
                        System.exit(0);

                }
            }
            }

        }
    }