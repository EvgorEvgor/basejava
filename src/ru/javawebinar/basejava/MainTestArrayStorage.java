package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();
    private static final ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) {
//        doTest(ARRAY_STORAGE);
//        doTest(SORTED_ARRAY_STORAGE);
        doTest(LIST_STORAGE);
    }

    private static void doTest(Storage storage) {
        Resume r1 = new Resume("uuid1","Name1");
        Resume r2 = new Resume("uuid2","Name2");
        Resume r3 = new Resume("uuid3","Name3");
        Resume r4 = new Resume("uuid4","Name4");

        storage.save(r1);
        storage.save(r4);
        storage.save(r2);
        storage.save(r3);

        System.out.println("Get r1: " + storage.get(r1.getUuid()));
        System.out.println("Size: " + storage.size());

        try {
            System.out.println("Get dummy: " + storage.get("dummy"));
        } catch (NotExistStorageException e) {
            e.printStackTrace();
        }

        printAll(storage);
        storage.delete(r1.getUuid());
        printAll(storage);

        try {
            storage.update(r1);
        } catch (NotExistStorageException e) {
            e.printStackTrace();
        }
        printAll(storage);

        storage.clear();
        printAll(storage);

        System.out.println("Size: " + storage.size());

//        for (int i = 0; i < storage.getLimit(); i++) {
//            Resume resume = new Resume();
//            resume.setUuid("uuid" + i);
//            storage.save(resume);
//        }
//        try {
//            storage.save(new Resume("overflowUuid"));
//        } catch (StorageException e) {
//        }
    }

    private static void printAll(Storage storage) {
        System.out.println("\nGet All");
        for (Resume r : storage.getAllSorted()) {
            System.out.println(r);
        }
    }
}
