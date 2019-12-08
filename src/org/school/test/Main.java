package org.school.test;

class Main {

    /**
     * @param args Aplikacja jako pierwszy parametr przyjmuje nr programu do uruchomienia:
     *             1 - Program 1 – zarządzanie użytkownikami
     *             2 - Program 2 – zarządzanie zadaniami
     *             3 - Program 3 – zarządzanie grupami
     *             4 - Program 4 – przypisywanie zadań
     *             5 - Zadanie – Dodawanie rozwiązań, program wymaga id użytkownika jako kolejnego parametru
     *             Jesli nie podano nr programu albo nr będzie z poza zakresu 1-5 uruchomiony zostanie przykładowy program dla UserDao
     */
    public static void main(String[] args) {

        int programNumber = programNumber(args);

        Processor processor;
        switch (programNumber) {
            case 1:
                processor = new UserProcessor();
                break;
            case 2:
                processor = new ExerciseProcessor();
                break;
            case 3:
                processor = new GroupProcessor();
                break;
            case 4:
                processor = new SolutionProcessor();
                break;
            case 5:
                int userId = Integer.parseInt(args[1]);
                processor = new AddSolutionProcessor(userId);
                break;
            default:
                processor = new UserTestProcessor();
        }

        processor.run();
    }

    private static int programNumber(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }
        }

        return -1;
    }
}
