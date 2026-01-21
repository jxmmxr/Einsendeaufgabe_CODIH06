package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetectLineTest {

    private final DetectLine sut = new DetectLine();

    private int lineLength(int[][] field) {
        return sut.LineLengthInt(field);
    }

    private static final int INVALID = 0;

    @DisplayName("Grenzwertanalyse: liefert Linienlänge bei gültigem Feld, sonst INVALID")
    @ParameterizedTest(name = "{index}: {0} -> expectedLength={2}")
    @MethodSource("boundaryCases")
    void boundary_value_analysis(String name, int[][] input, int expectedLength) {
        assertEquals(expectedLength, lineLength(input), name);
    }

    static Stream<Object[]> boundaryCases() {
        return Stream.of(
                tc("null array", null, INVALID),
                tc("empty outer array", new int[0][], INVALID),
                tc("row empty", new int[][]{ {} }, INVALID),
                tc("row is null", new int[][]{ null }, INVALID),
                tc("jagged rows", new int[][]{ {0,0,0}, {0,0} }, INVALID),

                tc("1x1 all zero (no line)", new int[][]{ {0} }, INVALID),
                tc("1x1 single one (line length 1)", new int[][]{ {1} }, 1),

                tc("1x2 horizontal full line", new int[][]{ {1,1} }, 2),
                tc("1x3 single one", new int[][]{ {0,1,0} }, 1),
                tc("1x3 gap in line", new int[][]{ {1,0,1} }, INVALID),

                tc("2x1 vertical full line", new int[][]{ {1},{1} }, 2),
                tc("3x1 gap vertical", new int[][]{ {1},{0},{1} }, INVALID),

                tc("2x2 main diagonal", new int[][]{ {1,0},{0,1} }, 2),
                tc("2x2 anti diagonal", new int[][]{ {0,1},{1,0} }, 2),

                tc("contains -1", new int[][]{ {0,-1},{0,0} }, INVALID),
                tc("contains 2",  new int[][]{ {0,2},{0,0} }, INVALID),

                tc("3x3 horizontal middle row", new int[][]{
                        {0,0,0},
                        {1,1,1},
                        {0,0,0}
                }, 3),

                tc("3x3 vertical middle col", new int[][]{
                        {0,1,0},
                        {0,1,0},
                        {0,1,0}
                }, 3),

                tc("3x3 diagonal", new int[][]{
                        {1,0,0},
                        {0,1,0},
                        {0,0,1}
                }, 3),

                tc("3x3 diagonal backwards", new int[][]{
                        {0,0,1},
                        {0,1,0},
                        {1,0,0}
                }, 3),

                tc("6x6 diagonal segment length 3 (like example)", new int[][]{
                        {0,0,0,0,0,0},
                        {0,1,0,0,0,0},
                        {0,0,1,0,0,0},
                        {0,0,0,1,0,0},
                        {0,0,0,0,0,0},
                        {0,0,0,0,0,0}
                }, 3),

                tc("valid diagonal + one extra 1", new int[][]{
                        {1,0,0},
                        {0,1,0},
                        {1,0,1} // extra 1
                }, INVALID),

                tc("cross shape", new int[][]{
                        {0,1,0},
                        {1,1,1},
                        {0,1,0}
                }, INVALID),

                tc("diagonal with one gap", new int[][]{
                        {1,0,0,0},
                        {0,0,0,0}, // gap
                        {0,0,1,0},
                        {0,0,0,1}
                }, INVALID)
        );
    }

    private static Object[] tc(String name, int[][] input, int expectedLength) {
        return new Object[]{name, input, expectedLength};
    }
}
