import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {

    final static String COMMA_DELIMITER = ",";
    final static String NEWLINE = "\n";

//    public static void main(String[] args) {
//
//        List<List<String>> records = loadAsStrings("src/scores.csv");
//        Leaderboard lb = new Leaderboard();
//        lb.load();
//        System.out.println(lb.stringify());
////        Score sc = new Score("Liam",3,500);lb.add(sc);
////        sc = new Score("Liam",3,600);lb.add(sc);
////        sc = new Score("Liam",3,700);lb.add(sc);
////        sc = new Score("Liam",3,800);lb.add(sc);
////        sc = new Score("Liam",3,200);lb.add(sc);
////        sc = new Score("Liam",3,100);lb.add(sc);
//
//        System.out.println(lb.stringify());
//        lb.save();
//
//        List<Score> topScores = lb.topFive();
//
//
//        showStrings(records);
//        //records = writeInts("src/scores.csv");
//        //showInts(records);
//
//
//    }

    public static void showInts(List<List<Integer>> records) {
        for(List<Integer> ints : records) {

            for (int item : ints) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.println("Done");
    }
    public static void showStrings(List<List<String>> records) {
        for(List<String> strings : records) {

            for (String item : strings) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.println("Done");
    }

    public static List<List<Integer>> readInts(String filename) {
        List<List<String>> records = new ArrayList<>();
        List<List<Integer>> intRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }

            for(int i = 0; i< records.size(); i++) {
                List<String> row = records.get(i);
                List<Integer> intRow = new ArrayList<>();
                for(String item : row) {
                    intRow.add(Integer.parseInt(item));
                }
                intRecords.add(intRow);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return intRecords;
    }

    public static List<List<String>> loadAsStrings(String filename) {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;

    }

    public static void save(List<String> tables) {
    }

    public static void save(List<String> list, String filename) {

        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(writer)) {

            for(String line : list) {
                bw.write(line + NEWLINE);
            }


        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }


    }

    public static int readInt(String filename) {

        List<String> records = new ArrayList<>();
        int value=1;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
            value = Integer.parseInt( records.get(0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return value;
    }

}
