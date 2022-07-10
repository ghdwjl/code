
import java.io.File;

import java.io.FileWriter;


public class Write {

    public void TxtFileWriteTest() {

            try {

                Read read = new Read();
                double[][] arr ;
                arr = read.read();

                /* 写入Txt文件 */

                File file = new File("data.txt"); //存放数组数据的文件

                FileWriter out = new FileWriter(file); //文件写入流
                for (double[] doubles : arr) {
                    for (double aDouble : doubles) {
                        out.write(String.valueOf(aDouble) + '\t');
                    }
                    out.write('\n');
                }

                out.flush(); // 把缓存区内容压入文件

                out.close(); // 最后记得关闭文件



            } catch (Exception e) {

                e.printStackTrace();
            }

    }

}

