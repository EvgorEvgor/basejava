package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract void doDelete(Object key);

    abstract Resume doGet(Object key);

    abstract void doSave(Resume r, Object key);

    abstract void doUpdate(Resume r, Object key);

    protected abstract Object getFindedKey(String uuid);

    protected abstract boolean isExist(Object key);

    @Override
    public void delete(String uuid) {
        Object key = getKey(uuid, true);
        doDelete(key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKey(uuid, true);
        return doGet(key);
    }

    @Override
    public void save(Resume r) {
        Object key = getKey(r.getUuid(), false);
        doSave(r, key);
    }

    @Override
    public void update(Resume r) {
        Object key = getKey(r.getUuid(), true);
        doUpdate(r, key);
    }

    private Object getKey(String uuid, boolean existed) {
        Object findedKey = getFindedKey(uuid);
        if (existed) {
            if (!isExist(findedKey)) {
                throw new NotExistStorageException(uuid);
            }
        } else {
            if (isExist(findedKey)) {
                throw new ExistStorageException(uuid);
            }
        }
        return findedKey;
    }
}
