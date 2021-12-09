import GameBoard.MancalaBoard;

public class test {
    public static void BoardCheck() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        mb.clickSquare(1);
        System.out.println(mb);
        //p1
        mb.clickSquare(3);
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        //p1
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(3);
        System.out.println(mb);
        //p1
        mb.clickSquare(1);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        //p1
        mb.clickSquare(2);
        System.out.println(mb);
        //p2
        mb.clickSquare(2);
        System.out.println(mb);
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(4);
        System.out.println(mb);
        //p2
        mb.clickSquare(1);
        System.out.println(mb);
        // 20
        // 8 1 8 2 0 0
        // 0 0 4 0 0 0
        // 5
        //p1
        mb.clickSquare(3);
        System.out.println(mb);
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(5);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        // 20
        // 9 0 8 2 0 0
        // 0 0 0 1 0 1
        // 7
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        mb.clickSquare(4);
        System.out.println(mb);
        //p2
        mb.clickSquare(3);
        System.out.println(mb);
        //p1
        mb.clickSquare(5);
        System.out.println(mb);
        //p2
        mb.clickSquare(5);
        System.out.println(mb);
        //p1
        mb.clickSquare(6);
        System.out.println(mb);
        //eog
        mb.clickSquare(5);
        System.out.println(mb);

    }
    public static void main(String[] args) {
        BoardCheck();
    }
}
