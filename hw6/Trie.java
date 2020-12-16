

public class Trie {
    private static final int R = 128;
    private Node root;
    public Trie(){
        root = new Node();
    }
    private class Node{
        boolean exists;
        Node[] links;

        public Node(){
            links = new Node[R];
            exists = false;
        }
    }

    public void put(String s){
        putHelper(s,root,0);
    }

    private Node putHelper(String s, Node root, int d){
        if(root ==null){
            root = new Node();
        }
        else if(d == s.length()-1){
            root.exists=true;
            return root;
        }
        char c = s.charAt(d);
        root.links[c] = putHelper(s,root.links[c],d+1);
        return root;


    }

    public boolean exists(String s){
        return existsHelper(s,root,0);
    }
    private boolean existsHelper(String s,Node root,int d){
        Node nextNode = root.links[s.charAt(d)];
        if(nextNode==null){
            return false;
        }
        if(d==s.length()-1){
            return root.links[s.charAt(d)].exists;
        }
        else{
            return existsHelper(s,nextNode,2);
        }

    }


}
