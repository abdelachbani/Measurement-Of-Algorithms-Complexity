# Measurement of Algorithm Complexity

An experimental project to measure the complexity of common searching and sorting algorithms in Java and visualize the results.

-----

## 📋 Description

This project provides a hands-on approach to understanding algorithm complexity. It contains Java code to measure the execution time of several fundamental algorithms under different scenarios (best, worst, and average cases). The collected data is then used with Gnuplot to generate graphical representations of their performance, offering a clear visual comparison of their efficiency.

-----

## ✨ Features

The following algorithms are measured and analyzed:

* **Searching:**
    * Linear Search ($`\Omega(1)`$, $`\mathcal{O}(n)`$)
* **Sorting:**
    * Selection Sort ($`\Theta(n^2)`$)
    * Insertion Sort ($`\Omega(n)`$, $`\mathcal{O}(n^2)`$)

-----

## ⚙️ How It Works

The project is divided into two main parts:

1.  **Measurement in Java:**

    * The `src` directory contains the Java source code.
    * `MeasuringLinearSearch.java` and `MeasuringSortingAlgorithms.java` contain the main logic to run the experiments.
    * They systematically test the algorithms with increasing data sizes, from an `INITSIZE` to a `MAXSIZE`.
    * For sorting algorithms, different initial array states are used to simulate best-case (already sorted), worst-case (reverse sorted), and average-case (randomly filled) scenarios.
    * The execution time is measured in nanoseconds for precision and then converted to microseconds for the final report.

2.  **Visualization with Gnuplot:**

    * The Java programs print the results to the standard output. This output can be redirected to `.out` files.
    * The `Gnuplot Plots` directory contains `.plot` scripts that read the data from the `.out` files.
    * These scripts generate plots and can also fit functions to the data points to visualize the theoretical complexity curves (e.g., linear `f(x) = a*x+b`).

-----

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK)
* Gnuplot

### Instructions

1.  **Clone the repository:**

    ```sh
    git clone https://github.com/abdelachbani/measurement-of-algorithms-complexity.git
    cd measurement-of-algorithms-complexity
    ```

2.  **Compile the Java code:**

    ```sh
    javac src/*.java
    ```

3.  **Run the measurements:**

    * Run the desired measurement class and redirect the output to a data file. For example, for Linear Search:
      ```sh
      java -cp src MeasuringLinearSearch > "Gnuplot Plots/linearSearch.out"
      ```
    * For sorting algorithms, you need to pass an argument (1 for Selection, 2 for Insertion):
      ```sh
      java -cp src MeasuringSortingAlgorithms 2 > "Gnuplot Plots/insertionSort.out"
      ```

4.  **Generate the plots:**

    * Navigate to the Gnuplot directory:
      ```sh
      cd "Gnuplot Plots"
      ```
    * Run Gnuplot with the corresponding plot script:
      ```sh
      gnuplot linearSearch1.plot
      ```
    * The scripts will generate `.pdf` files in the `Graphics` directory.

-----

## 📊 Results

The `Graphics` directory contains the generated PDF files with the complexity plots.

> [!NOTE] The sample data files and the graphics are using data from the execution of the measurement
programs, executed in a JVM in client mode. That said, the times may be higher than those obtained in a JVM in server mode.
Anyway, **the goal is to show the complexity of the algorithms**, not to measure the absolute time,
and the results are perfectly reflecting that.

Example of another sample dat file for Insertion Sort using a server mode JVM:

```
# Insertion. Times in microseconds.
# Size    Best       Worst     Average 
#----------------------------------------
  1000  1.09172  267.27  160.3795
  2000  2.25713  555.376  331.791
  3000  2.35566  1106.151  492.762
  4000  2.312425  1664.2215  747.1365
  5000  3.677125  2137.58  1211.958
  6000  4.00604  3750.3745  1760.715
  7000  5.49219  4334.0675  2532.068
  8000  5.64816  6834.839  3308.36
  9000  6.10659  7202.74  3767.5865
  10000  7.150745  9095.712  4636.191

```
### Insertion Sort Complexity ($`\Omega(n)`$, $`\mathcal{O}(n^2)`$)

*(Source: Graphics/InsertionSortComplexity.pdf)*

### Linear Search Complexity ($`\Omega(1)`$, $`\mathcal{O}(n)`$)

*(Source: Graphics/LinearSearchComplexity.pdf)*

### Selection Sort Complexity ($`\Theta(n^2)`$)

*(Source: Graphics/SelectionSortComplexity.pdf)*
