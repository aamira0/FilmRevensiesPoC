package org.example.poc;

public class ModelFactory {
    public static MachineLearningModel createModel(String modelType) {
        if (modelType.equalsIgnoreCase("NaiveBayes")) {
            return new NaiveBayesModel();
        } else if (modelType.equalsIgnoreCase("RandomForest")) {
            return new RandomForestModel(); // Voeg de RandomForest class toe
        }
        throw new IllegalArgumentException("Onbekend model type: " + modelType);
    }
}