import java.io.*;
import java.util.*;

public class Main {
    private static final String PATH = "dictionary.txt";
    private static final Scanner scan = new Scanner(System.in);
    private static List<String> dict = new ArrayList<>();
    private static final int DICT_LEN = 51302;
    private static String gameStatePicture;
    private static String word;
    private static String encodedWord;
    private static int mistakes;

    public static void main(String[] args) {
        createDictionary();
        game();
    }

    public static void game() {
        if (loopMenu()) {
            // выбираем слово из словаря, шифруем его,
            // а также обнуляем количество ошибок
            chooseWordForGame();
            encode();
            mistakes = 0;
            System.out.println("Введите слово полностью, либо попробуйте угадать букву");

            while (true) {
                System.out.println(encodedWord);
                System.out.println("Количество ошибок - " + mistakes);
                break;
            }
        }
    }

    public static String printMenu() {
        System.out.println("Чтобы начать игру, введите [Y]\n" +
                "Чтобы выйти - [E]");
        return scan.nextLine();
    }

    public static boolean loopMenu() {
        while (true) {
            String choice = printMenu();
            if (choice.equals("Y")) {
                return true;
            } else if (choice.equals("E")) {
                return false;
            }
        }
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

    public static void chooseWordForGame() {
        Random rand = new Random();
        word = dict.get(rand.nextInt(DICT_LEN));
    }

    public static void encode() {
        char[] asterisks = new char[word.length()];
        Arrays.fill(asterisks, '*');
        encodedWord = new String(asterisks);
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

    public static String renderGameState() {
        switch (mistakes) {
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
