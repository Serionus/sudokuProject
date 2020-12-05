import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao {
    String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public SudokuBoard read() {
        try (ObjectInputStream sin = new ObjectInputStream(new FileInputStream(fileName))) {
            return (SudokuBoard) sin.readObject();
        } catch (IOException | ClassNotFoundException ie) {
            System.out.println("Błąd podczas odczytu z pliku: "
                    + fileName + " obiektu typu SudokuBoard");
            return null;
        }
    }

    @Override
    public void write(Object object) {
        try (ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream(fileName))) {
            sout.writeObject(object);
        } catch (IOException ie) {
            System.out.println("Błąd podczas zapisu do pliku: "
                    + fileName + " obiektu typu SudokuBoard");
        }
    }
}
