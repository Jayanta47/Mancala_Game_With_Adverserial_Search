import GameBoard.MancalaBoard;
import Heuristics.*;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void AB_search_test() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        Player P1 = new Player(new Heuristic1(), 1, mb);
        Player P2 = new Player(new Heuristic2(), 2, mb);
        P1.setMaxDepth(6);
        P2.setMaxDepth(14);
//        P2.performMinimax();
        P1.turnOnIDS();
        P2.turnOnIDS();
        while(!mb.isEndOfGame()) {
            if (mb.current_turn%2==0) {
                P1.makeMove();
            }
            else {
                P2.makeMove();
            }

            System.out.println(mb);
        }
    }

    public static void singlePlayerTest() {
        MancalaBoard mb = new MancalaBoard();
        System.out.println(mb);
        Player P1 = new Player(new Heuristic1(), 1, mb);
        Scanner scn = new Scanner(System.in);
        while(!mb.isEndOfGame()) {
            if (mb.current_turn%2==0) {
                P1.makeMove();
            }
            else {
                int move = scn.nextInt();
                mb.clickSquare(move);
            }
            System.out.println(mb);
        }
    }

    public static void Player_test() {
        MancalaBoard mb = new MancalaBoard();
        Player P1 = new Player(new Heuristic1(), 1, mb);
        P1.turnOnTestMode();
        P1.makeMove();

    }

    public static void collectData() throws IOException {

        WriteCSV wcsv;
        wcsv = new WriteCSV("data.csv");


        String header = ",,,,H1,,,,,,H2,,,,,,H3,,,,,,H4,,,,,,H5,,,,,,H6,,";
        String header2 = "depth_1,depth2,alpha-beta,IDS,Result,";

        String h_temp=new String(header2);

        for(int i=0;i<5;i++) {
            h_temp+=(","+header2);
        }
        header2 = ",,"+h_temp;
        ArrayList<String> a = new ArrayList<>();
        a.add(header);
        a.add(header2);
        wcsv.saveData(a, false);

        ArrayList<IHeuristic> heuristics = new ArrayList<>();
        heuristics.add(new Heuristic1());
        heuristics.add(new Heuristic2());
        heuristics.add(new Heuristic3());
        heuristics.add(new Heuristic4());
        heuristics.add(new Heuristic5());
        heuristics.add(new Heuristic6());

        int[] depthP1 = new int[] {6,8,10,14};
        int[] depthP2 = new int[] {14,12,10,6};

        for(int i=0;i<6;i++) {
            ArrayList<String> data = new ArrayList();
            for(int d=0;d<4;d++){
                StringBuilder sb = new StringBuilder();
                sb.append("H").append(i + 1).append(",,");


                    for(int j=0;j<6;j++){

                        if (i!=j) {
                            // uncomment this line during simulation
//                            System.out.println("Game Played Among: H"+(i+1)+" vs H"+(j+1) + " Depth: "+depthP1[d] + " - "+depthP2[d]);
                            MancalaBoard mb = new MancalaBoard();
                            Player P1 = new Player(heuristics.get(i), 1, mb);
                            Player P2 = new Player(heuristics.get(j), 2, mb);
                            P1.setMaxDepth(depthP1[d]);
                            P2.setMaxDepth(depthP2[d]);
                            sb.append(depthP1[d]).append(",");
                            sb.append(depthP2[d]).append(",");
                            while(!mb.isEndOfGame()) {
                                if (mb.current_turn%2==0) {
                                    P1.makeMove();
                                }
                                else {
                                    P2.makeMove();
                                }
                            }
                            sb.append(mb.getP1Points()).append("-").append(mb.getP2Points()).append(",");

                            mb = new MancalaBoard();
                            P1 = new Player(heuristics.get(i), 1, mb);
                            P2 = new Player(heuristics.get(j), 2, mb);
                            P1.turnOnIDS();
                            P2.turnOnIDS();

                            while(!mb.isEndOfGame()) {
                                if (mb.current_turn%2==0) {
                                    P1.makeMove();
                                }
                                else {
                                    P2.makeMove();
                                }
                            }
                            sb.append(mb.getP1Points()).append("-").append(mb.getP2Points()).append(",");
                            if (mb.getP1Points()>mb.getP2Points()) {
                                sb.append("H").append(i+1).append(" wins").append(",,");
                            }
                            else if (mb.getP1Points()==mb.getP2Points()){
                                sb.append("Draw").append(",,");
                            }
                            else {
                                sb.append("H").append(j+1).append(" wins").append(",,");
                            }


                        }
                        else {
                            sb.append(",,,,,,");
                        }

                    }
                    data.add(sb.toString());

                }
            wcsv.saveData(data, false);


            }
        wcsv.endCurrentFile();
        }



    public static void testCSV() throws IOException {
        WriteCSV wcsv = new WriteCSV("test.csv");
        String header = new String(",,,,H1,,,,,,H2,,,,,,H3,,,,,,H4,,,,,,H5,,,,,,H6,,");
        String header2 = new String("depth_1,depth2,alpha-beta,IDS,Result,");
        String h_temp=new String(header2);
        for(int i=0;i<5;i++) {
            h_temp+=(","+header2);
        }
        header2 = ",,"+h_temp;
        ArrayList<String> a = new ArrayList<>();
        a.add(header);
        a.add(header2);
        wcsv.saveData(a, false);
        wcsv.endCurrentFile();
    }

    public static void main(String[] args) {
//        BoardCheck();
//        AB_search_test();
//        Player_test();
//        singlePlayerTest();
//        try {
//            testCSV();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            collectData();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
