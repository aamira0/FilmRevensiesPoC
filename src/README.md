# FilmRecensies - Proof of Concept (PoC)

## Overzicht
Deze Proof of Concept (PoC) demonstreert een Machine Learning-toepassing voor het classificeren van filmrecensies op basis van hun sentiment (bijvoorbeeld positief of negatief). Het project gebruikt de Weka-bibliotheek in een programmeeromgeving (Java) om classificatie te implementeren en te evalueren.

---

## Functionaliteiten
- Classificatie van filmrecensies via twee modellen:
    - **NaiveBayes**: Simpel, snel, en accuraat voor tekstuele data.
    - **RandomForest**: Een krachtiger model voor complexe datasets.
- Vergelijking van modelprestaties met metriek zoals nauwkeurigheid, Kappa-statistiek en fouten.

---

## Resultaten
Hieronder staan de prestaties van beide modellen op de dataset:

| **Metriek**               | **NaiveBayes**     | **RandomForest**    |
|---------------------------|--------------------|---------------------|
| Correct geclassificeerde voorbeelden | 92.73%            | 78.18%             |
| Kappa-statistiek          | 0.8533            | 0.5417             |
| Gemiddelde absolute fout  | 0.0684            | 0.2676             |
| Root Mean Squared Error   | 0.2532            | 0.3525             |

### Conclusie
- **NaiveBayes presteert beter** in termen van nauwkeurigheid en fouten, en wordt daarom aanbevolen voor dit project.
- **RandomForest** biedt ruimte voor verbetering, bijvoorbeeld door hyperparameter tuning en datasetvergroting.

---

## Installatie & Vereisten

### Vereisten
- **IDE**: IntelliJ IDEA.
- **Weka-bibliotheek (JAR-bestand)**, toegevoegen aan het project. Dit download je via: [Weka Download](https://sourceforge.net/projects/weka/files/weka-3-8/3.8.0/) en zet je in je library map.
- **Dataset**: Tekstbestand met filmrecensies (in code geïmplementeerd, geen externe CSV nodig)

### Installatiestappen en hoe het werkt
1. Download de Weka JAR en voeg deze toe aan je Java-project in een library map zoals.
2. Ga naar je project structure en voeg de Weka JAR toe aan je project in libraries.
3. Zorg ervoor dat alle imports geinstallerd zijn.
4. Zorg ervoor dat je dataset in de code correct is geïmplementeerd. Voorbeelden van dataset-initialisatie zijn in de code opgenomen (deze werkt en kan je gewoon gebruiken).
5. Train de modellen en evalueer de prestaties in FilmRecensies class.
6. Werken beide modellen? Welke presteert beter? Je kan nu de machine learning modellen gebruiken om filmrecensies te classificeren.
7. Ga naar CustomDataset class, start de applicatie en voer een filmrecensie in om deze te classificeren.
8. Bekijk de resultaten en beoordeel de nauwkeurigheid van de classificatie.

### Training van Modellen
1. Bij het starten van de applicatie in de `CustomDataset` class, wordt een keuzemenu weergegeven.
Selecteer de gewenste classifier:
    - Voor **NaiveBayes**, kies `NaiveBayes`.
    - Voor **RandomForest**, kies `RandomForest`.
2. Je kan nu film recenties invoeren en de classificatie resultaten bekijken.
3. Je geeft feedback over de classificatie.
4. Het model wordt opnieuw getraind met de feedback. Dit kan bij sommige recensies sneller zijn dan bij andere. Lange recensies werken op de huidige dataset beter en sneller.
5. Je kan na 10 recensies van model wisselen.
6. Je kan de applicatie stoppen door `stop` in te voeren.
7. Wanneer je het stopt eindigt de sessie en wordt de applicatie afgesloten. Hte leert op je feedback binnen een sessie. Bij het opnieuw starten van de applicatie wordt de sessie opnieuw gestart.

### Evaluatie
- Resultaten worden weergegeven in de console na het trainen van de modellen.
- De prestaties van beide modellen worden vergeleken op basis van nauwkeurigheid, Kappa-statistiek en fouten.
- Vergelijk de prestaties tussen de modellen om tot een aanbeveling te komen.

- Bij het toevoegen van een nieuwe recensie wordt de classificatie weergegeven en na één of meerdere recensies wordt de machine learning verbeterd.

---

## Toekomstige Verbeteringen
1. **Hyperparameter Tuning**: Optimaliseer de RandomForest-parameters om de prestaties te verbeteren.
2. **Meer Data**: Verhoog de omvang van de trainingsdataset om de nauwkeurigheid van beide modellen te verbeteren.
3. **Alternatieve Modellen**: Experimenteer met andere classifiers zoals SVM of Logistic Regression.
