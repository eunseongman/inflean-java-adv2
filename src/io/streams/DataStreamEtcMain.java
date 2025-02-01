package io.streams;

import java.io.*;

public class DataStreamEtcMain {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("회원A");
        dos.writeInt(20);
        dos.writeDouble(10.5);
        dos.writeBoolean(true);
        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);

        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    }
}

// 이 스트림을 사용할 때 주의할 점으로는 꼭! 저장한 순서대로 읽어야 한다는 것이다. 그렇지 않으면 잘못된 데이터가 조회될 수 있다.

// 저장한 data.dat 파일을 직접 열어보면 제대로 보이지 않는다.
// 왜냐하면 writeUTF() 의 경우 UTF-8 형식으로 저장하지만, 나머지의 경우 문자가 아니라 각 타입에 맞는 byte 단위로 저장하기 때문이다.

