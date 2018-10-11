package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

abstract class AbstractStorageTest {
    static final String DUMMY = "dummy";

    static final String UUID1 = "uuid1";
    static final String UUID2 = "uuid2";
    static final Resume RESUME1;
    static final Resume RESUME2;
    static final Resume RESUME3;
    static final Resume RESUME4;
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";

    static {
        RESUME1 = new Resume(UUID1);
        RESUME2 = new Resume(UUID2);
        RESUME3 = new Resume(UUID3);
        RESUME4 = new Resume(UUID4);
    }

    Storage storage;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @After
    public void tearDown() {
        storage.clear();
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID2);
        assertSize(2);
        storage.get(UUID2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void get() {
        assertGet(RESUME1);
        assertGet(RESUME2);
        assertGet(RESUME3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertSize(4);
        assertGet(RESUME4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get(DUMMY);
    }

    protected abstract void assertSize(int size);

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}
