# oop-javafx-tutorial
Tutorial for JavaFX and beginners

TODO:
- [X] Serializácia
- [X] Multithreading using JavaFX tasks
- [ ] AspectJ

## Splnenie vedľajších kritérií

1. Multithreading som implementoval v triede TracksController vytvorením inštancie triedy JavaFX Task na púšťanie skladieb:

https://github.com/alexandervalach/oop-javafx-tutorial/blob/18bb1b5616f2b65103e907cbaa4e709850004149/12%20-%20Multithreading/src/controller/tracks/TracksController.java#L152

2. Serializáciu som implementoval v rámci načítanie zoznamu skladieb
Deklarácia AppendingOutputStream:

https://github.com/alexandervalach/oop-javafx-tutorial/blob/5c587a17adefd51a90d25fbd1fcb5f1a713e6b0d/10%20-%20Adding%20tracks/src/model/AppendingObjectOutputStream.java#L7

Použitie pri ukladaní:

https://github.com/alexandervalach/oop-javafx-tutorial/blob/7d6b6c9831765e1d1defa81ee223f0b8a66ceaa3/10%20-%20Adding%20tracks/src/controller/tracks/AddController.java#L65

Použitie pri čítaní:

https://github.com/alexandervalach/oop-javafx-tutorial/blob/18bb1b5616f2b65103e907cbaa4e709850004149/10%20-%20Adding%20tracks/src/controller/tracks/TracksController.java#L157


