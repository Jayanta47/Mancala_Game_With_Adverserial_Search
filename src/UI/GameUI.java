package UI;

import GameBoard.MancalaBoard;
import Heuristics.Heuristic1;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class GameUI {

    static MancalaBoard mb;
    static Player AI_player;

    static String player1_type;
    static String player2_type;
    static String message;
    static boolean isSinglePlayer;
    static boolean isFirstPlayer;

    static JLabel j1;
    static JLabel j2;
    static JLabel msg;
    static JLabel turn;

    static JTextField p2MancalaBox;
    static JTextField p1MancalaBox;


    static JButton P1_1;

    static JButton P1_2;

    static JButton P1_3;

    static JButton P1_4;

    static JButton P1_5;

    static JButton P1_6;

    static JButton P2_1;

    static JButton P2_2;

    static JButton P2_3;

    static JButton P2_4;

    static JButton P2_5;

    static JButton P2_6;

    GameUI() {
        P1_1 = new JButton();

        P1_2 = new JButton();

        P1_3 = new JButton();

        P1_4 = new JButton();

        P1_5 = new JButton();

        P1_6 = new JButton();

        P2_1 = new JButton();

        P2_2 = new JButton();

        P2_3 = new JButton();

        P2_4 = new JButton();

        P2_5 = new JButton();

        P2_6 = new JButton();

        p2MancalaBox = new JTextField();
        p1MancalaBox = new JTextField();


        setButtonVal();

        P1_1.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(1, true) > 0) {
                mb.clickSquare(1);
                setButtonVal();
                setInfoCenter();
                if (isSinglePlayer) makeAIPlay();
            }


        });

        P1_2.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(2, true) > 0) {
                mb.clickSquare(2);
                setButtonVal();
                setInfoCenter();
                if (isSinglePlayer) makeAIPlay();
            }
        });

        P1_3.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(3, true) > 0) {
                mb.clickSquare(3);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }


        });

        P1_4.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(4, true) > 0) {
                mb.clickSquare(4);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }


        });

        P1_5.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(5, true) > 0) {
                mb.clickSquare(5);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }

        });

        P1_6.addActionListener(e -> {
            if ((!isSinglePlayer || isFirstPlayer) && mb.current_turn % 2 == 0 && mb.getBeadsAt(6, true) > 0) {
                mb.clickSquare(6);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }

        });


        // player 2 actions

        P2_1.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(6, false) > 0) {
                mb.clickSquare(6);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }

        });

        P2_2.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(5, false) > 0) {
                mb.clickSquare(5);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }

        });

        P2_3.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(4, false) > 0) {
                mb.clickSquare(4);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }
        });

        P2_4.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(3, false) > 0) {
                mb.clickSquare(3);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }
        });

        P2_5.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(2, false) > 0) {
                mb.clickSquare(2);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }
        });

        P2_6.addActionListener(e -> {
            if ((!isSinglePlayer || !isFirstPlayer) && mb.current_turn % 2 == 1 && mb.getBeadsAt(1, false) > 0) {
                mb.clickSquare(1);
                setButtonVal();
                setInfoCenter();
                if(isSinglePlayer) {
                    makeAIPlay();
                }
            }
        });

        JPanel player2Bttn = new JPanel();
        player2Bttn.setLayout(new BoxLayout(player2Bttn, BoxLayout.LINE_AXIS));

        player2Bttn.add(P2_1);
        player2Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player2Bttn.add(P2_2);
        player2Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player2Bttn.add(P2_3);
        player2Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player2Bttn.add(P2_4);
        player2Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player2Bttn.add(P2_5);
        player2Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player2Bttn.add(P2_6);

        // setting up the mancala boxes for both the players
        JPanel mancalaPBoxes = new JPanel();
        mancalaPBoxes.setLayout(new BoxLayout(mancalaPBoxes, BoxLayout.LINE_AXIS));

        p2MancalaBox.setBackground(Color.lightGray);
        p2MancalaBox.setBounds(50, 50, 50, 50);
        p2MancalaBox.setHorizontalAlignment(JTextField.CENTER);
        p2MancalaBox.setEditable(false);

        p1MancalaBox.setBackground(Color.lightGray);
        p1MancalaBox.setBounds(50, 50, 50, 50);
        p1MancalaBox.setHorizontalAlignment(JTextField.CENTER);
        p1MancalaBox.setEditable(false);

        mancalaPBoxes.add(p2MancalaBox);
        mancalaPBoxes.add(Box.createRigidArea(new Dimension(370, 50)));
        mancalaPBoxes.add(p1MancalaBox);

        // setting up buttons for player 1

        JPanel player1Bttn = new JPanel();
        player1Bttn.setLayout(new BoxLayout(player1Bttn, BoxLayout.LINE_AXIS));

        player1Bttn.add(P1_1);
        player1Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player1Bttn.add(P1_2);
        player1Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player1Bttn.add(P1_3);
        player1Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player1Bttn.add(P1_4);
        player1Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player1Bttn.add(P1_5);
        player1Bttn.add(Box.createRigidArea(new Dimension(40, 40)));

        player1Bttn.add(P1_6);


        JLabel heading = new JLabel("     Mancala      ");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);

        JPanel headingBox = new JPanel();
        headingBox.setLayout(new BoxLayout(headingBox, BoxLayout.LINE_AXIS));
        headingBox.setBackground(Color.BLACK);
        headingBox.add(heading);

        JFrame mancalaBoard = new JFrame("Mancala Board");
//        mancalaBoard.setLayout(new FlowLayout());
        mancalaBoard.setLayout(new FlowLayout());
        mancalaBoard.setSize(600, 500);
        mancalaBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        mainContainer.add(headingBox);
        mainContainer.add(Box.createRigidArea(new Dimension(100, 50)));
        mainContainer.add(getInfoCenter());
        mainContainer.add(Box.createRigidArea(new Dimension(100, 10)));
        mainContainer.add(msg);
        mainContainer.add(Box.createRigidArea(new Dimension(100, 10)));
        mainContainer.add(player2Bttn);
        mainContainer.add(Box.createRigidArea(new Dimension(100, 35)));
        mainContainer.add(mancalaPBoxes);
        mainContainer.add(Box.createRigidArea(new Dimension(100, 35)));
        mainContainer.add(player1Bttn);


        setInfoCenter();

        mancalaBoard.add(mainContainer);
        mancalaBoard.setLocationRelativeTo(null);
        mancalaBoard.setVisible(true);
    }

    public static JPanel getInfoCenter() {

        JPanel infoC = new JPanel();
        infoC.setLayout(new BoxLayout(infoC, BoxLayout.LINE_AXIS));
        infoC.setBackground(Color.GRAY);
        JPanel playerIC = new JPanel();
        playerIC.setLayout(new BoxLayout(playerIC, BoxLayout.PAGE_AXIS));
        playerIC.setBackground(Color.GRAY);

        j1 = new JLabel("Player 1: " + player1_type);
        j1.setFont(new Font("SansSerif", Font.BOLD, 18));
        j1.setForeground(Color.WHITE);

        j2 = new JLabel("Player 2: " + player2_type);
        j2.setFont(new Font("SansSerif", Font.BOLD, 18));
        j2.setForeground(Color.WHITE);

        playerIC.add(j1);
        playerIC.add(j2);

        JPanel msgIC = new JPanel();
        msgIC.setLayout(new BoxLayout(msgIC, BoxLayout.PAGE_AXIS));
        msgIC.setBackground(Color.GRAY);

        message = "MESSAGE: PLAY BEGIN!";
        msg = new JLabel(message);
        msg.setFont(new Font("SansSerif", Font.BOLD, 18));
//        msg.setForeground(Color.WHITE);
        turn = new JLabel("TURN: Player 1"); // implement if else
        turn.setFont(new Font("SansSerif", Font.BOLD, 18));
        turn.setForeground(Color.WHITE);


//        msgIC.add(msg);
        msgIC.add(turn);

        infoC.add(playerIC);
        infoC.add(Box.createRigidArea(new Dimension(50, 20)));
        infoC.add(msgIC);


        return infoC;
    }

    public static void setButtonVal() {
        P1_1.setText(Integer.toString(mb.getBeadsAt(1, true)));
        P1_2.setText(Integer.toString(mb.getBeadsAt(2, true)));
        P1_3.setText(Integer.toString(mb.getBeadsAt(3, true)));
        P1_4.setText(Integer.toString(mb.getBeadsAt(4, true)));
        P1_5.setText(Integer.toString(mb.getBeadsAt(5, true)));
        P1_6.setText(Integer.toString(mb.getBeadsAt(6, true)));

        P2_6.setText(Integer.toString(mb.getBeadsAt(1, false)));
        P2_5.setText(Integer.toString(mb.getBeadsAt(2, false)));
        P2_4.setText(Integer.toString(mb.getBeadsAt(3, false)));
        P2_3.setText(Integer.toString(mb.getBeadsAt(4, false)));
        P2_2.setText(Integer.toString(mb.getBeadsAt(5, false)));
        P2_1.setText(Integer.toString(mb.getBeadsAt(6, false)));

        p1MancalaBox.setText(Integer.toString(mb.getP1Points()));
        p2MancalaBox.setText(Integer.toString(mb.getP2Points()));
    }

    public static void setInfoCenter() {
        if (mb.current_turn % 2 == 0) {
            turn.setText("TURN: Player 1");
        } else {
            turn.setText("TURN: Player 2");
        }

        if (mb.isEndOfGame()) {
            msg.setText("MESSAGE: END OF GAME");
        } else if (mb.isCurrentFT()) {
            msg.setText("MESSAGE: FREE TURN");
        } else {
            msg.setText("MESSAGE: ");
        }
    }

    public static void makeAIPlay() {
        int turn = mb.current_turn;

        if (turn!= AI_player.playerNo-1) {
//            System.out.println("returning");
            return;
        }

        Thread t = new Thread(() -> {
            while(turn==mb.current_turn && !mb.isEndOfGame()) {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int move = AI_player.makeMove();
                setButtonVal();
                msg.setText("MESSAGE: AI PLAYED " + move);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setInfoCenter();
            }
        });

//        while(turn==mb.current_turn && !mb.isEndOfGame()) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int move = AI_player.makeMove();
//            setButtonVal();
//            msg.setText("MESSAGE: AI PLAYED " + move);
//            setInfoCenter();
//        }

        t.start();
    }

    public static void main(String[] args) {

        mb = new MancalaBoard();
        System.out.println("Welcome To Mancala");
        System.out.println("==========================");
        System.out.println("Select Mode:\n1. Single Player\n2. Multi player");

        Scanner scn = new Scanner(System.in);

        int ch = scn.nextInt();

        if (ch == 1) {
            isSinglePlayer = true;
            int AI_playerNo;
            System.out.println("Play As:\n1. First player\n2. Second Player");
            AI_playerNo = 3-scn.nextInt();
            if (!(AI_playerNo == 1 || AI_playerNo == 2)) {
                System.out.println("Invalid Choice");
                exit(0);
            }
            if (AI_playerNo == 2) {
                isFirstPlayer = true;
                player1_type="HUMAN";
                player2_type="BOT";
            }
            else {
                isFirstPlayer = false;
                player1_type="BOT";
                player2_type="HUMAN";
            }
            AI_player = new Player(new Heuristic1(), AI_playerNo, mb);
        } else {
            isSinglePlayer = false;
            isFirstPlayer = true;
            player2_type="HUMAN";
            player1_type="HUMAN";
        }

        SwingUtilities.invokeLater(() -> {
            new GameUI();
            if (isSinglePlayer && !isFirstPlayer) makeAIPlay();

        });


    }
}
