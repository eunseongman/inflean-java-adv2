package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV1 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";

        // 문자 - byte UTF-8 인코딩
        byte[] writeBytes = writeString.getBytes(UTF_8);
        System.out.println("writeString = " + writeString);
        System.out.println("writeBytes = " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        // byte -> String UTF-8 디코딩
        String readString = new String(readBytes, UTF_8);
        System.out.println("readBytes = " + Arrays.toString(readBytes));
        System.out.println("readString = " + readString);
    }
}

// 여기서 핵심은 스트림은 byte 만 사용할 수 있으므로, String 과 같은 문자는 직접 전달할 수 는 없다는 점이다.
// 그래서 개발자가 번거롭게 다음과 같은 변환 과정을 직접 호출해주어야 한다.
// String + 문자 집합 byte[]
// byte[] + 문자 집합 String
// 이렇게 번거로운 변환 과정을 누군가 대신 처리해주면 더 편리하지 않을까?

