import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Read {
    BufferedReader br;
    List<String> list = new ArrayList<String>();
    String line;
    int readLine_start = 53;
    int readLine_end = 80;
    int index = 0;

    public double[][] read() {
        double[][] array;
        {
            try {
                br = new BufferedReader(new FileReader("well_logging_data.txt"));
                String text = "";
                array = null;
                while (text != null) {
                    index++;
                    line = br.readLine();
                    if (index > readLine_start && index < readLine_end) {
                        list.add(line);
                    }
                    if (index > readLine_end) {
                        text = null;
                    }
                }

                int lineNum = list.size();

                String s = list.get(0);
                int columnNum = s.split(" {2}").length;

                array = new double[list.size()][columnNum];

                int count = 0;

                for (String str : list) {
                    String[] strs = str.split("  ");
                    for (int i = 0; i < columnNum; i++) {
                        array[count][i] = Double.parseDouble(strs[i]);
                    }
                    count++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {

                try {
                    if (br != null)
                        br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
        return array;
    }

    public void test(double[][] array, int num) {

        double[][] arr = new double[array.length][];
        arr = array;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                arr[i][j] = array[i][j] * num;
            }
        }
            for (int k = 0; k < array.length; k++) {
                for (int h = 0; h < array[k].length; h++) {
                    System.out.printf("%-12.6f",arr[k][h]);
                }
                System.out.println();
            }
        }
    }




