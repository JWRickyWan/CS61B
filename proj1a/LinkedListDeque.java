public class LinkedListDeque<T> implements Deque<T>{
    public int size = 0;
    public T first;
    public LinkedListDeque rest;
    private Node sentinel;
    public class Node{
        Node prev;
        T item;
        Node next;
        Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }

    }
    public  LinkedListDeque(){
        sentinel = new Node (null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        Node curList = new Node(sentinel, item, sentinel.next);
        size +=1;
        sentinel.next.prev = curList;
        sentinel.next = curList;
    }
    public void addLast(T item){
        Node curList = new Node(sentinel.prev,item,sentinel);
        size +=1;
        sentinel.prev.next = curList;
        sentinel.prev = curList;
    }
    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node curNode = sentinel.next;
        while(curNode != sentinel){
            System.out.print(curNode.item);
            curNode = curNode.next;
        }
    }
    public T removeFirst(){
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size-=1;
        return first;
    }

    public T removeLast(){
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size-=1;
        return last;
    }

    public T get(int index) {
        Node CurNode = sentinel;
        for (int i = index; i > 0; i++){
            CurNode = CurNode.next;
        }
        return CurNode.item;
    }

    public T getRecursive(int index){
        Node CurNode = sentinel.next;
        return getRecursiveHelper(CurNode,index);
    }
    public T getRecursiveHelper(Node CurNode,int index){
        if(index ==0){
            return CurNode.item;
        }
        CurNode = CurNode.next;
        index -=1;
        return getRecursiveHelper(CurNode,index);
    }

}
