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
    private static final Resume r1 = new Resume(UUID1);

    private static final String UUID2 = "uuid2";
    private static final Resume r2 = new Resume(UUID2);

    private static final String UUID3 = "uuid3";
    private static final Resume r3 = new Resume(UUID3);

    private static final String UUID4 = "uuid4";
    private static final Resume r4 = new Resume(UUID4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
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
        assertEquals(r1, storage.get(UUID1));
        assertEquals(r2, storage.get(UUID2));
        assertEquals(r3, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        final String DUMMY = "dummy";
        storage.get(DUMMY);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(r1, resumes[0]);
        assertEquals(r2, resumes[1]);
        assertEquals(r3, resumes[2]);
    }

    @Test
    public void save() {
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get(UUID4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get(UUID4));
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        String nameTestUuid = "testUuid";
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume(nameTestUuid + i));
        }
        storage.save(r4);
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
