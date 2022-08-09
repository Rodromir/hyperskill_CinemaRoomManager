package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // setting-up the theater
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        char[][] seatPlan = buildSeatPlan(rows, seatsInRow);

        // theater statistics
        int totalSeats = rows * seatsInRow;
        boolean smallRoom = totalSeats <= 60;
        int purchasedTickets = 0;
        int totalIncome = getTotalIncome(smallRoom, rows, seatsInRow, totalSeats);
        int currentIncome = 0;

        //running program
        boolean programRuns = true;
        while (programRuns) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    showSeatPlan(rows, seatsInRow, seatPlan);
                    break;
                case 2:
                    boolean seatIsReserved = false;
                    int rowNumber = 0;
                    int seatNumber = 0;
                    while (!seatIsReserved) {
                        System.out.println("Enter a row number:");
                        rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                        seatIsReserved = reserveSeat(seatPlan, rowNumber, seatNumber);
                    }
                    int ticketPrice = getTicketPrice(rowNumber, rows, smallRoom);
                    System.out.println("Ticket price: $" + ticketPrice);
                    purchasedTickets++;
                    currentIncome += ticketPrice;
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: " + purchasedTickets);
                    System.out.println("Percentage: " + String.format("%.2f", (double) purchasedTickets / (double) totalSeats * 100) + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;
                case 0:
                    programRuns = false;
                    break;
            }
        }

    }
    public static char[][] buildSeatPlan(int rowCount, int seatCount) {
        char[][] seatPlan = new char[rowCount][seatCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < seatCount; j++) {
                    seatPlan[i][j] = 'S';
            }
        }
        return seatPlan;
    }
    public static void showSeatPlan(int rowsNumber, int seatsNumber, char[][] seatPlan) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsNumber; i++) {
            System.out.print(i + " ");
            if (i == seatsNumber) {
                System.out.println();
            }
        }
        for (int i = 0; i < rowsNumber; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsNumber; j++) {
                System.out.print(seatPlan[i][j] + " ");
                if (j == seatsNumber - 1) {
                    System.out.println();
                }
            }
        }
    }
    public static int getTicketPrice(int rowNumber, int rows, boolean smallRoom){
        if (smallRoom) {
            return 10;
        } else {
            return rowNumber <= (rows / 2) ? 10 : 8;
        }
    }

    public static boolean reserveSeat(char[][] seatPlan, int rowNumber, int seatNumber) {
            if (rowNumber > (seatPlan.length) || seatNumber > (seatPlan[0].length)) {
                System.out.println("Wrong input!");
                return false;
            } else if ((seatPlan[rowNumber - 1][seatNumber - 1] != 'S')) {
                System.out.println("That ticket has already been purchased!");
                return false;
            } else {
                seatPlan[rowNumber - 1][seatNumber - 1] = 'B';
                return true;
            }
    }

    public static int getTotalIncome(boolean smallRoom, int rows, int seatsInRow, int totalSeats) {
        if (smallRoom) {
            return totalSeats * 10;
        } else if (rows % 2 == 0) {
            return (rows / 2 * seatsInRow * 8) + (rows / 2 * seatsInRow * 10);
        } else {
            return ((rows - 4) * seatsInRow * 8) + ((rows - (rows - 4)) * seatsInRow * 10);
        }
    }


}
