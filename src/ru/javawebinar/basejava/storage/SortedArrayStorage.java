package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends ArrayStorage {
    @Override
    protected void deleteElement(int index) {
        int countShifted = size - index - 1;
        if (countShifted > 0) {
            System.arraycopy(storage, index + 1, storage, index, countShifted);
        }
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int realIndex = - index -1;
        System.out.println(realIndex);
        System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        storage[realIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
