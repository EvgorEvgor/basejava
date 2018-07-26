import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
  private Resume[] storage = new Resume[10000];
  private int size = 0;

  void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  void save(Resume r) {
    int indexUuid = getIndexUuid(r.uuid);

    if (indexUuid < 0) {
      storage[size] = r;
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
      if (storage[i].uuid == uuid) {
        return i;
      }
    }
    return -1;
  }
}
