package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {

    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가다나";
        System.out.println("=== Write String ===");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();


        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        System.out.println("=== Read String ===");
        System.out.println(content);
    }
}

// BufferedOutputStream , BufferedInputStream 과 같이 Reader , Writer 에도 버퍼 보조 기능을 제공하는 BufferedReader , BufferedWriter 클래스가 있다.
// br.readLine()은 한 줄 단위로 문자를 읽고 String 을 반환한다.
// 반환 타입이 String 이기 때문에 EOF를 -1로 표현할 수 없다. 대신에 null 을 반환한다.


