package org.example.poc.ModelTraining;

import weka.classifiers.functions.SMO;  // SVM model in Weka
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class SVMModel implements MachineLearningModel {
    private SMO classifier; // SVM classifier
    private StringToWordVector filter; // Filter voor tekst naar numerieke waardes

    @Override
    public void train(Instances data) throws Exception {
        // Stel de tekstfilter in
        filter = new StringToWordVector();
        filter.setInputFormat(data);  // Stel de filter in voor de input data
        Instances filteredData = Filter.useFilter(data, filter);  // Pas de filter toe

        // Train het SVM-model met de gefilterde data
        classifier = new SMO(); // Maak een nieuw SVM-model
        classifier.buildClassifier(filteredData); // Train het model
    }

    @Override
    public String classify(String newReview) throws Exception {
        // Maak een dataset voor de nieuwe recensie
        Instances singleInstanceData = new Instances(filter.getOutputFormat(), 0);

        // Vul de waarden van de nieuwe instantie
        double[] vals = new double[singleInstanceData.numAttributes()];
        vals[0] = singleInstanceData.attribute(0).addStringValue(newReview);  // Voeg de tekst toe
        vals[1] = Double.NaN;  // Geen waarde voor de klasse, aangezien we de classificatie doen

        // Voeg de nieuwe instantie toe aan de dataset
        Instance newInstance = new DenseInstance(1.0, vals);
        singleInstanceData.add(newInstance);

        // Pas het filter toe om de nieuwe instantie om te zetten naar een woordenvector
        Instances filteredSingleInstance = Filter.useFilter(singleInstanceData, filter);

        // Controleer of de gefilterde instantie correct is toegevoegd
        if (filteredSingleInstance.numInstances() == 0) {
            throw new IllegalStateException("Gefilterde instantie bevat geen data. Controleer de filterinstellingen.");
        }

        // Voorspel de klasse van de nieuwe recensie
        double predictedIndex = classifier.classifyInstance(filteredSingleInstance.firstInstance());
        return singleInstanceData.classAttribute().value((int) predictedIndex);
    }

    public SMO getClassifier() {
        return classifier;
    }
}