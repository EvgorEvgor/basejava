package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";

    private static final Resume RESUME1;
    private static final Resume RESUME2;
    private static final Resume RESUME3;
    private static final Resume RESUME4;

    static {
        RESUME1 = new Resume(UUID1);
        RESUME2 = new Resume(UUID2);
        RESUME3 = new Resume(UUID3);
        RESUME4 = new Resume(UUID4);
    }

    protected AbstractArrayStorageTest(Storage storage) {
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

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID2);
        assertEquals(2, storage.size());
        storage.get(UUID2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        final String DUMMY = "dummy";
        storage.delete(DUMMY);
    }

    @Test
    public void get() {
        assertEquals(RESUME1, storage.get(UUID1));
        assertEquals(RESUME2, storage.get(UUID2));
        assertEquals(RESUME3, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        final String DUMMY = "dummy";
        storage.get(DUMMY);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(RESUME1, resumes[0]);
        assertEquals(RESUME2, resumes[1]);
        assertEquals(RESUME3, resumes[2]);
    }

    @Test
    public void save() {
        storage.save(RESUME4);
        assertEquals(4, storage.size());
        assertEquals(RESUME4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME3);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        String nameTestUuid = "testUuid";
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume(nameTestUuid + i));
        }
        storage.save(RESUME4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID1));
    }

    @Test (expected = NotExistStorageException.class)
    public void updateNotExist() {
        final String DUMMY = "dummy";
        storage.get(DUMMY);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}
