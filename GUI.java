public class GUI {


    public static void show(double[][] arr){
        String[] name = {"#DEPTH", "CAL", "DT", "GR", "LLD", "LLS", "MSFL", "NPHI", "RHOB", "SP", "POR", "VSH", "So"};
        for (String s : name) {
            System.out.printf("%-8s",s);
        }
        System.out.println();
        for (double[] doubles : arr) {
            for (int j = 0; j < doubles.length; j++) {
                if (j < doubles.length - 3) {
                    System.out.print(String.format("%-5.3f", doubles[j]) + "\t");
                } else {
                    System.out.print(String.format("%-5.3f", doubles[j] * 100) + '%' + '\t');
                }
            }
            System.out.println();
        }

    }


}
