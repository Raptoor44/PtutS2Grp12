package sample;

import java.util.Scanner;

public class Test {

    public static void main(String args[]) {
        String script = "Cool, heat-reflecting white rooms are already an urban climate solution that's been embraced in cities around the world.\n" +
                "In New York, more than 10 million square feet [929,000 square metres] of rooves have been coated with white heat-reflecting paint.\n" +
                "But these scientists say their ULTRA-white paint could make those rooves even cooler.\n" +
                "While the whitest currently available paints reflect between 80 and 90 per cent of sunlight, these researchers say theirs reflects more than 98 per cent.\n" +
                "Covering a 1,000 square foot [92.9 square metre] roof with this paint, they estimate, would provide more cooling power than a typical central air conditioner.\n" +
                "Prof Xiulin Ruan, Purdue University\n" +
                "Every one per cent of reflectance that you get will translate to 10 watts per metre squared less heating from the Sun. So, basically, it provides cooling of 18 kilowatts. That's really a big deal. That's more than a typical air conditioner does for a house with the same kind of space.\n" +
                "The secret to its formulation also makes it relatively cheap to produce: the scientists used high concentrations of a compound called barium sulphate, that’s already used to make paper.\n" +
                "Back in 2014, another group of scientists developed the blackest possible black coating, a material called 'Vantablack', that absorbs so much light it makes every surface look almost invisibly flat.\n" +
                "And one museum in the US now wants to put these two breakthroughs side-by-side, displaying the whitest possible white alongside the blackest black.\n";

        String mot = "";
        TextAfficheur exo = new TextAfficheur(script, "#");

        System.out.println(exo.getOccultedString());

        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 15; i++) {
            System.out.println("Entrez un mot du script");
            mot = sc.nextLine();
            exo.discoverWord(mot, 3);
            for (Word w : exo.getWords()) {
                if (w.isDiscovered()) {
                    System.out.println(w.getValue());
                    System.out.println(w.getIndex());
                }
            }

            System.out.println(exo.getScore());
            System.out.println(exo.buildOccultedScript());
        }
        sc.close();
    }
}
