package Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import AddressBook.AddressBook;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.jupiter.api.*;


import AddressBook.*;

public class FileSystemTest {

  AddressBook bookTest = new AddressBook();
  AddressBookController controllerTest = new AddressBookController(bookTest);
  Person personTest = new Person("test", "person", "street", "city",
      "state", "num", "phone");


  @Test
  void readFile() {
    FileSystem fileSystemTest = new FileSystem();
    bookTest.add(personTest);

    Throwable exception = assertThrows(FileNotFoundException.class, () -> {
      throw new FileNotFoundException("FileNotFound");
    });

    Throwable exception2 = assertThrows(SQLException.class, () -> {
      throw new SQLException("SQL Exception");
    });

    File fileTest = new File("Book Test");
    assertDoesNotThrow(() -> fileSystemTest.saveFile(bookTest, fileTest));

    assertDoesNotThrow(()-> fileSystemTest.readFile(bookTest,fileTest));
    assertTrue(bookTest.getRowCount()==1);

    boolean canReadTest = fileTest.canRead();

    assertEquals("FileNotFound", exception.getMessage());
    assertEquals("SQL Exception", exception2.getMessage());
    assertTrue(canReadTest);

  }


  @Test
  void saveFile() throws SQLException {

    FileSystem fileSystemTest = new FileSystem();

    Throwable exception2 = assertThrows(SQLException.class, () -> {
      throw new SQLException("SQL Exception");
    });

    File fileTest = new File("Book Test");

    assertDoesNotThrow(()->fileSystemTest.saveFile(bookTest,fileTest));

    boolean existTest = fileTest.exists();
    assertTrue(existTest, fileTest.toString());


  }

}
