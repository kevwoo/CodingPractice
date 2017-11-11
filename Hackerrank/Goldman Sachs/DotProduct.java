// Implement dotProduct class. This should return the dot product (the sum of the products of corresponding entries)
// of two input arrays and 0 for any error conditions(e.g. arrays of unequal length or empty arrays)
//
// Input Format
// Two arrays of integers entered with the length, N, followed by the values
// N
// x_0
// ...
// x_n-1
// N
// y_0
// ...
// y_n-2
//
// Constratins
// Input array lengths are between 0 and 100.
//
// Output Format
// Your fucntion must return an int

class DotProduct{

    int DotProduct(int[] x, int[] y){
        if ((x.length != y.length)|| x.length == 0)
            return 0;   // by instruction

        int[] dotArray = new int[x.length];
        int dotProduct = 0;
        for (int i=0; i<x.length; i++){
            dotProduct += x[i] * y[i];
        }

        return dotProduct;
    }
}


