
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //数据读入
        Read read = new Read();
        double[][] arr ;
        arr = read.read();

        //数据的计算
        Calculate calculate = new Calculate();
        double[] arr_por;
        double[] arr_vsh;
        double[] arr_so;
        arr_por = calculate.POR(arr);
        arr_vsh = calculate.VSH(arr);
        arr_so = calculate.So(arr, arr_por);

        //数据拼接
        double[][] data_arr;
        data_arr = calculate.data(arr, arr_por, arr_vsh, arr_so);

        //好油层
        Select select1 = new Select();

        // 界面设计
        Scanner scanner = new Scanner(System.in);
        boolean isture = true;
    while (isture){

        System.out.println("###### —— 欢迎登录测井资料处理分析系统 —— ######");
        System.out.println("        1.处理测井数据");
        System.out.println("            1.提取测井数据");
        System.out.println("            2.测井数据处理并写入txt");
        System.out.println("        2.处理结果分析 ");
        System.out.println("            1.各深度点处理成果查询 ");
        System.out.println("            2.数值统计查询 ");
        System.out.println("            3.含油饱和度顺序查询 ");
        System.out.println("            4.不同等级深度点查询");
        System.out.println("            5.好油层深度查询 ");
        System.out.println("        3.退出");
        System.out.println();
        System.out.print("请选择选项:" );
        int NUM = scanner.nextInt();

        switch (NUM){
            case 1:
                System.out.println("        1.处理测井数据");
                System.out.println("            1.提取测井数据");
                System.out.println("            2.测井数据处理并写入txt");
                System.out.print("请选择选项:" );
                int NUM_1 = scanner.nextInt();
                switch (NUM_1) {
                    case 1 -> {
                        String[] name = {"#DEPTH", "CAL", "DT", "GR", "LLD", "LLS", "MSFL", "NPHI", "RHOB", "SP", "POR", "VSH", "So"};
                        for (String s : name) {
                            System.out.printf("%-5s",s);
                        }
                        System.out.println();
                        for (double[] doubles : arr) {
                            for (double aDouble : doubles) {
                                System.out.printf("%-5.3f", aDouble);
                            }
                            System.out.println();
                        }
                    }
                    case 2 -> {
                        Write write = new Write();
                        write.TxtFileWriteTest();
                    }
                    default -> {
                        System.out.println();
                        System.out.println("输入错误。");
                        System.out.println();
                    }
                }
                break;

            case 2:
                System.out.println("        2.处理结果分析 ");
                System.out.println("            1.各深度点处理成果查询 ");
                System.out.println("            2.数值统计查询 ");
                System.out.println("            3.含油饱和度顺序查询 ");
                System.out.println("            4.不同等级深度点查询");
                System.out.println("            5.好油层深度查询 ");
                System.out.print("请选择选项:");
                int NUM_2 = scanner.nextInt();
                switch (NUM_2){
                    case 1:
                        System.out.print("请选择1~17：");
                        int num_1 = scanner.nextInt();
                        Select select = new Select();
                        select.show(data_arr,num_1);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("最大值：" + calculate.max(arr_por));

                        System.out.println("最小值: " + calculate.min(arr_por));

                        System.out.println("平均值： " + calculate.avarage(arr_por));

                    case 3:
                        calculate.so_sort(data_arr);
                        break;
                    case 4:
                        int[] data_nums;
                        data_nums = calculate.get_num(arr_vsh, arr_por);
                        System.out.print("要查询的等级：");
                        int num_2 = scanner.nextInt();
                        if (num_2 == 1) {
                            calculate.Stratification_1(data_arr, data_nums[num_2 - 1]);
                        } else if (num_2 == 2) {
                            calculate.Stratification_2(data_arr, data_nums[num_2 - 1]);
                        } else if (num_2 == 3) {
                            calculate.Stratification_3(data_arr, data_nums[num_2 - 1]);
                        } else {
                            System.out.println("四等级为空。");
                        }
                        break;
                    case  5:
                        double [] [] GOODSO;
                        GOODSO=select1.Good_So(data_arr);
                        GUI.show(GOODSO);
                        break;


                    default:
                        System.out.println();
                        System.out.println("输入错误。");
                        System.out.println();
                        break;

                }
                break;


            case 3:
                isture = false;
                System.out.println("已安全退出。");
            default:
                System.out.println();
                System.out.println("输入错误。");
                System.out.println();
                break;
            }
        }
    }
}