
public interface Fila{
    public boolean isEmpty();
    public Object first() throws EFilaVazia;
    public Object end() throws EFilaVazia;
    public void enqueue(Object o);
    public Object dequeue() throws EFilaVazia;
}