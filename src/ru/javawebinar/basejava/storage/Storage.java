package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void delete(String uuid);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void save(Resume resume);

    int size();

    void update(Resume resume);
}
