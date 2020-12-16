public class ArrayDeque<T> implements Deque<T> {
    private static final int ArrayCapacity = 8;
    private T[] items;
    private int size;
    private int frontPointer;
    private int backPointer;
    public ArrayDeque(){
        items = (T[]) new Object[ArrayCapacity];
        size = 0;
        frontPointer = 0;
        backPointer = 1;
    }
    private int addOne(int index){
        return (index+1)%items.length;
    }
    private int minusOne(int index){
        return (index-1+items.length)%items.length;
    }
    private boolean isFull(){
        return size == items.length;
    }
    private boolean isSpare(){
        return (items.length >= 16 && size < (items.length / 4));
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }
    private void resize(int capacity){
        T[] newDeque = (T[]) new Object[capacity];
        int oldIndex = addOne(frontPointer);
        for(int i = 0; i < size; i++){
            newDeque[i] = items[oldIndex];
            oldIndex = addOne(oldIndex);
        }
        items = newDeque;
        frontPointer = capacity-1;
        backPointer = size;

    }
    private void size_up(){
        resize(2*size);

    }
    private void size_down(){
        resize(items.length/2);

    }
    public static int getArrayCapacity() {
        return ArrayCapacity;
    }
    @Override
    public void addLast(T item){
        if (isFull()){
            size_up();
        }
        items[backPointer] = item;
        backPointer = addOne(backPointer);
        size +=1;
    }
    @Override
    public T removeLast(){
        if (isSpare()){
            size_down();
        }
        backPointer = minusOne(backPointer);
        T x = items[backPointer];
        items[backPointer] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return x;
    }
    @Override
    public T removeFirst(){
        if(isSpare()){
            size_down();
        }
        frontPointer = addOne(frontPointer);
        T x = items[frontPointer];
        items[frontPointer] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return x;
    }
    @Override
    public void addFirst(T item){
        if(isFull()){
            size_up();
        }

        items[frontPointer] = item;
        frontPointer = minusOne(frontPointer);
        size+=1;
    }
    @Override
    public T get(int index){
        if(index ==0){
            return items[frontPointer];
        }
        int start = addOne(frontPointer);

        return items[(start+index)%items.length];
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for(int i = size; i>0;i--){
            System.out.print(get(i));
        }
    }

    }

