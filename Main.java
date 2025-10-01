import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space:");
        String[] inputs = sc.nextLine().split(" ");

        for (String s : inputs) {
            Integer num = Integer.parseInt(s);
            numbers.add(num);
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }

        System.out.println("Total sum: " + sum);
        sc.close();
    }
}


