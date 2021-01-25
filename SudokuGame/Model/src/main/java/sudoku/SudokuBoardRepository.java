package sudoku;

import javax.persistence.EntityManager;
import java.util.List;

public class SudokuBoardRepository {
    private final EntityManager entityManager;

    public SudokuBoardRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(SudokuBoard board, String name) {
        board.setName(name);
        entityManager.getTransaction().begin();
        entityManager.merge(board);
//        entityManager.persist(board);
        entityManager.getTransaction().commit();
    }

    public SudokuBoard findByName(String name) {
        return entityManager.find(SudokuBoard.class, name);
    }
//
    public List<SudokuBoard> findAll() {
        return entityManager.createQuery("select a from SudokuBoard a",
        SudokuBoard.class).getResultList();
    }
}
