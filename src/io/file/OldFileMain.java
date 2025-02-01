package io.file;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        // exists(): 파일이나 디렉토리의 존재 여부를 확인
        System.out.println("File exists" + file.exists());

        // createNewFile(): 새 파일을 생성
        Boolean created = file.createNewFile();
        System.out.println("File created = " + created);

        // mkdir(): 새 디렉토리를 생성
        boolean dirCreated = directory.mkdir();
        System.out.println("Directory Created = " + dirCreated);

        // delete(): 파일이나 디렉토리를 삭제
        //boolean delete = file.delete();
        //System.out.println("File delete = " + delete);

        // 각종 파일 정보 확인
        System.out.println("Is file : " + file.isFile());
        System.out.println("Is directory : " + directory.isDirectory());
        System.out.println("File name : " + file.getName());
        System.out.println("File size : " + file.length() + "bytes");

        // renameTo(File dest): 파일의 이름을 변경하거나 이동
        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);
        System.out.println("File renamed = " + renamed);

        // lastModified(): 마지막으로 수정된 시간을 반환
        long lastModified = newFile.lastModified();
        System.out.println("lastModified = " + new Date(lastModified));
    }
}
