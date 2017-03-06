/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;
import java.io.FileNotFoundException;

/**
 * Serialize/deserialize objects to files.
 *
 * @author xavier
 */
public interface Serializor {

    public static void serialize(TeacheableStrategy st, String fileName)
            throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(st);
            System.out.printf("\nSaved : '%s'", fileName);
        }
    }

    public static TeacheableStrategy deserialize(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TeacheableStrategy st = (TeacheableStrategy) ois.readObject();
        System.out.printf("\nLoaded : '%s'", fileName);
        return st;
    }

}
