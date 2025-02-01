package io.start;

import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.*;

public class PrintStreamMain {

    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;

        byte[] bytes = "Hello\n".getBytes(UTF_8);
        printStream.write(bytes);
        printStream.println("Print!");


    }
}

// write(byte[]) : OutputStream 부모 클래스가 제공하는 기능이다.
// println(String) : PrintStream 이 자체적으로 제공하는 추가 기능이다.
// -> 스트링을 바이트 배열로 바꿔주어 출력해주는 메서드, 그래서 스트링을 매개변수로 받을 수 있다.
