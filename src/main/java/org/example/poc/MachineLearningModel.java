package org.example.poc;

import weka.core.Instances;

public interface MachineLearningModel {
    void train(Instances data) throws Exception; // Om het model te trainen
    String classify(String newReview) throws Exception; // Om een nieuwe recensie te classificeren
}


