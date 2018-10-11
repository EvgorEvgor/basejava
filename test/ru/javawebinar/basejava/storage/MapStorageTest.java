package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertThat(Arrays.asList(resumes), hasItem(RESUME1));
        assertThat(Arrays.asList(resumes), hasItem(RESUME2));
        assertThat(Arrays.asList(resumes), hasItem(RESUME3));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}
