package serialize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize implements Serializable {
    private static final long serialVersionUID = 1920L;
    public int num = 1390;

    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("serialize.dat"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Serialize serialize = new Serialize();
            objectOutputStream.writeObject(serialize);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
