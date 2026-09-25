# Application for Scout Moving Up Ceremony
This is a small side project I have done for my Scout group. Since the Moving up ceremony was close and a new group of people were joining my section (the *Compagnia*, in English often called *Rover Scout Crew*), we decided to host as a supplementary tool an Android App I coded to guide the future members of the Section through a guided Real-Life itinerary.

## Architecture and Files
/main
│  
├── assets/  
│ ├── lettera1.txt
│ ├── lettera2.txt
│ ├── lettera3.txt
│ ├── lettera4.txt
│ └── lettera5.txt
│
│ ├── java/com/example/passaggicompany2k25/  
│ │ ├── adapter/  
│ │ │ └── LetterAdapter.java  
│ │ ├── bean/  
│ │ │ └── Letter.java  
│ │ ├── business/  
│ │ │ └── LogicClass.java  
│ │ ├── commons/  
│ │ │ └── Comms.java 
│ │ ├── util/  
│ │ │ └── FileUtil.java
│ │ └── MainActivity.java 
│ 
│ ├── res/ -- Omitted all the `.xml` files


## Functioning and Protocol
The app is essentially a personalized RecyclerView with an ad-hoc Adapter that shows the context of *Letters*. Each Letter, initialized as a Bean object, would be read from the corresponding File in the Assets folder. 
**Why** a Bean Object just for some text? 
In real life, the group of people would find clues. Each clue was a password to put in the app, to unlock the corresponding Letter:
`2 - Consegna speciale + Tramite l'utilizzo dei mezzi di trasporto, continuare il percorso fino alla prossima meta. Una volta raggiunta, contattare l'assistente digitale per ricevere le direttive su come agire. /n https://mapy.com/... /n https://maps.app.goo.gl/... + R7KJ2C2` from *Lettera2.txt*
I decided to split the `.txt` files by the '+' symbol to make the code easy, after all, the App was meant to be used only once by a group of trusted people. 
Each Letter had the following structure:
`*title + *description* + *password*`
That would obviously correspond to the Letter bean Object:
`private  String  title, text, password;`
`private  boolean  isVisible  =  false;`

### Used Technologies
- Java 17+ (OOP)
- Android Studio (Android Environment in general)

## End Notes
This is a very simple App that its job wonderfully. Despite my lack of presence in real life there due to personal reasons, I am glad that I built the guide as an App for the newcomers in my section myself, so despite the obvious flaws and lack of *any* industry-standard procedure in safety, multithreading or just the use of the **outdated** RecyclerView (chosen because I was taught it at school, therefore there I already knew how to implement it), the App worked fine and no bug arose when least expected.

Link for this Repo: [here](https://github.com/lucaastri/android-scout-movingup-app)
