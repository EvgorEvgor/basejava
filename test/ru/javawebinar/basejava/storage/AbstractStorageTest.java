package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

abstract class AbstractStorageTest {
    Storage storage;

    private static final String DUMMY = "dummy";

    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID4 = "uuid4";

    static final Resume RESUME1;
    static final Resume RESUME2;
    static final Resume RESUME3;
    static final Resume RESUME4;


    static {
        RESUME1 = new Resume(UUID1);
        RESUME2 = new Resume(UUID2);
        RESUME3 = new Resume(UUID3);
        RESUME4 = new Resume(UUID4);
    }



    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME3);
        storage.save(RESUME2);
    }

    @After
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
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
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertThat(Arrays.asList(resumes), hasItem(RESUME1));
        assertThat(Arrays.asList(resumes), hasItem(RESUME2));
        assertThat(Arrays.asList(resumes), hasItem(RESUME3));
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
    public void size() {
        assertSize(3);
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

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
