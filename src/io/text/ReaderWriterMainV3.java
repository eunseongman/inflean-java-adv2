package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        int ch;
        while ((ch = fr.read()) != -1) {
            content.append((char) ch);
        }
        fr.close();

        System.out.println("string = " + content.toString());
    }
}

// FileOutputStream fos = new FileOutputStream(FILE_NAME);
// OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);
// -> FileWriter fw = new FileWriter(FILE_NAME, UTF_8);

// 한 줄로 축약이 가능하다.
// 이는 FileWriter 가 사실 내부에서 스스로 FileOutputStream 을 하나 생성해서 사용하기 때문이다.
// 따라서 FileWriter 는 OutputStreamWriter 를 조금 편리하게 사용하도록 도와줄 뿐이다. 물론 FileReader도 마찬가지다.

// Writer , Reader 클래스를 사용하면 바이트 변환 없이 문자를 직접 다룰 수 있어서 편리하다.
// 하지만 실제로는 내부에서 byte로 변환해서 저장한다는 점을 꼭 기억하자.
// 그리고 반드시 기억하자, 문자를 byte로 변경하려면 항상 문자 집합(인코딩 셋)이 필요하다!


