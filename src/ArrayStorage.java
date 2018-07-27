import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
  private final int STORAGE_SIZE = 10000;
  private Resume[] storage = new Resume[STORAGE_SIZE];
  private int size = 0;

  int getStorageSize() {
    return STORAGE_SIZE;
  }

  void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  void update(Resume resume) {
    int indexUuid = getIndexUuid(resume.uuid);
    if (indexUuid < 0) {
      System.out.println("ERROR Illegal argument");
      return;
    }
    storage[indexUuid] = resume;
  }

  void save(Resume resume) {
    int indexUuid = getIndexUuid(resume.uuid);
    if (indexUuid < 0) {
      if (size >= STORAGE_SIZE) {
        System.out.println("ERROR Storage size was exceeded.");
        return;
      }
      storage[size] = resume;
      size++;
    }
  }

  Resume get(String uuid) {
    int indexUuid = getIndexUuid(uuid);
    if (indexUuid < 0) {
      return null;
    }
    return storage[indexUuid];
  }

  void delete(String uuid) {
    int indexUuid = getIndexUuid(uuid);
    if (indexUuid >= 0) {
      int lastIndex = size - 1;
      if (indexUuid != lastIndex) {
        storage[indexUuid] = storage[lastIndex];
      }
      storage[lastIndex] = null;
      size--;
    } else {
      System.out.println("ERROR Illegal argument");
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    return Arrays.copyOf(storage, size);
  }

  int size() {
    return size;
  }

  private int getIndexUuid(String uuid) {
    for (int i = 0; i < size; i++) {
      if (uuid == storage[i].uuid) {
        return i;
      }
    }
    return -1;
  }
}