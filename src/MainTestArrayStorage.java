/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
  private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

  public static void main(String[] args) {
    Resume r1 = new Resume();
    r1.uuid = "uuid1";
    Resume r2 = new Resume();
    r2.uuid = "uuid2";
    Resume r3 = new Resume();
    r3.uuid = "uuid3";

    ARRAY_STORAGE.save(r1);
    ARRAY_STORAGE.save(r2);
    ARRAY_STORAGE.save(r3);

    System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.uuid));
    System.out.println("Size: " + ARRAY_STORAGE.size());

    System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

    printAll();
    ARRAY_STORAGE.delete(r1.uuid);
    printAll();

    ARRAY_STORAGE.update(r1);
    printAll();

    ARRAY_STORAGE.clear();
    printAll();

    System.out.println("Size: " + ARRAY_STORAGE.size());

    for (int i = 0; i <= ARRAY_STORAGE.getStorageSize(); i++) {
      Resume resume = new Resume();
      resume.uuid = "uuid" + i;
      ARRAY_STORAGE.save(resume);
    }
  }

  private static void printAll() {
    System.out.println("\nGet All");
    for (Resume r : ARRAY_STORAGE.getAll()) {
      System.out.println(r);
    }
  }
}
