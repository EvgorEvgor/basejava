package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    void doDelete(Object key) {
        storage.remove((String) key);
    }

    @Override
    Resume doGet(Object key) {
        return storage.get(key);
    }

    @Override
    void doSave(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    void doUpdate(Resume resume, Object key) {
        storage.replace((String) key, resume);
    }

    protected Object getFindedKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey(key);
    }
}
