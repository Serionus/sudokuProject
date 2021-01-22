package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class FileSudokuBoardDao implements Dao {
    String fileName;
    ResourceBundle bundle;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
        bundle = ResourceBundle.getBundle("SudokuBundle", Locale.getDefault());
    }

    public FileSudokuBoardDao(String fileName, ResourceBundle bundle) {
        this.fileName = fileName;
        this.bundle = bundle;
    }

    @Override
    public void close() {

    }

    @Override
    public SudokuBoard read() throws WrongFileChosenException {
        try (ObjectInputStream sin = new ObjectInputStream(new FileInputStream(fileName))) {
            return (SudokuBoard) sin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new WrongFileChosenException(bundle.getString("fileChooserException"), e);

        }
    }

    //1
    //jakis tam wyjatek
    //spowodowany wrongclassusedexception
    //spowodowany ioexception albo class not found exception

    //2
    //jakis tam wyjatek
    //spowodowany ioexception
    //spowodowany wrongclassusedexception

    @Override
    public void write(Object object) throws FileCreateException {
        try (ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream(fileName))) {
            sout.writeObject(object);
        } catch (IOException e) {
            throw new FileCreateException(bundle.getString("classUsageException"), e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
