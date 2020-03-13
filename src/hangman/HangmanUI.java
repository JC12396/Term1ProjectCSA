package hangman;

import java.util.Scanner;
import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;
import static java.lang.Character.toUpperCase;
import static java.nio.file.StandardOpenOption.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class HangmanUI {
    private static String[] wordBank = {"isis", "no", "bruh", "you", "tropical", "ope"};
    private static String word = wordBank[(int) (Math.random() * wordBank.length)];
    private static String hide = new String(new char[word.length()]).replace("\0", "*");
    private static int incorrect = 0;
    private static Scanner input = new Scanner(System.in);
    private static  Path winners = Paths.get("C:\\Users\\jc155706\\Desktop\\Java\\Term1ProjectCSA\\src\\hangman\\HangmanWords.txt");
    private static  FileChannel fcIn = null;
    private static  String delimiter = "|";
    private static  final String NAME_FORMAT = "          ";
    private static  final int NAME_LENGTH = NAME_FORMAT.length();
    private static HangmanInfo counter = new HangmanInfo();

    public static void main(String[] args)
    {
        while (incorrect != 4 && hide.contains("*"))
        {
            System.out.println("Please guess a letter in the word! >> ");
            System.out.println(hide);
            String guess = input.nextLine();
            counter.setTrys(counter.getTrys() + 1);
            checker(guess);
        }
        input.close();
    }
    public static void checker(String guess)
    {
        Scanner input = new Scanner(System.in);
        String newHide = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                newHide += guess.charAt(0);
            } else if (hide.charAt(i) != '*') {
                newHide += word.charAt(i);
            } else {
                newHide += "*";
            }
        }
        if (hide.equals(newHide)) {
            incorrect++;
            bruh();
        } else
        {
            hide = newHide;
        }
        if (hide.equals(word)) {
            System.out.println("Correct! You win! The word was " + word);
            counter.setWins(counter.getWins() + 1);
            System.out.println("It took you " + counter.getTrys() + " try(s)!");
            try
            {
                fcIn = (FileChannel)Files.newByteChannel(winners, CREATE, WRITE);
                System.out.print("What is your name? >> ");
                String name = input.nextLine();
                StringBuilder sb = new StringBuilder(name);
                sb.setLength(NAME_LENGTH);
                String s = name + delimiter + "W's: " + counter.getWins() + delimiter + "L's: " + counter.getLosses();

                byte data[] = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(data);

                fcIn.write(buffer);
                fcIn.close();
            }
            catch (Exception e)
            {
                System.out.println("Error Message: " + e);
            }
        }
    }
    public static void bruh()
    {
        if(incorrect == 1)
        {
            System.out.println("1st one wrong! Good Luck!");
            System.out.println("_________");
            System.out.println("|       |");
            System.out.println("|       O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("____________");
        }
        else if(incorrect == 2)
        {
            System.out.println("Only 2 incorrect answers left");
            System.out.println("_________");
            System.out.println("|       |");
            System.out.println("|       O");
            System.out.println("|       |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("____________");
        }
        else if(incorrect == 3)
        {
            System.out.println("One incorrect guess left!");
            System.out.println("_________");
            System.out.println("|       |");
            System.out.println("|       O");
            System.out.println("|       |");
            System.out.println("|      | | ");
            System.out.println("|");
            System.out.println("____________");
        }
        else if(incorrect == 4)
        {
            System.out.println("You have lost!");
            System.out.println("_________");
            System.out.println("|       |");
            System.out.println("|       O");
            System.out.println("|     --|--");
            System.out.println("|      | | ");
            System.out.println("|");
            System.out.println("____________");
            counter.setLosses(counter.getLosses() + 1);
//            System.exit(-1);
            try
            {
                fcIn = (FileChannel)Files.newByteChannel(winners, CREATE, WRITE);
                System.out.print("What is your name? >> ");
                String name = input.nextLine();
                StringBuilder sb = new StringBuilder(name);
                sb.setLength(NAME_LENGTH);
                String s = name + delimiter + "W's: " + counter.getWins() + delimiter + "L's: " + counter.getLosses();

                byte data[] = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(data);

                fcIn.write(buffer);

                fcIn.close();
            }
            catch (Exception e)
            {
                System.out.println("Error Message: " + e);
            }


        }

    }
}