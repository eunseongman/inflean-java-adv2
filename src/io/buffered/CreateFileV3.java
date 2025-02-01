package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File create: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}

// BufferedOutputStream 은 버퍼 기능을 내부에서 대신 처리해준다.
// 따라서 단순한 코드를 유지하면서 버퍼를 사용하는 이점도 함께 누릴 수 있다.
// 여기서는 FileOutputStream 객체를 생성자에 전달한다. 추가로 사용할 버퍼의 크기도 함께 전달할 수 있다.
// 버퍼를 위한 byte[] 을 직접 다루지 않고, 마치 예제1과 같이 단순하게 코드를 작성할 수 있다.

// BufferedOutputStream 은 내부에 byte[] buf 라는 버퍼를 가지고 있다.
// 버퍼가 가득차면 FileOutputStream 에 있는 write(byte[]) 메서드를 호출한다.
// 버퍼가 다 차지 않아도 버퍼에 남아있는 데이터를 전달하려면 flush() 라는 메서드를 호출하면 된다.

// 만약 버퍼에 데이터가 남아있는 상태로 close() 를 호출하면 먼저 내부에서 flush() 를 호출한다. 따라서 버퍼에 남아있는 데이터를 모두 전달하고 비운다.
// 버퍼가 비워지고 나면 close() 로 BufferedOutputStream 의 자원을 정리한다. 그리고 나서 다음 연결된 스트림의 close() 를 호출한다.
// 여기서 핵심은 close() 를 호출하면 close() 가 연쇄적으로 호출된다는 점이다.

// 주의! - 반드시 마지막에 연결한 스트림을 닫아야 한다.
// 만약 BufferedOutputStream 을 닫지 않고, FileOutputStream 만 직접 닫으면 flush()가 호출되지 않으므로 문제가 발생할 수 있다.
// 따라서 가장 마지막에 연결했던 BufferedOutputStream 에서 close() 를 호출하여 연쇄적으로 close()가 호출되게 해야한다.

// 기본 스트림, 보조 스트림
// FileOutputStream 과 같이 단독으로 사용할 수 있는 스트림을 기본 스트림이라 한다.
// BufferedOutputStream 과 같이 단독으로 사용할 수 없고, 보조 기능을 제공하는 스트림을 보조 스트림이라 한다.

