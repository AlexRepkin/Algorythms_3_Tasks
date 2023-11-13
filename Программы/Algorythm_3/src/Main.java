import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

    static int needed = 1000000;
    static StringBuilder results = new StringBuilder();

    public static void main(String[] args) {
        Random rand = new Random();
        while (needed <= 6700000) {
            List<Integer> numbers = new ArrayList<>();
            results.append(needed).append(";");
            for(int i = 0; i < needed; i++) numbers.add(i);
            int max = (int) (needed * 0.6);
            int min = (int) (needed * 0.4);
            // Middle
            int randomNumber = rand.nextInt((max - min) + 1) + min;
            long startTime = System.nanoTime();
            for (Integer integer : numbers) {
                if (integer == randomNumber) {
                    break;
                }
            }
            long endTime = System.nanoTime();
            long spent_time = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            results.append(spent_time/ 1000000).append(",").append((spent_time/ 10000)%100).append(";");
            // Doesn't exist, -1
            startTime = System.nanoTime();
            for (Integer number : numbers) {
                if (number == needed) {
                    System.out.println("Oh no! Why is it here?!");
                }
            }
            endTime = System.nanoTime();
            spent_time = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            results.append(spent_time/ 1000000).append(",").append((spent_time/ 10000)%100).append("\n");
            needed += 10000;
        }
        File outputFile = new File("Results.txt");
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(results.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}