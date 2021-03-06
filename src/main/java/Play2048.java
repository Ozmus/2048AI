import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Play2048 extends JFrame{
    private static final int SIZE = 4;
    private static final int INITIAL_NUMBERS = 2;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    public Play2048(int rows, int columns, Board board)
    {
        super();
        setSize(WIDTH, HEIGHT);
        setTitle("GridLayout Demonstration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(rows, columns ));
        /*String label =
        if(board.isEmpty(new Cell(0,0))){

        }
        else{
            board.getCell(new Cell(0,0))
        }*/
        JButton label1 = new JButton(board.getCell(new Cell(0,0))== 0 ? "" : Integer.toString(board.getCell(new Cell(0,0))));
        add(label1);
        setColor(board.getCell(new Cell(0,0)), label1);
        JButton label2 = new JButton(board.getCell(new Cell(1,0))== 0 ? "" :Integer.toString(board.getCell(new Cell(1,0))));
        add(label2);
        setColor(board.getCell(new Cell(1,0)), label2);
        JButton label3 = new JButton(board.getCell(new Cell(2,0))== 0 ? "" : Integer.toString(board.getCell(new Cell(2,0))));
        add(label3);
        setColor(board.getCell(new Cell(2,0)), label3);
        JButton label4 = new JButton(board.getCell(new Cell(3,0))== 0 ? "" : Integer.toString(board.getCell(new Cell(3,0))));
        add(label4);
        setColor(board.getCell(new Cell(3,0)), label4);
        JButton label5 = new JButton(board.getCell(new Cell(0,1))== 0 ? "" : Integer.toString(board.getCell(new Cell(0,1))));
        add(label5);
        setColor(board.getCell(new Cell(0,1)), label5);
        JButton label6 = new JButton(board.getCell(new Cell(1,1))== 0 ? "" : Integer.toString(board.getCell(new Cell(1,1))));
        add(label6);
        setColor(board.getCell(new Cell(1,1)), label6);
        JButton label7 = new JButton(board.getCell(new Cell(2,1))== 0 ? "" : Integer.toString(board.getCell(new Cell(2,1))));
        add(label7);
        setColor(board.getCell(new Cell(2,1)), label7);
        JButton label8 = new JButton(board.getCell(new Cell(3,1))== 0 ? "" : Integer.toString(board.getCell(new Cell(3,1))));
        add(label8);
        setColor(board.getCell(new Cell(3,1)), label8);
        JButton label9 = new JButton(board.getCell(new Cell(0,2))== 0 ? "" : Integer.toString(board.getCell(new Cell(0,2))));
        add(label9);
        setColor(board.getCell(new Cell(0,2)), label9);
        JButton label10 = new JButton(board.getCell(new Cell(1,2))== 0 ? "" : Integer.toString(board.getCell(new Cell(1,2))));
        add(label10);
        setColor(board.getCell(new Cell(1,2)), label10);
        JButton label11 = new JButton(board.getCell(new Cell(2,2))== 0 ? "" : Integer.toString(board.getCell(new Cell(2,2))));
        add(label11);
        setColor(board.getCell(new Cell(2,2)), label11);
        JButton label12 = new JButton(board.getCell(new Cell(3,2))== 0 ? "" : Integer.toString(board.getCell(new Cell(3,2))));
        add(label12);
        setColor(board.getCell(new Cell(3,2)), label12);
        JButton label13 = new JButton(board.getCell(new Cell(0,3))== 0 ? "" : Integer.toString(board.getCell(new Cell(0,3))));
        add(label13);
        setColor(board.getCell(new Cell(0,3)), label13);
        JButton label14 = new JButton(board.getCell(new Cell(1,3))== 0 ? "" : Integer.toString(board.getCell(new Cell(1,3))));
        add(label14);
        setColor(board.getCell(new Cell(1,3)), label14);
        JButton label15 = new JButton(board.getCell(new Cell(2,3))== 0 ? "" : Integer.toString(board.getCell(new Cell(2,3))));
        add(label15);
        setColor(board.getCell(new Cell(2,3)), label15);
        JButton label16 = new JButton(board.getCell(new Cell(3,3))== 0 ? "" : Integer.toString(board.getCell(new Cell(3,3))));
        add(label16);
        setColor(board.getCell(new Cell(3,3)), label16);
    }

    private void setColor(int num, JButton button){
        if(num == 0){
            button.setBackground(Color.lightGray);
        }
        else if(num == 2){
            button.setBackground(Color.white);
        }
        else if(num == 4){
            button.setBackground(Color.gray);
        }
        else if(num == 8){
            button.setBackground(Color.yellow);
        }
        else if(num == 16){
            button.setBackground(Color.orange);
        }
        else if(num == 32){
            button.setBackground(Color.PINK);
        }
        else if(num == 64){
            button.setBackground(Color.RED);
        }
        else if(num == 128){
            button.setBackground(Color.yellow);
        }
        else if(num == 256){
            button.setBackground(Color.yellow);
        }else if(num == 512){
            button.setBackground(Color.yellow);
        }
        else if(num == 1024){
            button.setBackground(Color.orange);
        }
        else if(num == 2048){
            button.setBackground(Color.orange);
        }
        else{
            button.setBackground(Color.MAGENTA);
        }

    }

    public static void main(String[] args) {
        boolean stop = false;

        // The board and players
        Board board = new Board(SIZE);
        Computer computer = new Computer();
        Human human = new Human();

        Map<Integer, Integer> result = new HashMap<>();
        //for(int k=0; k < 1000; k++) {
            // The computer has two moves first
            board = new Board(SIZE);
            //System.out.println("Setup");
            //System.out.println("=====");
            for (int i = 0; i < INITIAL_NUMBERS; ++i) {
                board = computer.makeMove(board);
            }

            //printBoard(board);
            do {
                try {
                    board = human.makeMove(board);
                } catch (Exception e) {
                    //System.out.println(e.getMessage());
                    stop = true;
                    break;
                }
                //System.out.println("\nHuman move");
                //System.out.println("==========");
                //printBoard(board);
                printBoardGUI(board);

            try {
                Thread.sleep(50);
            }
            catch (Exception e){

            }

                board = computer.makeMove(board);
                //System.out.println("\nComputer move");
                //System.out.println("=============");
                //printBoard(board);
                printBoardGUI(board);
           try {
                Thread.sleep(250);
            }
            catch (Exception e){

            }
            } while (!stop); // ADD ALSO NO available move
            printBoardGUI(board);
            stop = false;
            try {
                result.put(board.maxTile(), result.get(board.maxTile()) + 1);
            }
            catch (Exception e){
                result.put(board.maxTile(), 1);
            }
        //}
        //System.out.println("\nFinal Score: " + board.getScore());
        System.out.println(result);
    }

    private static void printBoard(Board board) {
        StringBuilder topLines = new StringBuilder();
        StringBuilder midLines = new StringBuilder();
        for (int x = 0; x < board.getSize(); ++x) {
            topLines.append("+--------");
            midLines.append("|        ");
        }
        topLines.append("+");
        midLines.append("|");


        for (int y = 0; y < board.getSize(); ++y) {
            System.out.println(topLines);
            System.out.println(midLines);
            for (int x = 0; x < board.getSize(); ++x) {
                Cell cell = new Cell(x, y);
                System.out.print("|");
                if (board.isEmpty(cell)) {
                    System.out.print("        ");
                } else {
                    StringBuilder output = new StringBuilder(Integer.toString(board.getCell(cell)));
                    while (output.length() < 8) {
                        output.append(" ");
                        if (output.length() < 8) {
                            output.insert(0, " ");
                        }
                    }
                    System.out.print(output);
                }
            }
            System.out.println("|");
            System.out.println(midLines);
        }
        System.out.println(topLines);
        System.out.println("Score: " + board.getScore());
    }

    private static void printBoardGUI(Board board) {
        Play2048 gui = new Play2048(4, 4, board);
        gui.setVisible(true);
    }

}