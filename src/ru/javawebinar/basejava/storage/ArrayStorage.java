package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (NOT_EXIST_INDEX == index) {
            System.out.println("ERROR Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume resume) {
        if (NOT_EXIST_INDEX == getIndex(resume.getUuid())) {
            if (STORAGE_LIMIT <= size) {
                System.out.println("ERROR Storage overflow");
                return;
            }
            storage[size] = resume;
            size++;
        }
    }


    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (NOT_EXIST_INDEX == index) {
            System.out.println("ERROR Resume " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return NOT_EXIST_INDEX;
    }
}