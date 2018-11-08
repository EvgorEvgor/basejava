package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void getSortedAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(RESUME1, resumes[0]);
        assertEquals(RESUME2, resumes[1]);
        assertEquals(RESUME3, resumes[2]);
    }
}
