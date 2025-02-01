package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {

    public static void main(String[] args) throws IOException {
        // 아웃풋 스트림으로 파일 쓰기
        FileOutputStream fos = new FileOutputStream("temp/hello.dat", true);    // append 여부 설정 가능
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();        // 외부 자원을 사용하면 사용 후 반드시 닫아주어야 한다.

        // 인풋 스트림으로 파일 읽기
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        int data;
        while ((data = fis.read()) != -1) {
            System.out.println(data);
        }
        fis.close();
    }
}

// 참고 - read()가 int를 반환하는 이유
// 이 부분은 크게 중요하지 않은 내용이니 참고만 해두자

// 자바의 byte 는 -128에서 127까지 256종류의 값만 가질 수 있어, EOF를 위한 특별한 값을 할당하기 어렵다.
// int 는 0~255까지 모든 가능한 바이트 값을 표현하고, 여기에 추가로 -1을 반환하여 스트림의 끝(EOF)을 나타낼 수 있다.
