package sudoku;

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
    public void close() {

    }

    @Override
    public SudokuBoard read() throws WrongFileChosenException {
        try (ObjectInputStream sin = new ObjectInputStream(new FileInputStream(fileName))) {
            return (SudokuBoard) sin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 1
            throw new WrongFileChosenException("Wybrano zla klase do zapisu", e);
            // 2
            // exception.initCause(new WrongClassUsedException("Wybrano zla klase do zapisu"));
            // throw exception;
        }
    }

    @Override
    public void write(Object object) throws IOException {
        try (ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream(fileName))) {
            sout.writeObject(object);
        } catch (IOException e){
            throw new WrongClassUsedException("Wybrano zla klase do zapisu", e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
