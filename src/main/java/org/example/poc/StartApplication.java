package org.example.poc;

import org.example.poc.ModelTraining.ModelFactory;
import org.example.poc.ModelTraining.MachineLearningModel;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.classifiers.bayes.NaiveBayes;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.trees.RandomForest;

import java.util.ArrayList;
import java.util.Scanner;

public class StartApplication {

    private static weka.classifiers.Classifier classifier; // Algemeen type voor zowel NaiveBayes, RandomForest en SVM
    public static StringToWordVector filter; // Filter voor het omzetten van tekst naar woordenvectoren
    private static Instances dataSet; // Dataset voor het opslaan van recensies en labels

    // Helperfunctie om een recensie toe te voegen aan de dataset
    private static void voegRecensieToe(Instances dataSet, String recensie, String sentiment) {
        if (dataSet == null || dataSet.numAttributes() < 2) {
            throw new IllegalArgumentException("Dataset is niet correct geïnitialiseerd.");
        }
        double[] vals = new double[dataSet.numAttributes()]; // Aantal attributen in de dataset (tekst, klasse)
        vals[0] = dataSet.attribute(0).addStringValue(recensie); // Voeg de recensie toe
        vals[1] = dataSet.attribute(1).indexOfValue(sentiment); // Voeg het sentiment toe
        dataSet.add(new DenseInstance(1.0, vals)); // Voeg de nieuwe instantie toe aan de dataset
    }

    // Methode om een nieuwe dataset te creëren met voorbeelden van recensies
    public static Instances createDataset() {
        ArrayList<Attribute> attributes = new ArrayList<>(); // Lijst met attributen
        attributes.add(new Attribute("tekst", (ArrayList<String>) null)); // Tekstattribuut
        ArrayList<String> classValues = new ArrayList<>(); // Lijst met mogelijke klassen
        classValues.add("positief");
        classValues.add("negatief");
        attributes.add(new Attribute("klasse", classValues)); // Voeg het klasse-attribuut toe

        Instances dataSet = new Instances("FilmRecensies", attributes, 0); // Maak dataset aan
        dataSet.setClassIndex(dataSet.numAttributes() - 1); // Stel het klasseattribuut in

        // Lijst met positieve recensies
        String[] positieveRecensies = {
                "Een fantastische film die me vanaf het eerste moment heeft gegrepen. De acteerprestaties zijn werkelijk ongelooflijk en het verhaal is zo goed geschreven dat je er volledig in opgaat.",
                "Geweldige verhaallijn en boeiende personages. Ik kon mijn ogen niet van het scherm afhouden! Dit is absoluut een aanrader voor iedereen die van films houdt.",
                "De cinematografie was adembenemend en de muziek maakte het nog specialer. Het voelde alsof ik elke seconde in een andere wereld was.",
                "Een meesterwerk van begin tot eind! De mix van drama en humor was perfect, en ik heb elke minuut genoten van deze film.",
                "Prachtig verhaal dat me echt raakte. Het was niet alleen een film, maar een ervaring die ik voor altijd zal onthouden.",
                "Een ontroerende film die je bijblijft, zelfs nadat de aftiteling is afgelopen. Het gaat echt over menselijke emoties en verbindingen.",
                "Dit is zonder twijfel een van de beste films die ik ooit heb gezien. Elk aspect van de film was perfect, van het script tot de acteerprestaties.",
                "De soundtrack voegde zoveel emotie toe aan deze prachtige film. Ik kon niet anders dan meedoen met de gevoelens die werden opgeroepen.",
                "De regie en het script waren meesterlijk. Ik kon niet stoppen met nadenken over deze film, zelfs niet nadat ik de bioscoop had verlaten.",
                "Een prachtige film die diepe emoties oproept. De manier waarop de karakters zijn ontwikkeld, maakt het verhaal zo authentiek.",
                "Indrukwekkende acteerprestaties, vooral van de hoofdrolspeler. Het verhaal is zo meeslepend dat je het gevoel hebt dat je deel uitmaakt van de film.",
                "Een film die je doet lachen en huilen. De balans tussen humor en serieuze momenten was uitstekend. Dit is wat cinema zou moeten zijn!",
                "Verbluffende visuals en een prachtig verhaal. Elke scène was zorgvuldig samengesteld, wat het kijken nog aangenamer maakte.",
                "Een film die je echt aan het denken zet. De thema's zijn relevant en de boodschap is krachtig. Ik ben er van onder de indruk.",
                "Fantastische cinematografie, een visueel spektakel dat de emoties perfect vastlegde. Deze film zal me nog lang bijblijven.",
                "Een boeiende film die je van begin tot eind vasthoudt. Ik kon niet geloven hoe snel de tijd vloog terwijl ik keek.",
                "Een emotionele rollercoaster die elke keer weer indruk op me maakt. Absoluut een aanrader voor iedereen die van goede films houdt.",
                "De ontwikkeling van de personages was uitstekend, je kon echt meeleven met hun strijd en triomfen. Dit is een verhaal dat je raakt.",
                "Deze film heeft me echt geraakt op een diep niveau. Het verhaal was zo goed doordacht en de uitvoering was perfect!",
                "Een adembenemende ervaring die ik niet snel zal vergeten. De emoties waren zo echt en aangrijpend. Geweldig gedaan!",
                "De film heeft veel diepgang en is zeer goed gemaakt. Elk detail is zorgvuldig overwogen, wat het kijken zo speciaal maakt.",
                "Een episch verhaal dat je meesleept in een andere wereld. Het was een reis die ik absoluut opnieuw zou maken.",
                "Een mooie en ontroerende film die ik iedereen zou aanraden. Het is een verhaal dat je echt laat nadenken over het leven.",
                "Een fantastische film die me vanaf het eerste moment heeft gegrepen.",
                "Geweldige verhaallijn en boeiende personages. Echt genieten!"
        };

        // Lijst met negatieve recensies
        String[] negatieveRecensies = {
                "De film was een grote teleurstelling; de plot was zwak en niet boeiend.",
                "Een saaie en ongeïnspireerde film die zijn tijd niet waard was.",
                "De karakters waren slecht uitgewerkt en de acteerprestaties waren teleurstellend.",
                "Een film die zoveel beloofde, maar in werkelijkheid zo teleurstelde.",
                "De cinematografie was ondermaats en de effecten waren teleurstellend.",
                "De film had een interessant concept, maar de uitvoering was rampzalig.",
                "Slechte scriptwriting met ongeïnspireerde dialogen. Echt jammer.",
                "Ik had veel hogere verwachtingen, maar deze film stelde echt teleur.",
                "Verschrikkelijk geschreven; het leek alsof de scriptwriter zich niet had ingeleefd.",
                "De film duurde te lang en de climax was teleurstellend.",
                "Deze film had geen diepgang en was gewoon een herhaling van zetten.",
                "Het acteerwerk was zo slecht dat ik niet kon geloven dat het echt was.",
                "De film miste elke vorm van originaliteit. Echt teleurstellend.",
                "Een rommelige film met te veel subplotten die nergens toe leidden.",
                "Dit was geen film, maar een oefening in frustratie. Absoluut niet aanbevelenswaardig.",
                "De film was een grote teleurstelling; de plot was zwak en niet boeiend.",
                "Een saaie en ongeïnspireerde film die zijn tijd niet waard was.",
                "De karakters waren slecht uitgewerkt en de acteerprestaties waren teleurstellend.",
                "Een film die zoveel beloofde, maar in werkelijkheid zo teleurstelde.",
                "De cinematografie was ondermaats en de effecten waren teleurstellend.",
                "De film had een interessant concept, maar de uitvoering was rampzalig.",
                "Slechte scriptwriting met ongeïnspireerde dialogen. Echt jammer.",
                "Ik had veel hogere verwachtingen, maar deze film stelde echt teleur.",
                "Verschrikkelijk geschreven; het leek alsof de scriptwriter zich niet had ingeleefd.",
                "De film duurde te lang en de climax was teleurstellend.",
                "Deze film had geen diepgang en was gewoon een herhaling van zetten.",
                "Het acteerwerk was zo slecht dat ik niet kon geloven dat het echt was.",
                "De film miste elke vorm van originaliteit. Echt teleurstellend.",
                "Een rommelige film met te veel subplotten die nergens toe leidden.",
                "Dit was geen film, maar een oefening in frustratie. Absoluut niet aanbevelenswaardig."
        };

        // Voeg positieve recensies toe
        for (String recensie : positieveRecensies) { // Loop over de positieve recensies
            voegRecensieToe(dataSet, recensie, "positief"); // Voeg de recensie toe met het label "positief"
        }

        // Voeg negatieve recensies toe
        for (String recensie : negatieveRecensies) { // Loop over de negatieve recensies
            voegRecensieToe(dataSet, recensie, "negatief"); // Voeg de recensie toe met het label "negatief"
        }

        return dataSet; // Geef de dataset terug
    }

    // Filter instellen voor het omzetten van tekst naar een numerieke representatie
    private static Instances applyStringToWordVectorFilter(Instances dataSet) throws Exception {
        filter = new StringToWordVector(); // Initialiseer het filter
        filter.setInputFormat(dataSet); // Configureer het filter met de dataset
        return Filter.useFilter(dataSet, filter); // Toepassen van het filter op de dataset
    }

    // Train het model (NaiveBayes, RandomForest of SVM)
    public static void trainModel(Instances dataSet) throws Exception {
        Instances filteredData = applyStringToWordVectorFilter(dataSet); // Filter de dataset
        classifier.buildClassifier(filteredData); // Train de classifier
    }

    // Classificeer een nieuwe recensie en geef het voorspelde sentiment terug
    public static String classificeerRecensie(String nieuweRecensie) throws Exception {
        double[] vals = new double[dataSet.numAttributes()]; // Array voor de nieuwe instantie
        vals[0] = dataSet.attribute(0).addStringValue(nieuweRecensie); // Voeg de nieuwe recensie toe
        vals[1] = Double.NaN;  // Gebruik Double.NaN voor de ontbrekende klassewaarde
        DenseInstance nieuweInstance = new DenseInstance(1.0, vals); // Maak een nieuwe instantie aan
        nieuweInstance.setDataset(dataSet); // Koppel de instantie aan de dataset

        // Maak een lege dataset voor het filteren van de nieuwe instantie
        Instances singleInstanceData = new Instances(dataSet, 0); // Maak een lege dataset
        singleInstanceData.add(nieuweInstance); // Voeg de nieuwe instantie toe

        // Filter de nieuwe recensie-instantie
        Instances filteredSingleInstance = Filter.useFilter(singleInstanceData, filter); // Pas het filter toe

        // Voorspel de klasse
        double predictedIndex = classifier.classifyInstance(filteredSingleInstance.firstInstance()); // Voorspel klasse
        return dataSet.classAttribute().value((int) predictedIndex); // Geeft "positief" of "negatief" terug
    }

    // Voeg de nieuwe recensie met het juiste sentiment toe aan de dataset en hertrain het model
    public static void updateDataset(String recensie, String correctSentiment) throws Exception {
        voegRecensieToe(dataSet, recensie, correctSentiment); // Voeg de recensie toe
        trainModel(dataSet); // Train het model opnieuw met de bijgewerkte dataset
    }

    public static void main(String[] args) throws Exception {
        // Dataset aanmaken en het model trainen
        dataSet = createDataset(); // Dataset aanmaken
        classifier = new NaiveBayes(); // Default model (kan later worden veranderd)
        trainModel(dataSet); // Model trainen met initiële dataset

        // Kies vooraf het model via de Factory
        Scanner scanner = new Scanner(System.in);
        String modelType;

        // Blijf vragen om invoer totdat een geldige modelkeuze wordt ingevoerd
        while (true) {
            System.out.print("Kies het model (NaiveBayes/RandomForest/SVM): ");
            modelType = scanner.nextLine();  // Keuze voor het model

            // Controleer of de keuze geldig is
            if (modelType.equalsIgnoreCase("NaiveBayes") || modelType.equalsIgnoreCase("RandomForest") || modelType.equalsIgnoreCase("SVM")) {
                break; // Geldige keuze, breek de lus
            } else {
                System.out.println("Ongeldige invoer. Kies een geldig model: NaiveBayes, RandomForest of SVM.");
            }
        }

        // Gebruik de factory om het juiste model te maken
        MachineLearningModel model = ModelFactory.createModel(modelType);

        // Train het model met de dataset
        model.train(dataSet);

        // Opmerking over het verbeteren van het model na meerdere recensies
        System.out.println("Opmerking: Het model werkt beter met langere recensies (zie ReadMe). Met korte duurt het even voordat die het verbeterd.");

        // Teller voor het aantal recensies
        int recensieCount = 0;

        while (true) {
            // Vraag de gebruiker om een recensie in te voeren
            System.out.print("Voer een nieuwe filmrecensie in (of typ 'stop' om te eindigen): ");
            String nieuweRecensie = scanner.nextLine();
            if (nieuweRecensie.equalsIgnoreCase("stop")) {
                break; // Stop de loop als de gebruiker 'stop' invoert
            }

            // Classificeer de recensie met het huidige model
            String resultaat = classificeerRecensie(nieuweRecensie);
            System.out.println("Voorspeld sentiment: " + resultaat);

            // Vraag de gebruiker om feedback
            System.out.print("Was de voorspelling correct? (ja/nee): ");
            String feedback = scanner.nextLine();

            // Blijf vragen totdat de gebruiker 'ja' of 'nee' invoert
            while (!feedback.equalsIgnoreCase("ja") && !feedback.equalsIgnoreCase("nee")) {
                System.out.println("Ongeldige invoer. Typ 'ja' of 'nee'.");
                feedback = scanner.nextLine();
            }

            if (feedback.equalsIgnoreCase("nee")) {
                // Vraag om het juiste sentiment (positief of negatief)
                System.out.print("Wat was het correcte sentiment? (positief/negatief): ");
                String correctSentiment = scanner.nextLine();

                // Blijf vragen totdat de gebruiker 'positief' of 'negatief' invoert
                while (!correctSentiment.equalsIgnoreCase("positief") && !correctSentiment.equalsIgnoreCase("negatief")) {
                    System.out.println("Ongeldige invoer. Typ 'positief' of 'negatief'.");
                    correctSentiment = scanner.nextLine();
                }

                updateDataset(nieuweRecensie, correctSentiment); // Update dataset met de juiste klasse
                System.out.println("Het model is bijgewerkt met de nieuwe filmrecensie en opnieuw getraind.");
            } else {
                System.out.println("Bedankt voor de bevestiging!");
            }

            // Verhoog de teller na elke recensie
            recensieCount++;

            // Vraag pas om van model te wisselen na 5 recensies
            if (recensieCount >= 10) {
                System.out.print("Wil je van model wisselen? (ja/nee): ");
                String switchModel = scanner.nextLine();

                // Blijf vragen totdat de gebruiker 'ja' of 'nee' invoert
                while (!switchModel.equalsIgnoreCase("ja") && !switchModel.equalsIgnoreCase("nee")) {
                    System.out.println("Ongeldige invoer. Typ 'ja' of 'nee'.");
                    switchModel = scanner.nextLine();
                }

                if (switchModel.equalsIgnoreCase("ja")) {
                    String newModelType;

                    // Blijf vragen om een nieuwe modelkeuze totdat een geldige keuze wordt ingevoerd
                    while (true) {
                        System.out.print("Kies het nieuwe model (NaiveBayes/RandomForest): ");
                        newModelType = scanner.nextLine();

                        // Controleer of de keuze geldig is
                        if (newModelType.equalsIgnoreCase("NaiveBayes") || newModelType.equalsIgnoreCase("RandomForest")) {
                            break; // Geldige keuze, breek de lus
                        } else {
                            System.out.println("Ongeldige invoer. Kies een geldig model: NaiveBayes of RandomForest.");
                        }
                    }

                    // Wijzig het modeltype en hertrain het model
                    if (newModelType.equalsIgnoreCase("RandomForest")) {
                        classifier = new RandomForest();
                    } else {
                        classifier = new NaiveBayes();
                    }

                    // Hertrain het geselecteerde model met de dataset
                    trainModel(dataSet);
                    System.out.println("Model is gewisseld naar: " + newModelType);
                }
                // Reset de teller na de modelwisseling
                recensieCount = 0;
            }
        }

        scanner.close(); // Sluit de scanner af bij beëindiging
    }
}
