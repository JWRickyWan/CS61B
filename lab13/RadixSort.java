/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int Max = Integer.MIN_VALUE;
        for(String i :asciis){
                Max = Math.max(Max, i.length());
            }
        while (Max>0){
            sortHelperLSD(asciis,Max-1);
            Max--;
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] tracking = new int[256];
        for (String ascii : asciis) {
            if (ascii.length() - 1 < index) {
                tracking[0] += 1;
            } else {
                char letter = ascii.charAt(index);
                tracking[(int) letter]++;
            }
        }
        int[] start = new int[256];
        int pos = 0;
        for(int k=0;k<start.length;k++){
            start[k] = pos;
            pos+=tracking[k];
        }
        String[] copy = asciis.clone();
        for (String ascii : copy) {
            int item = 0;
            if (ascii.length() - 1 >= index) {
                item = ascii.charAt(index);
            }
                int place = start[item];
                asciis[place] = ascii;
                start[item]+=1;

        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args){
        String[] a = new String[]{ "Gotengco", "Lira","Guban", "Garraez", "Liam","Noah","William","James"
                ,"Logan","Benjamin","Mason","Elijah","Oliver","Jacob","Lucas","Michael","Alexander","Ethan"
                ,"Daniel","Matthew","Aiden","Henry","Joseph","Jackson","Samuel","Sebastian","David","Carter",
                "Wyatt","Jayden","John","Owen","Dylan","Luke","Gabriel","Anthony","Isaac","Grayson","Jack"
                ,"Julian","Levi","Christopher","Joshua","Andrew","Lincoln","Mateo","Ryan","Jaxon","Nathan",
                "Aaron","Isaiah","Thomas","Charles","Caleb","Josiah","Christian","Hunter","Eli","Jonathan",
                "Connor","Landon","Adrian","Asher","Cameron","Leo","Theodore","Jeremiah","Hudson","Robert",
                "Easton","Nolan","Nicholas","Ezra","Colton"};
        String[] b;
        b = sort(a);
        for(String s : b){
            System.out.println(s);
        }
    }
}
