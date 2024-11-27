package org.example.poc.ModelTraining;

import org.example.poc.ModelTraining.MachineLearningModel;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class NaiveBayesModel implements MachineLearningModel {
    private NaiveBayes classifier;
    private StringToWordVector filter;

    @Override
    public void train(Instances data) throws Exception {
        // Maak een filter om tekst naar een woordenvector om te zetten
        filter = new StringToWordVector();
        filter.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, filter);

        // Train het Naive Bayes model met de gefilterde data
        classifier = new NaiveBayes();
        classifier.buildClassifier(filteredData);
    }

    @Override
    public String classify(String newReview) throws Exception {
        // Maak een dataset voor de nieuwe recensie
        Instances singleInstanceData = new Instances(filter.getOutputFormat(), 0);

        // Vul de waarden van de nieuwe instantie
        double[] vals = new double[singleInstanceData.numAttributes()];
        vals[0] = singleInstanceData.attribute(0).addStringValue(newReview);
        vals[1] = Double.NaN;  // Geen waarde voor de klasse

        // Voeg de nieuwe instantie toe aan de dataset
        Instance newInstance = new DenseInstance(1.0, vals);
        newInstance.setDataset(singleInstanceData);

        // Voer het filter toe om de nieuwe instantie om te zetten naar een woordenvector
        Instances filteredSingleInstance = Filter.useFilter(singleInstanceData, filter);

        // Voorspel de klasse
        double predictedIndex = classifier.classifyInstance(filteredSingleInstance.firstInstance());
        return singleInstanceData.classAttribute().value((int) predictedIndex);
    }

    public NaiveBayes getClassifier() {
        return classifier;
    }
}
