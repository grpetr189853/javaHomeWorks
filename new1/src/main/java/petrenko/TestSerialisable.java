/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studionovi.javacore.io.serialize;

import petrenko.House;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitaliy
 */
public class TestSerialisable {

    public void serialize(House cfs) throws IOException {
        FileOutputStream fos = new FileOutputStream("serialize//object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cfs);
        oos.flush();
        oos.close();
    }

    public House deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("serialize//object.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (House) oin.readObject();
    }

    public static void main(String[] args) {
        TestSerialisable serialisable = new TestSerialisable();
        House cfs = new House();

        try {
            serialisable.serialize(cfs);
            System.out.println(serialisable.deserialize());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestSerialisable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
