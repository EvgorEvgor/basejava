package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getLimit() {
        return STORAGE_LIMIT;
    }

    public int size() {
        return size;
    }

    @Override
    void doDelete(Object key) {
        int index = (Integer) key;
        deleteElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    Resume doGet(Object key) {
        int index = (Integer) key;
        return storage[index];
    }

    @Override
    void doSave(Resume r, Object key) {
        int index = (Integer) key;
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    void doUpdate(Resume r, Object key) {
        int index = (Integer) key;
        storage[index] = r;
    }

    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract void deleteElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
