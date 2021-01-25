package sudoku;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuBoardRepository {
    private final EntityManager entityManager;

    public SudokuBoardRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(SudokuBoard board, String name) throws IllegalArgumentException{
        board.setName(name);
        entityManager.getTransaction().begin();
        entityManager.merge(board);
        entityManager.getTransaction().commit();
    }

    public SudokuBoard findByName(String name) throws IllegalArgumentException {
        return  entityManager.find(SudokuBoard.class, name);
    }
//
    public List<String> findAll() {
        return entityManager.createQuery("select a from SudokuBoard a",
        SudokuBoard.class).getResultList().stream().map(SudokuBoard::getName).collect(Collectors.toList());
    }
}
