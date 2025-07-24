import java.util.Locale;
public class MeasuringLinearSearch {
    // Constants defining the measurement parameters
    public static final int MAXSIZE = 100000, INITSIZE = 10000;
    public static final int INCRSIZE = 10000;
    public static final int REPETITIONS = 100000, REP_BESTCASE = 2000000;
    public static final double NMS = 1e3;  // micro - nanoseconds ratio

    /** No objects of this class. */
    private MeasuringLinearSearch() { }
    
    /** Creates an int array of size 'size',
     *  with increasing values from 0 to size-1.
     *  @param size int, the size.
     *  @return int[], the array.
     */
    private static int[] createArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) { a[i] = i; }
        return a;
    }

    public static void measuringLinearSearch() {
        long ti, tf, tt; // Initial, final and total times
        // Print results header
        System.out.println("# Linear search. Times in microseconds");
        System.out.print("# Size       Best       Worst     Average\n");
        System.out.print("#------------------------------------------\n");

        // This loop repeats the process for several sizes
        for (int t = INITSIZE; t <= MAXSIZE; t += INCRSIZE) {
            // Create the array once for each size
            int[] a = createArray(t);
      
            // Best case study: search for a[0]
            // NOTE: As it is very fast,
            // the number of repetitions is higher
            tt = 0;                        // Initially accumulated time to 0
            for (int r = 0; r < REP_BESTCASE; r++) {
                ti = System.nanoTime();      // Initial time
                MeasurableAlgorithms.linearSearch(a, a[0]);
                tf = System.nanoTime();      // Final time
                tt += (tf - ti);             // Update accumulated time
            }
            double tBest = (double) tt / REP_BESTCASE; // Average time
                                                         // of the best case

            // Worst case study: search for one that is not present e.g. -1
            tt = 0;                        // Initially accumulated time to 0
            for (int r = 0; r < REPETITIONS; r++) {
                ti = System.nanoTime();      // Initial time
                MeasurableAlgorithms.linearSearch(a, -1);
                tf = System.nanoTime();      // Final time
                tt += (tf - ti);             // Update accumulated time
            }
            double tWorst = (double) tt / REPETITIONS; // Average time
                                                       // of the worst case

            // Average case study: search for a random number
            // between 0 and t-1
            tt = 0;                        // Initially accumulated time to 0
            for (int r = 0; r < REPETITIONS; r++) {
                int aux = (int) (Math.random() * t); // Number to search
                ti = System.nanoTime();      // Initial time
                MeasurableAlgorithms.linearSearch(a, aux);
                tf = System.nanoTime();      // Final time
                tt += (tf - ti);         // Update accumulated time
            }
            double tAvg = (double) tt / REPETITIONS; // Average time
                                                         // of the average case

            // Print results
            System.out.printf(Locale.US, "%8d %10.3f %10.3f %10.3f\n",
                              t, tBest / NMS, tWorst / NMS, tAvg / NMS);
        }
    }

    public static void main(String[] args) {
        measuringLinearSearch();
    }
}
