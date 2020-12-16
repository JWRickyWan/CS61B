public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0;i< word.length(); i++) {

            deque.addLast(word.charAt(i));

        }

        return deque;
    }
    private String getLetters(Deque deque, int size,String order) {
        String output = "";
        if (order == "last") {
            for (int i = 0; i < size / 2; i++) {
                output += deque.removeLast();
            }
            return output;
        }
        for (int i = 0; i < size / 2; i++) {
            output += deque.removeFirst();
        }
        return output;
    }
    private boolean checkObO(Deque deque, int size,CharacterComparator cc) {
        String m = getLetters(deque, size, "last");
        String n = getLetters(deque, size, "first");
        for (int i = 0; i < m.length(); i++) {
            if (!cc.equalChars(m.charAt(i), n.charAt(i))) {
                return false;
            }
        }
            return true;
        }








    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        if (deque.size() % 2 == 0) {
            int size = deque.size();
            String front = getLetters(deque, size,"last");
            String back = getLetters(deque, size,"first");
            return back.equals(front);
        }
        int size = deque.size() - 1;
        String front = getLetters(deque, size,"last");
        deque.removeLast();
        String back = getLetters(deque, size,"first");
        return back.equals(front);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        if (deque.size() % 2 == 0) {
            int size = deque.size();
            return checkObO(deque, size, cc);
        }

        int size = deque.size() - 1;
        return checkObO(deque, size, cc);
    }


    }
