import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;
    AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner){
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }
    @Override
    public void run(){
        while(now().isBefore(alarmTime)){
            try {LocalTime now = now();
                System.out.printf("\r%02d:%02d:%02d",
                        now().getHour(),
                        now().getMinute(),
                        now().getSecond());

                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("The thread was interrupted");
            }
        }
        System.out.println("\nAlarm Noises");
        playSound(filePath);
    }

    private void playSound(String filePath) {
        File audioFile = new File(filePath);
        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.print("Press Enter to stop the Alarm: ");
            scanner.nextLine();
            clip.stop();
            scanner.close();
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio file does not supported");
        }
        catch(LineUnavailableException e){
            System.out.println("Audio file is not accessible");

        }
        catch(IOException e){
            System.out.println("Can't read the audio");
        }
    }
}

