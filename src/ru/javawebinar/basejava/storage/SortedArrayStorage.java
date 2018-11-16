package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void deleteElement(int index) {
        int shiftCount = size - index - 1;
        if (shiftCount > 0) {
            System.arraycopy(storage, index + 1, storage, index, shiftCount);
        }
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int realIndex = -index - 1;
        System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        storage[realIndex] = resume;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        Comparator<Resume> uuidComparator = Comparator.comparing(Resume::getUuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, uuidComparator);
    }
}
