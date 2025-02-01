package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes));

        // 한 번에 쓰기, 한 번에 읽기
    }
}

// 부분으로 나누어 읽기 vs 전체 읽기

// read(byte[], offset, lentgh)
// 스트림의 내용을 부분적으로 읽거나, 읽은 내용을 처리하면서 스트림을 계속해서 읽어야 할 경우에 적합하다.
// 메모리 사용량을 제어할 수 있다.

// readAllBytes()
// 한 번의 호출로 모든 데이터를 읽을 수 있어 편리하다.
// 메모리 사용량을 제어할 수 없다.
// 큰 파일의 경우 OutOfMemoryError가 발생할 수 있다.

