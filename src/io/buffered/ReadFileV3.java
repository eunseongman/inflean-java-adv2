package io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV3 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);


        int fileSize = 0;
        int data;
        while ((data = bis.read()) != -1) {
            fileSize++;
        }
        bis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}

// read() 는 1byte만 조회한다.
// BufferedInputStream 은 먼저 버퍼를 확인한다. 버퍼에 데이터가 없으므로 데이터를 불러온다.
// BufferedInputStream 은 버퍼의 크기만큼 데이터를 미리 읽어서 버퍼에 보관해둔다. 따라서 read() 를 통해 1byte씩 데이터를 조회해도, 성능이 최적화 된다.

// 버퍼를 사용하는 것은 같기 때문에 결과적으로 예제2와 예제3은 비슷한 성능이 나와야한다. 그런데 왜 예제2가 더 빠른 것일까?
// 이 이유는 바로 동기화 때문이다.

// BufferedOutputStream 을 포함한 BufferedXxx 클래스는 모두 동기화 처리가 되어 있다.
// BufferedXxx 클래스는 자바 초창기에 만들이진 클래스인데, 처음부터 멀티 스레드를 고려해서 만든 클래스이다.
// 따라서 멀티 스레드에 안전하지만 락을 걸고 푸는 동기화 코드로 인해 성능이 약간 저하될 수 있다.

// 매우 큰 데이터를 다루어야 하고, 성능 최적화가 중요하다면 예제 2와 같이 직접 버퍼를 다루는 방법을 고려하자.
// 아쉽게도 동기화 락이 없는 BufferedXxx 클래스는 없다.

