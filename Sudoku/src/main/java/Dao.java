public interface Dao<T> extends AutoCloseable {
    T read() throws Exception;

    void write(T object) throws Exception;

    @Override
    void close() throws Exception;
}
