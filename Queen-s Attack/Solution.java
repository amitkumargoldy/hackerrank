import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int[][] blockers = initializeBlockers(n, r_q, c_q);
        for (int i = 0; i < obstacles.length; i++) {
            int r_o = obstacles[i][0];
            int c_o = obstacles[i][1];
            if (r_o == r_q) { // horizontal
                if (c_o < c_q) { // left
                    if (blockers[0][0] == 0 || c_o > blockers[0][1]) {
                        blockers[0][0] = r_o;
                        blockers[0][1] = c_o;
                    }
                } else { // right
                    if (blockers[4][0] == 0 || c_o < blockers[4][1]) {
                        blockers[4][0] = r_o;
                        blockers[4][1] = c_o;
                    }
                }
            } else if (c_o == c_q) { // vertical
                if (r_o < r_q) { // bottom
                    if (blockers[6][0] == 0 || r_o > blockers[6][0]) {
                        blockers[6][0] = r_o;
                        blockers[6][1] = c_o;
                    }
                } else { // top
                    if (blockers[2][0] == 0 || r_o < blockers[2][0]) {
                        blockers[2][0] = r_o;
                        blockers[2][1] = c_o;
                    }
                }
            } else if (r_o - r_q == c_o - c_q) { // left_bottom to right_top diagonal
                if (r_o > r_q) { // right top
                    if (blockers[3][0] == 0 || r_o < blockers[3][0]) {
                        blockers[3][0] = r_o;
                        blockers[3][1] = c_o;
                    }
                } else { // left bottom
                    if (blockers[7][0] == 0 || r_o > blockers[7][0]) {
                        blockers[7][0] = r_o;
                        blockers[7][1] = c_o;
                    }
                }
            } else if (Math.abs(r_o - r_q) == Math.abs(c_o - c_q)) { // left_top to right_bottom diagonal
                if (r_o > r_q) { // left top
                    if (blockers[1][0] == 0 || r_o < blockers[1][0]) {
                        blockers[1][0] = r_o;
                        blockers[1][1] = c_o;
                    }
                } else { // right bottom
                    if (blockers[5][0] == 0 || r_o > blockers[5][0]) {
                        blockers[5][0] = r_o;
                        blockers[5][1] = c_o;
                    }
                }
            }
        }
        return countOpenMoves(blockers);
    }

    private static int countOpenMoves(int[][] blockers) {
        int total = 0;
        // add horizontal options
        total += blockers[4][1] - blockers[0][1] - 2;
        // add vertical options
        total += blockers[2][0] - blockers[6][0] - 2;
        // add l_b to r_t options
        total += blockers[3][0] - blockers[7][0] - 2;
        // add l_t to r_b options
        total += blockers[1][0] - blockers[5][0] - 2;

        return total;
    }

    private static int[][] initializeBlockers(int n, int r_q, int c_q) {
        int[][] blockers = new int[8][2];
        // [0] left
        blockers[0][0] = r_q; blockers[0][1] = 0;
        // [1] left_top
        int min = Math.min(n - r_q, c_q - 1);
        blockers[1][0] = r_q + min + 1; blockers[1][1] = c_q - min - 1;
        // [2] top
        blockers[2][0] = n + 1; blockers[2][1] = c_q;
        // [3] right_top
        min = Math.min(n - r_q, n - c_q);
        blockers[3][0] = r_q + min + 1; blockers[3][1] = c_q + min + 1;
        // [4] right
        blockers[4][0] = r_q; blockers[4][1] = n + 1;
        // [5] right_bottom
        min = Math.min(r_q - 1, n - c_q);
        blockers[5][0] = r_q - min - 1; blockers[5][1] = c_q + min + 1;
        // [6] bottom
        blockers[6][0] = 0; blockers[6][1] = c_q;
        // [7] left_bottom
        min = Math.min(r_q - 1, c_q - 1);
        blockers[7][0] = r_q - min - 1; blockers[7][1] = c_q - min - 1;

        return blockers;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
