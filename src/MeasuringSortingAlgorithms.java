public class MeasuringSortingAlgorithms {
    // Constants defining the measurement parameters
    public static final int MAXSIZE = 10000, INITSIZE = 1000;
    public static final int INCRSIZE = 1000;
    public static final int REPETITIONSQ = 200, REPETITIONSL = 20000;

    /** No objects of this class. */
    private MeasuringSortingAlgorithms() { }

    /** Creates an int array of size t with values 0. Yeah, just don't ask.
     *  @param t int, the size.
     *  @return int[], the generated array.
     */
    private static int[] createArray(int t) {
        int[] a = new int[t];
        return a;
    }

    /** Fills the elements of an int array a
     *  with random values between 0 and a.length-1.
     *  @param a int[], the array.
     */
    private static void fillArrayRandom(int[] a) {
        for(int i=0; i<a.length; i++){
            a[i] = (int) (Math.random()*a.length);
        }
    }

    /** Fills the elements with an int array "a" in ascending order,
     *  with values from 0 to a.length-1.
     *  @param a int[], the array.
     */
    private static void fillArraySortedInAscendingOrder(int[] a) {
        for(int i=0; i<a.length; i++){
            a[i] = i;
        }
    }

    /** Fills the elements with an int array "a" in descending order,
     *  with values from a.length-1 to 0.
     *  @param a int[], the array.
     */
    private static void fillArraySortedInDescendingOrder(int[] a) {
        for(int i=0; i<a.length; i++){
            a[i] = a.length - i;
        }
    }

    public static void measuringSelectionSort() {
        // Print header
        System.out.println("# Selection. Times in microseconds");
        System.out.print("# Size    Average \n");
        System.out.print("#------------------\n");

        long ti, tf; // Initial, final and total times

        for(int i=INITSIZE; i<=MAXSIZE; i+=INCRSIZE){
            int[] a = createArray(i);
            double sum = 0;

            for(int j=0; j<REPETITIONSQ; j++){
                fillArrayRandom(a);

                ti = System.nanoTime();
                MeasurableAlgorithms.selectionSort(a);
                tf = System.nanoTime();

                sum += (tf-ti);
            }

            System.out.println("\t" + a.length + "    " + sum/(REPETITIONSQ*1000));
        }
    }

    public static void measuringInsertionSort() {
        // Print results header
        System.out.println("# Insertion. Times in microseconds.");
        System.out.print("# Size    Best       Worst     Average \n");
        System.out.print("#----------------------------------------\n");

        long tI = 0, tF = 0; // Initial, final and total times

        for(int i=INITSIZE; i<=MAXSIZE; i+=INCRSIZE){
            int[] a = createArray(i);
            double sumB = 0;
            double sumWorst = 0;
            double sumAvg = 0;

            /*
            * Note: The order of the test cases IS important, because of JIT optimizations.
            * Specifically, if you put the best case first, the JIT compiler will use branch prediction if the JVM is
            * in server mode. So the next case to be executed will need to get de-optimized and recompiled, making it
            * take longer to execute, making the results less reliable.
            * You can try this using a JVM in server mode and running this in the next order: best -> average -> worst.
            * You'll notice that the average case will take longer than the worst case for arrays of size ~6000 or more.
            */
            for(int j=0; j<REPETITIONSQ; j++){
                fillArraySortedInDescendingOrder(a);

                tI = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tF = System.nanoTime();

                sumWorst += (tF-tI);
            }

            for(int j=0; j<REPETITIONSQ; j++){
                fillArrayRandom(a);

                tI = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tF = System.nanoTime();

                sumAvg += (tF-tI);
            }

            for(int j=0; j<REPETITIONSL; j++){
                fillArraySortedInAscendingOrder(a);

                tI = System.nanoTime();
                MeasurableAlgorithms.insertionSort(a);
                tF = System.nanoTime();

                sumB += (tF-tI);
            }
            System.out.println("  " + a.length + "  " + sumB/(REPETITIONSL*1000) + "  " + sumWorst/(REPETITIONSQ*1000) + "  " + sumAvg/(REPETITIONSQ*1000));
        }
    }


    private static void help() {
        String msg = "Usage: java MeasuringSortingAlgorithms algorithm_number";
        System.out.println(msg);
        System.out.println("   where algorithm_number is: ");
        System.out.println("   1 -> Selection");
        System.out.println("   2 -> Insertion");
    }

    public static void main(String[] args) {
        if (args.length != 1) { help(); }
        else {
            try {
                int a = Integer.parseInt(args[0]);
                switch (a) {
                    case 1:
                        measuringSelectionSort();
                        break;
                    case 2:
                        measuringInsertionSort();
                        break;
                    default:
                        help();
                }
            } catch (Exception e) {
                help();
            }
        }
    }
}