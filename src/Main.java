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
                String guess = askValidInput();
                if (checkIfUserIsRight(guess)) {
                    openCharacter(guess);
                }

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
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("E")) {
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

    public static boolean checkIfUserIsRight(String str) {
        return word.contains(str);
    }

    public static void openCharacter(String str) {
        if (str.equals(word))
            encodedWord = word;
        else if (str.length() != word.length()) {
            Set<Integer> idxs = new HashSet<>();
            char c = word.toCharArray()[0];
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    idxs.add(i);
                }
            }
            StringBuilder newEncoded = new StringBuilder();
            for (int i = 0; i < encodedWord.length(); i++) {

                for (Integer idx : idxs) {
                    if (i == idx) {
                        newEncoded.append(c);
                    }
                }
            }
        }
    }

    public static String askValidInput() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if (!(str.length() == 1 || str.length() == word.length())) {
                System.out.println("Требуется одна буква либо все слово полностью");
                continue;
            }
            char[] chars = str.toCharArray();
            if (checkStrValidity(chars))
                return String.valueOf(chars);
        }
    }

    public static boolean checkStrValidity(char[] chars) {
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                System.out.println("Ошибка ввода: буква / слово должны быть в нижнем регистре");
                return false;
            }
            if (!Character.isAlphabetic(c)) {
                System.out.println("Ошибка ввода: требуется буква / слово");
                return false;
            }
        }
        return true;
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
