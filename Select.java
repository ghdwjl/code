import java.util.Arrays;

public class Select {
     final String[] name ={"#DEPTH","CAL","DT","GR","LLD","LLS","MSFL","NPHI","RHOB","SP","POR","VSH","So"};
    public void show(double[][] arr,int num){

        for (String s : name) {
            System.out.printf("%-8s",s);
        }
        System.out.println();
        for (int i =0 ;i<arr.length;i++){
            for (int j = 0 ;j<arr[i].length;j++){
                if (i == num-1){
                    if(j<arr[i].length-3){
                        System.out.printf("%-8.3f", arr[i][j]);
                    }
                  else {
                        System.out.printf("%-8.3f",arr[i][j]*100);
                    }
                }
            }
        }
    }

   public double[][] Good_So(double[][] arr){
       int vsh = 11;
       int por = 10;
       int so = 12;
       int index=0;
       for (double[] doubles : arr) {
           if (doubles[vsh] <= 0.25 && doubles[por] >= 0.06 && doubles[so] >= 0.6) {
               index++;
           }
       }

       double[][] array = new double[index][13];
       for (double[] value : array) {
           Arrays.fill(value, 0);
       }

       int num =0;
       for (double[] doubles : arr) {
           if (doubles[vsh] <= 0.25 && doubles[por] >= 0.06 && doubles[so] >= 0.6) {
               System.arraycopy(doubles, 0, array[num], 0, doubles.length);
               num++;
           }
       }
    return array;
   }

}



