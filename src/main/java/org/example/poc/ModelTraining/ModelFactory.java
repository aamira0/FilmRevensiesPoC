package org.example.poc.ModelTraining;

import org.example.poc.ModelTraining.MachineLearningModel;
import org.example.poc.ModelTraining.NaiveBayesModel;
import org.example.poc.ModelTraining.RandomForestModel;

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