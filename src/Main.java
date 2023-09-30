import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String PATH = "dictionary.txt";
    private static List<String> dict = new ArrayList<>();
    private static final int DICT_LEN = 51302;
    private static String gameStatePicture;

    public static void main(String[] args) {
//        createDictionary();
    }

    public static void createDictionary() {
        String word;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(PATH));
            while ((word = br.readLine()) != null) {
                dict.add(word);
            }
        } catch (IOException e) {
            System.out.println("Файл не найден или пуст");
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии стрима");
            }
        }
    }

    public static String getWord() {
        Random rand = new Random();
        return dict.get(rand.nextInt(DICT_LEN));
    }

    public char inputCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите букву");
        String str = scanner.nextLine();

        while (true) {
            if (str.length() > 1) {
                System.out.println("Требуется одна буква");
                continue;
            }
            char c = str.toCharArray()[0];

            if (!Character.isAlphabetic(c) || Character.isUpperCase(c)) {
                System.out.println("Ошибка ввода: требуется буква в нижнем регистре");
                str = scanner.nextLine();
                continue;
            }
            return c;
        }
    }

    public static String renderGameState(int gameState) {
        switch (gameState) {
            case 1:
                gameStatePicture = "    |\n   / \\";
                break;
            case 2:
                gameStatePicture = """
                            |
                            |
                            |
                            |
                           / \\\
                        """;
                break;
            case 3:
                gameStatePicture = """
                            |
                            |
                            |
                            |
                            |
                            |
                           / \\\
                        """;
                break;
            case 4:
                gameStatePicture = """
                             _____
                            |     |
                            |
                            |
                            |
                            |
                            |
                           / \\\
                        """;
                break;
            case 5:
                gameStatePicture = """
                             _____
                            |     |
                            |   \\ o /
                            |    ( )
                            |    / \\
                            |
                            |
                            |
                           / \\\
                        """;
        }
        return gameStatePicture;
    }
}
