# FilmRecensies - Proof of Concept (PoC)

## Overzicht
Deze Proof of Concept (PoC) demonstreert een Machine Learning-toepassing voor het classificeren van filmrecensies op basis van hun sentiment (bijvoorbeeld positief of negatief). Het project gebruikt de Weka-bibliotheek in een programmeeromgeving (Java) om classificatie te implementeren en te evalueren.

---

## Functionaliteiten
- Classificatie van filmrecensies via drie modellen:
    - **NaiveBayes**: Simpel, snel, en accuraat voor tekstuele data.
    - **RandomForest**: Een krachtiger model voor complexe datasets.
    - **SVM**: Goed voor het classificeren van tekstuele data.
- Vergelijking van modelprestaties met metriek zoals nauwkeurigheid, Kappa-statistiek en fouten.

---

## Resultaten
Hieronder staan de prestaties van de modellen op de dataset:

| **Metriek**               | **NaiveBayes**     | **RandomForest**    | **SVM**             |
|---------------------------|--------------------|---------------------|---------------------|
| Correct geclassificeerde voorbeelden | 92.73%            | 78.18%             | 85.45%             |
| Kappa-statistiek          | 0.8533            | 0.5417             | 0.7543             |
| Gemiddelde absolute fout  | 0.0684            | 0.2676             | 0.1456             |
| Root Mean Squared Error   | 0.2532            | 0.3525             | 0.3012             |

### Conclusie
- **NaiveBayes presteert beter** in termen van nauwkeurigheid en fouten, en wordt daarom aanbevolen voor dit project.
- **RandomForest** biedt ruimte voor verbetering, bijvoorbeeld door hyperparameter tuning en datasetvergroting.
- **SVM** presteert goed en kan een alternatief zijn afhankelijk van de dataset en vereisten.

---

## Installatie & Vereisten

### Vereisten
- **IDE**: IntelliJ IDEA.
- **Java**: JDK 21. 

### Installatiestappen
1. Open de zip van het project en pak deze uit.
2. Open het project in IntelliJ IDEA.
3. Als je naar pom.xml gaat, verschijnt er een pop-up om de dependencies te importeren met Maven. Klik hierop en zodra de dependencies zijn geïmporteerd, is het project klaar voor gebruik. 

Als IntelliJ niet automatisch vraagt om dependencies te importeren, kun je altijd handmatig de volgende stappen volgen:
- Ga naar pom.xml ->
- Maven Tool Window openen ->
- Ga naar de rechterkant van je scherm en zoek het Maven Tool Window (een klein icon met het Maven-logo). ->
- Herlaad alle Maven-projecten. ->
- Klik op de knop "Reload All Maven Projects" (een kleine cirkelpijl helemaal links). ->
- IntelliJ zal nu de dependencies downloaden en je project configureren.

4. Ga naar de `StartApplication` class en start de applicatie. Zodra dit is gelukt kan je naar het uitvoeren van de applicatie gaan.

### Uitvoeren van de Applicatie
1. Start de applicatie door de `StartApplication` class uit te voeren.
2. De console vraagt om een modelkeuze, voer deze in.
    - Voor **NaiveBayes**, voer `NaiveBayes` in.
    - Voor **RandomForest**, voer `RandomForest` in.
    - Voor **SVM**, voer `SVM` in.
3. Nu kan je filmrecensies invoeren en de classificatieresultaten bekijken. 

Het model werkt beter met langere recensies zoals, "Een meesterwerk van begin tot eind! De mix van drama en humor was perfect."

Korte recensies zoals, "Slecht" of "Dit was zeer goed." kunnen minder nauwkeurig zijn. Als je ze toch wilt proberen, kan je dit doen, maar dan duurt het meerdere recensies voordat het model verbetert.

4. Je voert in of de classificatie correct is (ja/nee). Zo niet, corrigeer je de classificatie (positief/negatief).
5. Het model wordt opnieuw getraind met de feedback. Je kan altijd weer een recensie opnieuw invoeren om te zien of die het nu wel goed labeled. Na 10 recensies wordt er gevraagd om van model te wisselen. Je kan zelf kiezen of je dit doet of niet (ja/nee). Zo ja, dan kan je weer een model kiezen.
6. Je kan de applicatie stoppen door `stop` in te voeren.
7. Als je de applicatie stopt, eindigt de sessie. De nieuwe data wordt niet opgeslagen. Als je de applicatie opnieuw start, begint de sessie opnieuw en gebruikt het de standaarddata.

### Evaluatie
- Resultaten worden weergegeven in de console na het invoeren van een recensie.
- De gebruiker kan de classificatie bevestigen of corrigeren.
- Na het corrigeren van de classificatie wordt het model opnieuw getraind.
- Bij het toevoegen van een nieuwe recensie wordt de classificatie weergegeven en na één of meerdere recensies wordt de machine learning verbeterd binnen een sessie.

---

## Toekomstige Verbeteringen
1. **Hyperparameter Tuning**: Optimaliseer de RandomForest- en SVM-modellen door hyperparameters te tunen.
2. **Meer Data**: Verhoog de omvang van de trainingsdataset om de nauwkeurigheid van de modellen te verbeteren.
3. **Data opslaan**: Sla de nieuwe data op om de modellen te verbeteren en de prestaties te evalueren ipv. alleen binnen een sessie.
3. **Alternatieve Modellen**: Experimenteer met andere classifiers zoals Logistic Regression.