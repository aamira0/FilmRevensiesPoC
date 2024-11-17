package org.example.poc;

import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class RandomForestModel implements MachineLearningModel {
    private RandomForest classifier;
    private StringToWordVector filter;

    @Override
    public void train(Instances data) throws Exception {
        // Stel de tekstfilter in
        filter = new StringToWordVector();
        filter.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, filter);

        // Train het RandomForest-model met de gefilterde data
        classifier = new RandomForest();
        classifier.buildClassifier(filteredData);
    }

    @Override
    public String classify(String newReview) throws Exception {
        // Maak een dataset voor de nieuwe recensie
        Instances singleInstanceData = new Instances(filter.getOutputFormat(), 0);

        // Vul de waarden van de nieuwe instantie
        double[] vals = new double[singleInstanceData.numAttributes()];
        vals[0] = singleInstanceData.attribute(0).addStringValue(newReview);
        vals[1] = Double.NaN; // Geen waarde voor de klasse

        // Voeg de nieuwe instantie toe aan de dataset
        Instance newInstance = new DenseInstance(1.0, vals);
        singleInstanceData.add(newInstance);

        // Voer het filter toe om de nieuwe instantie om te zetten naar een woordenvector
        Instances filteredSingleInstance = Filter.useFilter(singleInstanceData, filter);

        // Controleer of de gefilterde instantie correct is toegevoegd
        if (filteredSingleInstance.numInstances() == 0) {
            throw new IllegalStateException("Gefilterde instantie bevat geen data. Controleer de filterinstellingen.");
        }

        // Voorspel de klasse
        double predictedIndex = classifier.classifyInstance(filteredSingleInstance.firstInstance());
        return singleInstanceData.classAttribute().value((int) predictedIndex);
    }
    public RandomForest getClassifier() {
        return classifier;
    }
}
