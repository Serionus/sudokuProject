package sudoku;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class JPASudokuBoardDao implements Dao<SudokuBoard>{
    String fileName;
    ResourceBundle bundle;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SudokuBoard");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    SudokuBoardRepository repository = new SudokuBoardRepository(entityManager);

    public JPASudokuBoardDao(String fileName) {
        this.fileName = fileName;
        bundle = ResourceBundle.getBundle("SudokuBundle", Locale.getDefault());
    }

    public JPASudokuBoardDao(String fileName, ResourceBundle bundle) {
        this.fileName = fileName;
        this.bundle = bundle;
    }

    @Override
    public void close() {

    }

    @Override
    public SudokuBoard read() throws WrongFileChosenException {
        try {
            return repository.findByName(fileName);
        } catch (IllegalArgumentException e) {
            throw new WrongFileChosenException(bundle.getString("fileChooserException"), e);

        }
    }

    @Override
    public void write(SudokuBoard board) throws  FileCreateException {
        try {
            repository.save(board, fileName);
        } catch (IllegalArgumentException e) {
            throw new FileCreateException(bundle.getString("classUsageException"), e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public SudokuBoardRepository getRepository() {
        return repository;
    }
}
