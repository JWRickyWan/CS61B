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
    private T getLast(){
        return items[backPointer];
    }
    private T getFirst(){
        return items[frontPointer];
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
        return size/items.length <= 0.25;
    }
    private boolean isEmpty(){
        return size==0;
    }
   private void size_up(){
        T[] a = (T[]) new Object[size*2];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }
    private void size_down(){
        T[] a = (T[]) new Object[items.length/2];
        System.arraycopy(items,0,a,0,items.length/2);
        items = a;
    }

    public static int getArrayCapacity() {
        return ArrayCapacity;
    }
    public void addLast(T item){
        if (isFull()){
            size_up();
        }
        backPointer = addOne(backPointer);
        items[backPointer] = item;
        size +=1;
    }
    public T removeLast(T item){
        T x = getLast();
        backPointer = minusOne(backPointer);
        size -=1;
        if (isSpare()){
            size_down();
        }
        return x;
    }

    public T removeFirst(T item){
        T x = getFirst();
        frontPointer = addOne(frontPointer);
        if(isSpare()){
            size_down();
        }
        if(!isEmpty()){
            size-=1;
        }
        return x;
    }

    public void addFirst(T item){
        if(isFull()){
            size_up();
        }
        items[frontPointer] = item;
        frontPointer = minusOne(frontPointer);
        size+=1;
    }

    public T get(int index){
        if(index ==0){
            return items[frontPointer];
        }
        int start = addOne(frontPointer);

        return items[(start+index)%items.length];
    }

}
