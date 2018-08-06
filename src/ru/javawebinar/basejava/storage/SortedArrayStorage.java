package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends ArrayStorage {
    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR Resume " + uuid + " not exist");
        } else {
            int lastIndex = size - 1;
            if (index != lastIndex)
                System.arraycopy(storage, index + 1, storage, index, lastIndex - index);
            storage[lastIndex] = null;
            size--;
        }
    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR Resume " + r.getUuid() + " already exist");
        } else if (STORAGE_LIMIT <= size) {
            System.out.println("ERROR Storage overflow");
        } else {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (r.compareTo(storage[i]) < 0) {
                    index = i;
                }
            }
            if (index < 0) {
                storage[size] = r;
            } else {
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
            }
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
