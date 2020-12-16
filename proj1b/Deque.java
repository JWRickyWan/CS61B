public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public T get(int index);
    public T removeLast();
    public T removeFirst();
    public boolean isEmpty();
    public void printDeque();
    public int size();
}


