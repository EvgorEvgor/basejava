package ru.javawebinar.basejava.storage;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AllStorageTests {
    public static void main(String[] args) {
        Class[] storageTests = new Class[] {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapStorageTest.class
        };

        for (Class testClass : storageTests) {
            Result result = JUnitCore.runClasses(testClass);

            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }

            System.out.println(testClass+ ": " + result.wasSuccessful());
        }
    }
}
