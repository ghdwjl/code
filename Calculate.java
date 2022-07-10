
public class Calculate {

    final double DTf = 189.0000;
    final double DTma = 88.0;
    final double GRmax = 156.0010;
    final double GRmin = 52.2120;
    final double GCUR = 3.7000;
    final double Rw = 0.0400;
    final double a = 0.6600;
    final double b = 1.0000;
    final double m = 1.6496;
    final double n = 2.0000;
    final double standard = 0.000001;

    //孔隙度

    public double[] POR(double[][] arr) {

        int DT = 2;
        double min = 0.005;
        double max = 0.5;
        double num;
        double[] copypor = new double[arr.length];

        for (int j = 0; j < arr.length; j++) {
            num = (arr[j][DT] - DTma) / (DTf - DTma);
            if ((num - 0) < standard) {
                copypor[j] = min;
            } else copypor[j] = Math.min(num, max);

        }
        return copypor;
    }

    //泥质含量
    public double[] VSH(double[][] arr) {


        int GR = 3;
        double min = 0;
        double max = 1;
        double num;
        double SH;
        double[] copypor = new double[arr.length];

        for (int j = 0; j < arr.length; j++) {
            SH = (arr[j][GR] - GRmin) / (GRmax - GRmin);
            num = (Math.pow(2, GCUR * SH) - 1) / (Math.pow(2, GCUR) - 1);
            if ((num - min) < standard) {
                copypor[j] = min;
            } else copypor[j] = Math.min(num, max);
        }
        return copypor;
    }

    //    含油饱和度
    public double[] So(double[][] arr, double[] array) {
        int LLD = 4;
        double min = 0;
        double max = 1;
        double num;
        double Sw;
        double[] copypor = new double[arr.length];

        for (int j = 0; j < arr.length; j++) {
            Sw = Math.pow((a * b * Rw) / (Math.pow(array[j], m) * arr[j][LLD]), (1 / n));
            num = 1 - Sw;
            if ((num - min) < standard) {
                copypor[j] = min;
            } else copypor[j] = Math.min(num, max);
        }
        return copypor;


    }

    //数据的合并
    public double[][] data(double[][] arr, double[] por, double[] vsh, double[] so) {
        double[][] array = new double[arr.length][13];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length + 3; j++) {
                if (j < arr[i].length) {
                    array[i][j] = arr[i][j];
                } else if (j < arr[i].length + 1) {
                    array[i][j] = por[i];
                } else if (j < arr[i].length + 2) {
                    array[i][j] = vsh[i];
                } else {
                    array[i][j] = so[i];
                }
            }
        }
        return array;
    }

    //求最小值
    public double min(double[] arr) {
        double min_num = arr[0];
        for (double i : arr) {
            if (i < min_num) {
                min_num = i;
            }
        }
        return min_num;

    }

    //求最大值
    public double max(double[] arr) {
        double max_num = arr[0];
        for (double i : arr) {
            if (i > max_num) {
                max_num = i;
            }
        }
        return max_num;

    }

    //求平均值
    public double avarage(double[] arr) {
        int index = 0;
        double sum = 0;
        for (double i : arr) {
            sum += i;
            index++;
        }
        return (sum / index);
    }


    //按含油饱和度排序
    public void so_sort(double[][] arr){
        double tmp [];
        int so=12;
        for (int i = 0;i<arr.length;i++){
            for (int j= 0;j<arr[i].length;j++){
                if(arr[j][so]<arr[j+1][12]){
                    tmp =arr[j+1];
                    arr[j+1] =arr[j];
                    arr[j]=tmp;
                }
            }
        }
        double[][] data_arr;
        String[] name = {"#DEPTH", "CAL", "DT", "GR", "LLD", "LLS", "MSFL", "NPHI", "RHOB", "SP", "POR", "VSH", "So"};
        for (String s : name) {
            System.out.printf("%-8s",s);
        }
        System.out.println();
        for (int i =0 ;i<arr.length;i++){
            for (int j =0 ;j<arr[i].length;j++){
                if (j<arr[i].length-3){
                    System.out.print(String.format("%-5.3f",arr[i][j])+"\t");
                }
                else {
                    System.out.print(String.format("%-5.3f",arr[i][j]*100)+'%'+'\t');
                }

            }
            System.out.println();
        }
    }



    //获取各等级个数

    public int[] get_num(double[] vsh, double[] por){

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        for (int i = 0; i < vsh.length; i++) {
            if (vsh[i] - 0.25 <= standard) {
                if (por[i] - 0.12 > standard) {
                    one++;
                } else if (por[i] >= 0.08 && por[i] <= 0.12) {
                    two++;
                } else if (por[i] >= 0.05 && por[i] < 0.08) {
                    three++;
                }
                else {
                    four++;
                }
            }
        }
        return new int[]{one,two,three,four};
    }

   //等级分类
    public void Stratification_1(double[][] arr,int num ) {
        int vsh_num = 11;
        int por_num = 10;
        int index=0;
        double[][] array = new double[num][13];
        for (int i = 0;i<array.length;i++){
            for (int j= 0 ;j<array[i].length;j++){
                array[i][j]=0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][vsh_num] <=0.25 &&  arr[i][por_num]>=0.12 ) {
                for (int b = 0; b < arr[i].length; b++) {
                    array[index][b] = arr[i][b];
                }
                index++;
            }
        }
        GUI.show(array);
    }
    public void Stratification_2(double[][] arr,int num ) {
        int vsh_num = 11;
        int por_num = 10;
        int index=0;
        double[][] array = new double[num][13];
        for (int i = 0;i<array.length;i++){
            for (int j= 0 ;j<array[i].length;j++){
                array[i][j]=0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][vsh_num] <=0.25 &&  arr[i][por_num]>=0.08 && arr[i][por_num] <= 0.12 ) {
                for (int b = 0; b < arr[i].length; b++) {
                    array[index][b] = arr[i][b];
                }
                index++;
            }
        }

        GUI.show(array);
    }
    public void Stratification_3(double[][] arr,int num ) {
        int vsh_num = 11;
        int por_num = 10;
        int index=0;
        double[][] array = new double[num][13];
        for (int i = 0;i<array.length;i++){
            for (int j= 0 ;j<array[i].length;j++){
                array[i][j]=0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][vsh_num] <=0.25 && arr[i][por_num] >=0.05 && arr[i][por_num]<=0.08) {
                for (int b = 0; b < arr[i].length; b++) {
                    array[index][b] = arr[i][b];
                }
                index++;
            }
        }

     GUI.show(array);
    }

}