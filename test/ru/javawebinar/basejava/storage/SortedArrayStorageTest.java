package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertArrayEquals;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    @Override
    public void getAll() {
        Resume[] expectedResult = {RESUME1, RESUME2, RESUME3};
        assertArrayEquals(expectedResult, storage.getAll());
    }
}
