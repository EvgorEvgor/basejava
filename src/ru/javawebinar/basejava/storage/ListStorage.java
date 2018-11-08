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
        return storage.toArray(new Resume[0]);
    }


    @Override
    public int size() {
        return storage.size();
    }

    @Override
    void doDelete(Object index) {
        storage.remove((int) index);
    }

    @Override
    Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    void doSave(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storage.set((int) index, resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
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
