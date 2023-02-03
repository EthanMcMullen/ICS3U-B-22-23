public class ForEachLoop {
    public static void main(String[] args) {
        String[] feelings = { "Happy", "Sad", "Angry", "Blah", "Excited", "Sleepy" };

        //for each loop - meant to iterate throught he whole collection
        // cannot change the size of the collection
        // no access to the indes
        // must iterate starting at index 0 to length - 1

        for(String mood : feelings) {
            System.out.println(mood);
        }
    }
}
