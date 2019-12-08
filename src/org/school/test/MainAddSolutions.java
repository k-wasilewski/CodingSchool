package org.school.test;

class MainAddSolutions {

    public static void main(String[] args) {

        int userId = Integer.parseInt(args[0]);

        Processor addSolutionProcessor = new AddSolutionProcessor(userId);
        addSolutionProcessor.run();
    }
}
