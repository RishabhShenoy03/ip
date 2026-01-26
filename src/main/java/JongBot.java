import java.util.Scanner;

public class JongBot {
    public static void newline() { System.out.println(); }

    public static void printList(Task[] list, int numLines){
        newline();
        for (int i = 0; i < numLines; i ++){
            if (list[i].description.equals("list")){
                continue;
            }
            System.out.println( i+1 + ":[" + list[i].getStatusIcon() + "] "  + list[i].description);
        }
        newline();
    }

    public static void main(String[] args) {
        newline();
        System.out.println("Hello! My name is Jongbot");
        System.out.println("How may I help you?");
        newline();

        String input;
        Task[] list = new Task[100];
        Scanner in = new Scanner(System.in);

        int numLines = 0;
        while (true) {
            input = in.nextLine();

            if (input.equals("bye")){
                newline();
                System.out.println("Bye bye! See you soon!");
                break;
            }

            if (input.equals("list")){
                if (numLines == 0){
                    newline();
                    System.out.println("List is empty, insert some tasks!");
                    newline();
                    continue;
                }
                printList(list, numLines);
                continue;
            }

            if (input.startsWith("mark")){
                int taskIndex = Integer.parseInt(input.substring(5));
                newline();
                if (taskIndex > numLines || taskIndex < 1){
                    System.out.println("This task does not exist, use 'list' first to check task numbers");
                } else {
                    list[taskIndex - 1].markTask();
                    System.out.println("Task " + taskIndex + " has been marked");
                }
                newline();
                continue;
            }
            if (input.startsWith("unmark")){
                int taskIndex = Integer.parseInt(input.substring(7));
                newline();
                if (taskIndex > numLines || taskIndex < 1){
                    System.out.println("This task does not exist, use 'list' first to check task numbers");
                } else {
                    list[taskIndex - 1].unmarkTask();
                    System.out.println("Task " + taskIndex + " has been unmarked");
                }
                newline();
                continue;
            }

            //insert into list for normal input
            Task task = new Task(input);
            list[numLines] = task;
            numLines++;

            newline();
            System.out.println(input);
            newline();
        }
    }
}