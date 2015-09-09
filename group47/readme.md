# sem developer instructions

**Plugins**  
Gebruik a.u.b. de volgende plugins tijdens het programmeren  
1. Checkstyle (right-click on project -> Checkstyle -> Activate Checkstyle)  
Deze plugin controlleert jouw code op stijlfouten. Er zijn twee plugins voor: eentje in maven (door mij geconfigureerd) en eentje in Eclipse, die moet je zelf configureren.  
We gebruiken de Google checkstyle settings, maar staan daarbij wel het gebruik van tabs toe. Zorg dus dat je dit op dezelfde manier in eclipse instelt en dat jouw code vervolgens 
geen fouten heeft.  
2. PMD & FindBugs  
Deze plugin zoekt naar zogenaamde 'code smells' en bugs. Voor het verslag voer je maven uit (Run as...->Maven build...) met als target 'site'.
Het resultaat komt in target/site/ en is het bekijken waard.  
3. Cobertura  
Deze plugin genereert een coverage report van unittests. Ik heb hem helaas nog niet werkend gekregen.  
  
**Git**  
Om complete chaos te voorkomen hier even een quick-start guide voor werken met branches in Git.  
1. Alles in een branch  
Om te beginnen werken we in dit project **alleen** in branches. Maak een aparte branch voordat je begint met werken en push NOOIT jouw aanpassingen direct naar de master.  
2. Maak een pull request  
Om jouw aanpassingen in de master te krijgen heb je toestemming nodig van de rest van het team. Maak op github.com een pull request aan voor jouw branch en geef aan wat je gedaan hebt.  
3. Mergen van jouw branch  
Als er geen conflicts bestaan en er geen commentaar binnen is gekomen kun je mergen met de groene knop.  
4. Conflicts verwerken  
Als er wel conflicts bestaan is dat geen reden tot paniek, dit is simpel op te lossen. Zie hier het stappenplan:  
    a. Zorg dat je op de conflicted branch zit  
    b. Merge de master naar de aparte branch (bijv. git merge master).  
    c. Open eclipse en ga naar de conflicted stukken code. Deze zijn aangegeven met een rood diamantje. Verwijder het deel dat niet van toepassing is en zorg dat alles werkt naar behoren.  
    d. Commit deze aanpassingen weer naar de aparte branch.  
    e. Ga nu naar de master branch (git checkout master)  
    f. Merge de aparte branch naar de master (git merge [branch]). Dit zou zonder conflicts moeten kunnen.  

