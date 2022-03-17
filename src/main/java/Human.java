
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Pair;

public class Human {

    public Board makeMove(Board input) throws Exception{
        int[] values = new int[4];
        Move[] keys = new Move[4];
        Move move = null;

        keys[0] = Move.LEFT;
        keys[1] = Move.UP;
        keys[2] = Move.DOWN;
        keys[3] = Move.RIGHT;

        values[0] = calculateScore(input.move(Move.LEFT));
        values[1] = calculateScore(input.move(Move.UP));
        values[2] = calculateScore(input.move(Move.DOWN));
        values[3] = calculateScore(input.move(Move.RIGHT));

        int maxValue = 0, indexMax = -1, tempInt = 0;
        Move  tempMove;
        for(int i = 0; i < 4; i++){
            for(int j = i; j < 4; j++){
                if( values[j] >= maxValue){
                    indexMax = j;
                    maxValue = values[j];
                }
            }
            tempInt = values[i];
            tempMove = keys[i];
            values[i] = values[indexMax];
            keys[i] = keys[indexMax];
            values[indexMax] = tempInt;
            keys[indexMax] = tempMove;
            maxValue = 0;
        }

        for(int i = 0; i<4; i++) {
            System.out.println(i + ": " + keys[i] + ": " + values[i]);
        }

        for(int i = 0; i<4; i++) {
            if(!input.move(keys[i]).equals(input)){
                move = keys[i];
                break;
            }
        }



        //Check if move can be done
        //Move move = findMaxMove(left, up, down, right);


        // For each move in MOVE
        //   Generate board from move
        //   Generate Score for Board
        // Return board with the best score
        //
        // Generate Score
        //   If Depth Limit
        //     Return Final Score
        //   Total Score = 0
        //   For every empty square in new board
        //     Generate board with "2" in square
        //       Calculate Score
        //       Total Score += (Score * 0.9)
        //     Generate board with "4" in square
        //       Calculate Score
        //       Total Score += (Score * 0.1)
        //
        // Calculate Score
        //   For each move in MOVE
        //     Generate board from move
        //     Generate score for board
        //   Return the best generated score
        if(move == null){
            throw new Exception("no moves available");
        }
        return input.move(move);
    }


    private Move findMaxMove(int left, int up, int down, int right){
        Move result;
        if(left >= right && left >= up && left >= down){
            result = Move.LEFT;
        }
        else if(right >= left && right >= up && right >= down){
            result = Move.RIGHT;
        }
        else if(up >= right && up >= left && up >= down){
            result = Move.UP;
        }
        else{
            result = Move.DOWN;
        }
        return result;
    }

    private int generateScore(Board board, int depth) {
        if (depth >= 3) {
            int finalScore = calculateFinalScore(board);
            return finalScore;
        }

        return board.emptyCells().stream()
                .parallel()
                .flatMap(cell -> Stream.of(new Pair<>(cell, 2), new Pair<>(cell, 4)))
                .mapToInt(move -> {
                    Board newBoard = board.placeTile(move.getFirst(), move.getSecond());
                    int boardScore = calculateScore(newBoard);
                    int calculatedScore = (int) (boardScore * (move.getSecond() == 2 ? 0.9 : 0.1));
                    return calculatedScore;
                })
                .sum();
    }

    private int calculateScore(Board board) {
        //H = A x E - B x D - C x P   | A=4096, B=10 and C=10. check max tile if is in corner add high numbers in same edge
        int X=4096, Y=10, Z=10, W = 1000, K = 1000;
        int E = board.emptyCells().size();
        int D = board.numberOfDiffentNeighbouringNumbers();
        int P = board.sumOfDistancesToClosestBorder();
        int C = board.isMaxTileInCorner();
        int H = board.isHighNumbersInSameEdge();

        return  X * E - Y * D - Z * P + W * C + H * K;
    }

    private int calculateFinalScore(Board board) {
        List<List<Integer>> rowsToScore = new ArrayList<>();
        for (int i = 0; i < board.getSize(); ++i) {
            List<Integer> row = new ArrayList<>();
            List<Integer> col = new ArrayList<>();

            for (int j = 0; j < board.getSize(); ++j) {
                row.add(board.getCell(new Cell(i, j)));
                col.add(board.getCell(new Cell(j, i)));
            }

            rowsToScore.add(row);
            rowsToScore.add(col);
        }

        return rowsToScore.stream()
                .parallel()
                .mapToInt(row -> {
                    List<Integer> preMerged = row.stream()
                            .filter(value -> value != 0)
                            .collect(Collectors.toList());

                    int numMerges = 0;
                    int monotonicityLeft = 0;
                    int monotonicityRight = 0;
                    for (int i = 0; i < preMerged.size() - 1; ++i) {
                        Integer first = preMerged.get(i);
                        Integer second = preMerged.get(i + 1);
                        if (first.equals(second)) {
                            ++numMerges;
                        } else if (first > second) {
                            monotonicityLeft += first - second;
                        } else {
                            monotonicityRight += second - first;
                        }
                    }

                    int score = 1000;
                    score += 250 * row.stream().filter(value -> value == 0).count();
                    score += 750 * numMerges;
                    score -= 10 * row.stream().mapToInt(value -> value).sum();
                    score -= 50 * Math.min(monotonicityLeft, monotonicityRight);
                    return score;
                })
                .sum();
    }
}