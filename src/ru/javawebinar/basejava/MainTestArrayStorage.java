package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
//        doTest(ARRAY_STORAGE);
        doTest(SORTED_ARRAY_STORAGE);
    }

    private static void doTest(AbstractArrayStorage arrayStorage) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");

        arrayStorage.save(r1);
        arrayStorage.save(r4);
        arrayStorage.save(r2);
        arrayStorage.save(r3);

        System.out.println("Get r1: " + arrayStorage.get(r1.getUuid()));
        System.out.println("Size: " + arrayStorage.size());

        System.out.println("Get dummy: " + arrayStorage.get("dummy"));

        printAll(arrayStorage);
        arrayStorage.delete(r1.getUuid());
        printAll(arrayStorage);

        arrayStorage.update(r1);
        printAll(arrayStorage);

        arrayStorage.clear();
        printAll(arrayStorage);

        System.out.println("Size: " + arrayStorage.size());

        for (int i = 0; i <= arrayStorage.getLimit(); i++) {
            Resume resume = new Resume();
            resume.setUuid("uuid" + i);
            arrayStorage.save(resume);
        }
    }

    private static void printAll(AbstractArrayStorage arrayStorage) {
        System.out.println("\nGet All");
        for (Resume r : arrayStorage.getAll()) {
            System.out.println(r);
        }
    }
}
