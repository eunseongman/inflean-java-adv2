package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

    public static void main(String[] args) throws IOException {
        // 아웃풋 스트림으로 파일 쓰기
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();        // 외부 자원을 사용하면 사용 후 반드시 닫아주어야 한다.

        // 인풋 스트림으로 파일 읽기
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        fis.close();
    }
}

// hello.dat 에 분명 byte로 65, 66, 67을 저장했다. 그런데 왜 개발툴이나 텍스트 편집에서 열어보면 ABC라고 보이는 것일까?

// 우리가 사용하는 개발툴이나 텍스트 편집기는 UTF-8 또는 MS949 문자 집합을 사용해서 byte 단위의 데이터를 문자로 디코딩해서 보여준다.
// 따라서 65, 66, 67 byte를 ASCII 문자인 A, B, C로 인식해서 출력한 것이다.
