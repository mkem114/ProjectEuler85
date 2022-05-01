import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        final List<RectangleCandidate> rectangleCandidateList = new ArrayList<>();

        final int targetNumberOfRectangles = 2_000_000;
        final int axisLimit = findAxisLimit(targetNumberOfRectangles);
        for (int x = 1; x < axisLimit; x++) {
            for (int y = 1; y < axisLimit; y++) {
                final int count = countRectangles(x, y);
                rectangleCandidateList.add(new RectangleCandidate(x, y, count));

                if (count > targetNumberOfRectangles) {
                    break;
                }
            }
        }

        rectangleCandidateList.sort(new RectangleCandidateComparator(targetNumberOfRectangles));

        final RectangleCandidate closest = rectangleCandidateList.get(0);
        System.out.printf("The closet candidate was: %s with an area of: %d", closest, closest.x * closest.y);
    }

    static int countRectangles(int x, int y) {
        int rectanglesCount = 0;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j<= y; j++) {
                final int possibleXPositions = x - i + 1;
                final int possibleYPositions = y - j + 1;
                rectanglesCount += possibleXPositions * possibleYPositions;
            }
        }
        return rectanglesCount;
    }

    private static int findAxisLimit(final int targetNumberOfRectangles) {
        int axisLength = 1;
        while (countRectangles(1, axisLength) < targetNumberOfRectangles) {
            axisLength++;
        }
        return axisLength;
    }

    public record RectangleCandidate(int x, int y, int count) { }

    public static class RectangleCandidateComparator implements Comparator<RectangleCandidate> {
        private final int targetNumberOfRectangles;
        public RectangleCandidateComparator(final int targetNumberOfRectangles) {
            this.targetNumberOfRectangles = targetNumberOfRectangles;
        }
        @Override
        public int compare(final RectangleCandidate o1, final RectangleCandidate o2) {
            return abs(targetNumberOfRectangles - o1.count) - abs(targetNumberOfRectangles - o2.count);
        }
    }
}
