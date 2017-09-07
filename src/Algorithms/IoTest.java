package Algorithms;

import java.io.*;

/**
 * Created by yangkun on 2017/9/7.
 */
public class IoTest {
    public static void main(String args[]) throws IOException {
        StreamTest.fileInputStreamTest("/Users/yangkun/Desktop/MyGitRepository/DataStructures/src/Algorithms/io-english.txt");
        StreamTest.fileOutputStreamTest();
    }

}

class StreamTest{
    public static void fileInputStreamTest(String path) throws IOException {
        System.out.print("使用FileInputStream按字节读取出指定文件：");
        InputStream fileInput=new FileInputStream(path);
        int c;
        //以字节的形式读取，每读取一个字节，返回该字节的ASCII码，是一个int类型
        while ((c=fileInput.read())!=-1){
            System.out.print((char) c+" ");
        }
        System.out.println();
    }

    public static void fileOutputStreamTest() throws IOException {
        System.out.print("使用FileOutputStream按字节写入到指定文件：");
        InputStream fileInput=new FileInputStream("/Users/yangkun/Desktop/MyGitRepository/DataStructures/src/Algorithms/io-english.txt");
        OutputStream fileOutput=new FileOutputStream("/Users/yangkun/Desktop/MyGitRepository/DataStructures/src/Algorithms/io-english2.txt");
        int c;
        while ((c=fileInput.read())!=-1){
            do {
                fileOutput.write((char)c);
            }while (c==(int)'\n');
        }
        fileOutput.flush();
        fileOutput.close();


    }
}

