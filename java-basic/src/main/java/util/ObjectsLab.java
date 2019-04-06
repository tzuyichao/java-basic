package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectsLab {
    public static void main(String[] args) throws Exception {
        System.out.println(Objects.hash(new ArrayList<>()));
        System.out.println(Objects.hashCode(new ArrayList<>()));
        System.out.println(checksum(new ArrayList<>()));
    }

    private static String checksum(Object obj) throws IOException, NoSuchAlgorithmException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] content = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.digest(content);
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }
}
