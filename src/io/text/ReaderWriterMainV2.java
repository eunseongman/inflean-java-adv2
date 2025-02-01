package io.text;

import java.io.*;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);

        osw.write(writeString);
        osw.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        StringBuilder content = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) {
            content.append((char) ch);
        }
        isr.close();

        System.out.println("content = " + content);
    }
}

// OutputStreamWriter 는 문자를 입력 받고, 받은 문자를 인코딩해서 byte[] 로 변환한다.
// OutputStreamWriter 는 변환한 byte[] 을 전달할 OutputStream 과 인코딩 문자 집합에 대한 정보가 필요하다. 따라서 두 정보를 생성자를 통해 전달해야 한다.

// 데이터를 읽을 때는 int ch = read() 를 제공하는데, 여기서는 문자 하나인 char 형으로 데이터를 받게 된다.
// 그런데 실제 반환 타입은 int 형이므로 char 형으로 캐스팅해서 사용하면 된다.
// 자바의 char 형은 파일의 끝인 -1 을 표현할 수 없으므로 대신 int 를 반환한다.
// InputStreamReader 는 이렇게 읽은 byte[] 을 문자인 char 로 변경해서 반환한다. 물론 byte 를 문자로 변경할 때도 문자 집합이 필요하다.

// 그런데 OutputStreamWriter 의 write() 는 byte 가 아니라 String 이나 char 를 사용한다. 어떻게 된 것일까?
// 자바는 byte를 다루는 I/O 클래스와 문자를 다루는 I/O 클래스를 둘로 나누어두었다.
// byte를 다루는 클래스는 OutputStream , InputStream 의 자식이다.
// 문자를 다루는 클래스는 Writer , Reader 의 자식이다.

// 여기서 꼭! 기억해야할 중요한 사실이 있다. 처음에 언급했듯이 모든 데이터는 byte 단위(숫자)로 저장된다.
// Writer 가 아무리 문자를 다룬다고 해도 문자를 바로 저장할 수는 없다.
// 이 클래스에 문자를 전달하면 결과적으로 내부에서는 지정된 문자 집합을 사용해서 문자를 byte로 인코딩해서 저장한다.

