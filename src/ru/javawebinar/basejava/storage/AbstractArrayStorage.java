package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10000;
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
    void doSave(Resume resume, Object key) {
        int index = (Integer) key;
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    @Override
    void doUpdate(Resume resume, Object key) {
        int index = (Integer) key;
        storage[index] = resume;
    }

    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract void deleteElement(int index);

    protected abstract void insertElement(Resume resume, int index);
}
