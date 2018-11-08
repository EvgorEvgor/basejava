package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract void doDelete(Object key);

    abstract Resume doGet(Object key);

    abstract void doSave(Resume resume, Object key);

    abstract void doUpdate(Resume resume, Object key);

    protected abstract Object getFindedKey(String uuid);

    protected abstract boolean isExist(Object key);

    @Override
    public void delete(String uuid) {
        Object key = getExistedKey(uuid);
        doDelete(key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistedKey(uuid);
        return doGet(key);
    }

    @Override
    public void save(Resume resume) {
        Object key = getNotExistedKey(resume.getUuid());
        doSave(resume, key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistedKey(resume.getUuid());
        doUpdate(resume, key);
    }

    private Object getExistedKey(String uuid) {
        Object findedKey = getFindedKey(uuid);
        if (!isExist(findedKey)) {
            throw new NotExistStorageException(uuid);
        }
        return findedKey;
    }

    private Object getNotExistedKey(String uuid) {
        Object findedKey = getFindedKey(uuid);
        if (isExist(findedKey)) {
            throw new ExistStorageException(uuid);
        }
        return findedKey;
    }
}
