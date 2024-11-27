package org.example.poc.ModelTraining;

import org.example.poc.StartApplication;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO; // Voor Support Vector Machine (SVM)
import weka.classifiers.Evaluation;
import weka.core.Instances; // Voor het werken met datasets
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.trees.RandomForest;

public class CheckModels {
    public static void main(String[] args) throws Exception {
        // Haalt de dataset op uit CustomDataset. Deze methode is eerder gedefinieerd
        // en geeft een Instances-object terug dat de recensies en hun labels (positief/negatief) bevat.
        Instances dataSet = StartApplication.createDataset();

        // Maak een nieuw StringToWordVector filter aan
        // Dit filter zal tekst omzetten naar numerieke waarden (woordvectoren) voor de machine learning.
        StringToWordVector filter = new StringToWordVector(); // Stelt in dat het filter moet worden toegepast op het eerste attribuut (tekst)
        filter.setAttributeIndices("first"); // Pas het toe op het eerste attribuut (tekst)
        filter.setInputFormat(dataSet); // Configureer het filter met de dataset
//        filter.setWordsToKeep(1000); // Beperk het aantal woorden (meest voorkomende)
//        filter.setMinTermFreq(1); // Houd alleen woorden die minstens 1 keer voorkomen
//        filter.setTFTransform(true); // Pas termfrequenties toe
//        filter.setIDFTransform(true); // Pas inverse document frequentie toe
//        filter.setStopwordsHandler(new weka.core.stopwords.Rainbow()); // Zet stopwoorden (zoals "de", "en", "is") uit
//        weka.core.tokenizers.NGramTokenizer tokenizer = new weka.core.tokenizers.NGramTokenizer();
//        tokenizer.setNGramMinSize(1);
//        tokenizer.setNGramMaxSize(1);
//        filter.setTokenizer(tokenizer);

        // Pas het filter toe op de dataset om de tekst om te zetten naar numerieke waarden
        Instances filteredData = Filter.useFilter(dataSet, filter);

        // Train NaiveBayes model
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(filteredData);

        // Train RandomForest model
        RandomForest rf = new RandomForest();
        rf.buildClassifier(filteredData);

        // Train SVM model (Support Vector Machine)
        SMO svm = new SMO(); // SVM model
        svm.buildClassifier(filteredData);

        // Cross-validation evaluatie voor NaiveBayes
        Evaluation evalNaiveBayes = new Evaluation(filteredData);
        evalNaiveBayes.crossValidateModel(nb, filteredData, 10, new java.util.Random(1));

        // Cross-validation evaluatie voor RandomForest
        Evaluation evalRandomForest = new Evaluation(filteredData);
        evalRandomForest.crossValidateModel(rf, filteredData, 10, new java.util.Random(1));

        // Cross-validation evaluatie voor SVM
        Evaluation evalSVM = new Evaluation(filteredData);
        evalSVM.crossValidateModel(svm, filteredData, 10, new java.util.Random(1));

        // Resultaten printen
        System.out.println("NaiveBayes Model:");
        System.out.println(evalNaiveBayes.toSummaryString());
        System.out.println("RandomForest Model:");
        System.out.println(evalRandomForest.toSummaryString());
        System.out.println("SVM Model:");
        System.out.println(evalSVM.toSummaryString());

        // Vergelijk de modellen
        compareModels(evalNaiveBayes, evalRandomForest, evalSVM);
    }

    private static void compareModels(Evaluation evalNaiveBayes, Evaluation evalRandomForest, Evaluation evalSVM) {
        System.out.println("\nVergelijking van de drie modellen:");

        // Nauwkeurigheid van NaiveBayes
        double accuracyNaiveBayes = evalNaiveBayes.pctCorrect();
        // Nauwkeurigheid van RandomForest
        double accuracyRandomForest = evalRandomForest.pctCorrect();
        // Nauwkeurigheid van SVM
        double accuracySVM = evalSVM.pctCorrect();

        System.out.println("NaiveBayes Nauwkeurigheid: " + accuracyNaiveBayes);
        System.out.println("RandomForest Nauwkeurigheid: " + accuracyRandomForest);
        System.out.println("SVM Nauwkeurigheid: " + accuracySVM);

        // Vergelijk de drie modellen op basis van nauwkeurigheid
        if (accuracyNaiveBayes > accuracyRandomForest && accuracyNaiveBayes > accuracySVM) {
            System.out.println("NaiveBayes is het beste model.");
        } else if (accuracyRandomForest > accuracyNaiveBayes && accuracyRandomForest > accuracySVM) {
            System.out.println("RandomForest is het beste model.");
        } else if (accuracySVM > accuracyNaiveBayes && accuracySVM > accuracyRandomForest) {
            System.out.println("SVM is het beste model.");
        } else {
            System.out.println("Twee of meer modellen presteren even goed.");
        }
    }
}
