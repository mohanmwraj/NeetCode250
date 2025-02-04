package mathAndGeometry;

public class rotateImage {
    /*
        * Approach: Brute Force
        *
     */
    public void rotate_1(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rotated[i][j];
            }
        }
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n^2)
     */
    /*
        * Approach: Rotate By Four Cells
        *
     */
    public void rotate_2(int[][] matrix) {
        int l = 0;
        int r = matrix.length - 1;

        while ( l < r ) {
            for(int i = 0; i < r - l; i++) {
                int top = l;
                int bottom = r;
                //save the topleft
                int topLeft = matrix[top][l + i];

                //move bottom left into top left
                matrix[top][l + i] = matrix[bottom - i][l];

                // move bottom right into bottom left
                matrix[bottom - i][l] = matrix[bottom][r - i];

                // move top right into bottom right
                matrix[bottom][r - i] = matrix[top + i][r];

                // move top left into top right
                matrix[top + i][r] = topLeft;

            }
            r--;
            l++;
        }
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    /*
        * Approach: Reverse and Transpose
        *
     */
    public void rotate_3(int[][] matrix) {
        // Reverse the matrix vertically
        reverse(matrix);

        // Transpose the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reverse(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - 1 - i] = temp;
        }
    }

    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
}
