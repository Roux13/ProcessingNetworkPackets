import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    LinkedList<Integer> buffer = new LinkedList<>();

    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] firstLine = reader.readLine().split(" ");
        int bufferSize = Integer.parseInt(firstLine[0]);
        int n = Integer.parseInt(firstLine[1]);
        if (n == 0) {
            return;
        }

        String[][] textPackages = new String[n][2];
        for (int i = 0; i < n; i++) {
            textPackages[i] = reader.readLine().split(" ");

        }

        ArrayList<Integer[]> packages = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer[] currentPackage = new Integer[2];
            for (int j = 0; j < 2; j++) {
                currentPackage[j] = Integer.parseInt(textPackages[i][j]);
            }
            packages.add(currentPackage);
        }

        ArrayList<Integer> result = new ArrayList<>();
        int currentTime = 0;
        int currentProcessingTime = 0;
        ArrayList<Integer> indexesForRemoving = new ArrayList<>();

        while (!packages.isEmpty()) {
            for (int i = 0; i < packages.size(); i++) {
                if (packages.get(i)[0] == currentTime + 1) {
                    break;
                }
                if (packages.get(i)[0] == currentTime) {
                    indexesForRemoving.add(i);
                    if (buffer.size() == bufferSize) {
                        continue;
                    }
                    else {
                        buffer.add(packages.get(i)[1]);
                    }
                }
            }
            for (int k = 0; k < indexesForRemoving.size(); k++) {
                packages.remove(0);
            }
            if (currentProcessingTime == 0) {
                if (!buffer.isEmpty()) {
                    currentProcessingTime = buffer.poll();
                    result.add(currentTime);
                }
            }
            currentTime++;
            currentProcessingTime--;

        }


        result.forEach(r -> System.out.println(r));
    }

    public static void main(String[] args) throws IOException {

        new Main().run();

    }

}
