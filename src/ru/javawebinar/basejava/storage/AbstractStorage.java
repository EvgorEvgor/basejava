package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract void doDelete(Object key);

    abstract Resume doGet(Object key);

    abstract void doSave(Resume resume, Object key);

    abstract void doUpdate(Resume resume, Object key);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object key);

    @Override
    public void delete(String uuid) {
        Object key = getExistedSearchKey(uuid);
        doDelete(key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistedSearchKey(uuid);
        return doGet(key);
    }

    @Override
    public void save(Resume resume) {
        Object key = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, key);
    }

    private Object getExistedSearchKey(String uuid) {
        Object findedKey = getSearchKey(uuid);
        if (!isExist(findedKey)) {
            throw new NotExistStorageException(uuid);
        }
        return findedKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object findedKey = getSearchKey(uuid);
        if (isExist(findedKey)) {
            throw new ExistStorageException(uuid);
        }
        return findedKey;
    }
}
