## 1.5 Halbleiter
- In den 1960er Jahren hat es einige Computer aus diskreten Bauteilen gegeben.
- In 1970 Jahren wurden die ersten 4-Bit-Mikroprozessoren von Intel (4004) entwickelt. 
- Massenproduktion von Mikroprozessoren begann in den 1980er Jahren. mit dem 8080 von Intel und dem 6800 von Motorola.


## 2.2 Klassifizierung von Betriebsystemen
### 2.2.1 Bitbreite
- Bitbreite von Betriebssystemen wird unterschieden durch die Menge des adressierbaren Speichers. *(manchmal wird die Speicherbandbreite künstlich limitiert)*
- Heutzutage sind folgende Systeme auf dem Markt:
    - 16-Bit-Systeme (max. 64KB)
    - 20-Bit-Systeme (16Bit + Segmentierung bis 1MB)
    - 32-Bit-Systeme (max. 4GB)
    - 64-Bit-Systeme (max. 16EB = 18 Millionen TB)


### 2.2.2 64-Bit Verwechselung

- Adressierung eines Datums dauert bei 64-Bit doppelt so lange wie bei 32-Bit, da die Adresse über den Datenbus übertragen werden muss.
- Hype auf 64-Bit-Betriebssysteme entstand durch Verwechslung der Bitbreite des Betriebssystems mit der Systembreite der CPU.
Bei einem 64-Bit-Betriebssystem muss jede Speicheradresse 64 Bit breit sein.

- Vorteile von 64-Bit-Betriebssystemen bestehen nur bei Nutzung von mehr als 4 Gigabyte Speicher und wenn eine Anwendung dies benötigt.
- Aktuell gibt es keine Prozessoren mit mehr als 45 echten Adressleitungen.  
--> 64-Bit-Betriebssysteme sind in Wirklichkeit nur max. 45-Bit-Betriebssysteme und können nicht mehr als 256 Terabyte echten Speicher ansprechen.


### 2.2.3 NX-Bit

- Einige Prozessorhersteller haben begonnen, Sonderbedeutungen in diese Bits einzubauen.
- Beispiel: Höchstes Bit der Adresse (Bit Nr. 63) kennzeichnet, ob an der Adresse Daten oder ausführbarer Programmcode abgelegt ist.
- Zweck: Erschwerung für Schadprogramme, Schadcode in den Speicher einzuschleusen.
- No-eXecute-Bit (NX-Bit) dient dieser Sicherheitsfunktion.
- NX-Bit wurde 2003 von AMD mit dem Athlon64 eingeführt.
- Aktuell wird das NX-Bit von allen Betriebssystemen unterstützt.

![NX-Bit](Bilder/2.2.3NX-Bit.png)


## 2.3.3 Arbeitsspeicher/Cache
- Standard-Prozessoren wurden anfangs mit wenigen MHz betrieben.
- Dynamischer Speicher (DRAM) war als Arbeitsspeicher schnell genug.
- Bis in die 90er-Jahre wurde preiswertes dynamisches RAM verwendet.
- Mit Taktfrequenzen über 20 MHz wurde der langsame Speicher problematisch, da die CPU auf den Speicher warten musste.
- Lösung ab den ersten 32-Bit-Systemen: Ein schnellerer Zwischenspeicher (Cache) zwischen CPU und Speicher.
- Cache-Zwischenspeicher wurde als Statisches-RAM (SRAM) ausgeführt.
- Cache-Controller verwaltet, welche Daten im Cache stehen. *(ein eigener kleiner Computer für die Speicherverwaltung)*
- Ziel des Cache-Controllers: sicherstellen, dass benötigte Daten im Cache sind (Cache-Hit), bevor die CPU sie anfordert.
- Bei Cache-Miss muss die CPU auf den langsamen DRAM-Hauptspeicher warten.
- CPU-Hersteller haben ab steigenden Taktfrequenzen den Cache inklusive Cache-Controller in die CPU integriert (bei Intel ab i486).
- Anfangs waren nur kleine Cache-Größen (<16 kByte) möglich.
- Externer Cache wurde als Second-Level-Cache beibehalten und später integriert (ab ca. 2000).
- Neueste Entwicklungen (ab 2018) integrieren einen Third-Level-Cache (L3-Cache) bis über 20 MB in die CPU.

![RAM / Cache](Bilder/2.3.3RAM-Cache.png)

## 2.3.4 CPU
### 2.3.4.1 Generischer Aufbau einer CPU
- CPU (Central Processing Unit) ist das zentrale Element in einem Computer.
- Früher das einzige aktive Element, mittlerweile gibt es in modernen Computern mehrere, auch unterschiedliche CPUs.
- CPU bedient alle Busse: Adressbus, Datenbus, Steuerbus und setzt verschiedene Vorgänge in Gang.
- Interner Aufbau der CPU wird mit Registern dargestellt (1 Bit Speicher-Bausteine in Gruppen von  8-16-32 oder 64-Bit *jeweils Abhängig von der CPU-Architektur*)
- Frühe CPUs nutzten einen Akkumulator für arithmetische oder logische Funktionen.
- Moderne CPUs können Operationen mit jedem Register durchführen.

### 2.3.4.2 Aktueller CPU-Aufbau
- Langsamer Speicherzugriff auf DRAM-Hauptspeicher bleibt trotz 3 Cache-Ebenen ein Problem.
- Erste Lösungsversuche: 32-Bit-Prozessoren mit 64-Bit-Datenbus nach außen. --> Verdoppelung der Datenübertragungsgeschwindigkeit. **ABER** heutzutage nicht mehr möglich, da CPUs intern mit 64-Bit arbeiten.

![32-Bit 64-Bit Multiplexing](<Bilder/2.3.4.2 32Bit-64BitMultiplexing.png>)

- Moderne Prozessoren (ab ca. 2010) haben keine klassische Busstruktur mehr außerhalb der CPU. *(Aber weiterhin innerhalb der CPU)*
- Speicherinterface wurde stark verändert, um die Datenübertragung zu beschleunigen.
- Mehrere Speicherkanäle und gemultiplexte Adressleitungen sind jetzt vorhanden. *(DRAM-Speicher wird sowieso in 2 oder 4 Kanälen angesprochen, also aufteilung der Speicheradressen)*

![CPU mit 2 Speicherkanälen](<Bilder/2.3.4.2 CPU mit 2 Speicherkanälen.png>)

### 2.3.4.3 Fehlende Adressleitungen *(ab 32-Bit-Prozessoren)*
- Speicheradressierung ist historisch bedingt byteweise organisiert.
- 32-Bit-Prozessoren übertragen immer 4 Bytes auf einmal, daher werden die beiden untersten Adressleitungen nicht mehr benötigt. *(da 2^2 = 4)* 
    - Beispiel: Angenommen, ein 32-Bit-Prozessor möchte auf die Adresse 0x00000004 zugreifen. Da er immer 4 Bytes auf einmal liest, überspringt er die Adressen 0x00000001, 0x00000002 und 0x00000003, weil diese alle innerhalb des ersten 4-Byte-Wortes (Adresse 0x00000000) liegen würden.
- Einzelnes Byte wird durch zusätzliche Busleitungen "Byte Enable" BE0# - BE3# adressiert.
- Bei 64-Bit-Prozessoren wird dies mit acht Byte Enable Leitungen BE0# - BE7# weitergeführt. (Da hier immer 8 Bytes übertragen werden) *(2^3 = 8)*

### 2.3.4.4 Takt und Timing
- Erste Computer hatten genau einen Systemtakt von einem Taktoszillator (meistens Quarzoszillator).
- Ab den 1990ern wurde der Takt innerhalb der CPU erhöht, außerhalb entwickelte sich der Takt auf einige 100 MHz weiter.
- Hohe Taktfrequenzen auf Leiterplatten führen zu Signalintegritätsproblemen.
- Innerhalb der CPU wird mit einem höheren Takt gearbeitet, die Taktvervielfachung erfolgt mittels PLL *(Phase-Locked Loop: elektronische Schaltung zur Frequenzvervielfachung)*
- Speichercontroller in der CPU entkoppelt die unterschiedlich schnellen Busse.
- Maximale Taktfrequenz von 200-300 MHz wurde um das Jahr 2000 erreicht.
- Schnellere Datenübertragung durch 2 oder 4 Datenwörter pro Taktperiode.

## 2.3.6.2 Magnetischer Massenspeicher

### Bandlaufwerke
- Anfänge: Magnetische Massenspeicher begannen mit Magnetbändern, wobei anfangs echte Audio-Tonbänder verwendet wurden. In den 1980er Jahren wurden normale Audiokassettenrekorder mit Kompaktkassetten genutzt.
- Datenaufzeichnung: Häufig erfolgte die Aufzeichnung frequenzmoduliert, was gut zur Audiotechnologie passte.
- Aktuelle Nutzung: Magnetbänder werden heute noch als Streamer für Backups verwendet. Es gibt verschiedene Formate, die digital auf Band aufzeichnen.
- Vor- und Nachteile: Der sequenzielle Zugriff ist sowohl ein Hauptvorteil als auch ein Nachteil. Die Zugriffszeit kann im Minutenbereich liegen, was den schnellen Zugriff auf Daten behindert, aber auch vor Schadprogrammen schützen kann, da nicht alle Daten direkt erreichbar sind und somit nicht sofort zerstört werden können.
- LTO-Ultrium-Bandlaufwerke: Die aktuellen Streamer-Techniken basieren auf LTO-Ultrium-Bandlaufwerken (Linear Tape Open), die verschiedene Generationen von LTO1 bis LTO8 umfassen, wobei einige rückwärtskompatibel sind.

![LTO](<Bilder/2.3.6.2 LTO .png>)

### Diskettenlaufwerke (Floppy-Disk)
- Historie: Die Diskette war in den 1980er Jahren der mobile Massenspeicher für Computer. Sie fungierte als Zwischenlösung zwischen Magnetbandspeicher und Magnetplatte.
- Mechanisches Verhalten: Ähnlich dem Magnetband mit dem Vorteil des wahlfreien Zugriffs.
- Nachteile: Da es direkten Kontakt zwischen Schreiblesekopf und Medium gab, litten Disketten unter starkem Verschleiß und hatten eine geringe Lebensdauer.
- **ACHTUNG** Das Datenformat auf Disketten ist über verschiedene Computertypen hinweg nicht kompatibel.
- Entwicklung: Disketten wurden im Laufe der Jahre kleiner und wiesen eine höhere Speicherdichte auf.
- Ende der Nutzung: Mit dem Aufkommen von Flash-Speichern im USB-Stick-Format wurden Disketten ab ca. 2005 fast vollständig aufgegeben, abgesehen von einigen professionellen Anwendungen *(z.b. Musiker-Equipment)*.

![Disketten Größen](<Bilder/2.3.6.2 Disketten Größen.png>)

### Festplattenlaufwerke (HDD)
- Funktionsweise: Magnetplatten funktionieren ähnlich wie Magnetbänder oder Disketten, wobei der Schreib-/Lesekopf über der Platte schwebt. Der Luftwirbel, der durch die Rotation der Platte entsteht, sorgt dafür, dass der Schreib-/Lesekopf die Platte im Datenbereich niemals berührt.

![Aufbau Standard-Festplatte](<Bilder/2.3.6.2 Aufbau Standard-Festplatte.png>)

- Vorteile: Durch die hohe Rotationsrate bieten Festplatten kurze Zugriffszeiten und hohe Übertragungsraten.
- Entwicklung: Festplattenkapazitäten haben sich von anfangs einigen Megabytes auf heute über 10 Terabyte pro Platte gesteigert. Die Schnittstellen haben ebenfalls eine Evolution durchlaufen.
- Technologische Fortschritte: Technologien wie der GMR-Effekt (Giant Magneto Resistance) und Perpendicular Magnetic Recording haben dazu beigetragen, die Speicherkapazitäten weiter zu steigern.
- Ausblick: Zukünftige Technologien wie Heat Assisted Magnetic Recording (HAMR) und Microwave Assisted Magnetic Recording (MAMR) sollen noch höhere Kapazitäten ermöglichen, jedoch werden Kapazitäten von über 20 Terabyte erst in einigen Jahren erwartet.


## 2.4
## 2.5
## 3.3.1
## 3.3.7
## 3.3.9
## 4.4
## 5.2.2.1
## 5.2.2.3
## 5.3.4.6
## 6.3.2.4
## 6.3.8.4
## 6.3.9.1
## 7.2.1
## 7.2.2
## 7.3.3
## 7.4.2.4