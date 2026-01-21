package org.example;

public class DetectLine {
    public int LineLengthInt (int [][] field){
        if (field == null || field.length == 0) return 0;
        if (field[0] == null || field[0].length == 0) return 0;
        int xlength = field.length;
        int ylength = field[0].length;
        int maxlangth = Math.max(xlength, ylength);

        int[][] dirs = {{1,0},{0,1},{1,-1},{1,1}};

        for (int r = 0; r < xlength; r++){
            for (int c = 0; c < ylength; c++){
                if (field[r][c] == 0) continue;
                int maxhits = 0;
                for (int[] dir : dirs) {
                    int hits = 1;
                    int offR = 0;
                    int offC = 0;

                    for (int t = 0; t < maxlangth; t++) {
                        offR += dir[0];
                        offC += dir[1];

                        int rr = r + offR;
                        int cc = c + offC;

                        if (rr < 0 || rr >= xlength || cc < 0 || cc >= ylength) break;
                        if (field[rr][cc] == 0) break;

                        hits++;
                    }
                    maxhits = Math.max(maxhits, hits);
                }
                if (maxhits > 0)
                    return maxhits;
            }
        }
        return 0;
    }
}
