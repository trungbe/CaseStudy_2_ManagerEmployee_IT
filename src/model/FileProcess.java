package model;

import java.io.*;
import java.util.List;

public class FileProcess {
    public static final String TEXT_FILE_DEV = "src\\storage\\developer.dat";
    public static final String TEXT_FILE_TEST = "src\\storage\\tester.dat";


    public static Object readObjectFromFile(int object) {
        Object objects = new Object();
        try {
            FileInputStream fileInputStream = new FileInputStream(object == 1 ? TEXT_FILE_DEV : TEXT_FILE_TEST);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objects = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static void writeObjectToFile(List<Object> objectList, int object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(object == 1 ? TEXT_FILE_DEV : TEXT_FILE_TEST);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectList);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
