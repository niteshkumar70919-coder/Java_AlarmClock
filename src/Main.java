import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] Args) {
        // JAVA ALARM CLOCK
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "Wildfire - Jessie Villa.wav";

        while (alarmTime == null) {
            try {
                System.out.print("Enter an alarm Time (HH:MM:SS): ");
                String enteredTime = scanner.nextLine();

                alarmTime = LocalTime.parse(enteredTime, formatter);
                System.out.println("The Alarm is set for : " + alarmTime);
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid time in (HH:MM:SS)");
            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, scanner);
        Thread alarmThread = new Thread(alarmClock);
        alarmThread.start();
    }
}

