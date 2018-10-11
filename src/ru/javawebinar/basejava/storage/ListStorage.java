package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new LinkedList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray(new Resume[storage.size()]);
    }


    @Override
    public int size() {
        return storage.size();
    }

    @Override
    void doDelete(Object key) {
        int index = (Integer) key;
        storage.remove(index);
    }

    @Override
    Resume doGet(Object key) {
        int index = (Integer) key;
        return storage.get(index);
    }

    @Override
    void doSave(Resume r, Object key) {
        storage.add(r);
    }

    @Override
    public void doUpdate(Resume r, Object key) {
        int index = (Integer) key;
        storage.set(index, r);
    }

    @Override
    protected Object getFindedKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }
}
