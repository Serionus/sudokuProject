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
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        try (ObjectInputStream sin = new ObjectInputStream(new FileInputStream(fileName))) {
            return (SudokuBoard) sin.readObject();
        }
    }

    @Override
    public void write(Object object) throws IOException {
        try (ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream(fileName))) {
            sout.writeObject(object);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
