package ada5;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner

        SortingLayer sortingLayer = new SortingLayerImpl();
        VariationGenerationLayer variationGenerationLayer = new VariationGenerationLayerImpl(sortingLayer);
        InputCleaner normalizationLayer = new InputCleanerImpl(variationGenerationLayer);

        String stringKwic = scanner.nextLine();

        normalizationLayer.KWICHandler(stringKwic);
        List<String> finalList = normalizationLayer.bringList();
        System.out.println("Result: ");
        finalList.stream().forEach(System.out::println);
        scanner.close();
    }
}