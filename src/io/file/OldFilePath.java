package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());

        // 절대 경로
        System.out.println("Absolute path = " + file.getAbsolutePath());

        // 정규 경로
        System.out.println("Canonical path = " + file.getCanonicalPath());

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }

    }
}

// 절대 경로(Absolute path): 절대 경로는 경로의 처음부터 내가 입력한 모든 경로를 다 표현한다.
// 정규 경로(Canonical path): 경로의 계산이 모두 끝난 경로이다. 정규 경로는 하나만 존재한다.
// - 예제에서 .. 은 바로 위의 상위 디렉토리를 뜻한다. 이런 경로의 계산을 모두 처리하면 하나의 경로만 남는다.
