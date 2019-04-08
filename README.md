oop-2019-uto-18-b-kanuch-emarich created by GitHub Classroom

# ZADANIE PROJEKTU 

E-správa (E-Governance)

Okrem podpory a zabezpečenia demokratickosti vládnutia (e-government), informačné a komunikačné technológie umožňujú aj podporu množstva ďalších procesov súvisiacich so štátnou správou. Tzv. e-správa (e-governance) zahŕňa podporu akýchkoľvek procesov medzi štátnou alebo lokálnou správou a fyzickými alebo právnickými osobami. Môže sa to týkať pobytu, nehnuteľností, zamestnania, finančnej podpory, školstva a pod.

Príklady e-správy zahŕňajú elektronické zabezpečenie podávania žiadosti o víza a udeľovania víz, komunikácie s finančnou správou, resp. s jej ľubovoľnou sekciou (daňový, živnostenský, colný úrad a pod.), poradenstva pre študentov študujúcich v zahraničí, registrácie privezeného dopravného prostriedku zo zahraničia a podania daňového priznania.


# ZAMER PROJEKTU

ECO - Electronic cadastral office

Ema Richnáková

Projekt je zameraný na spravovanie pozemkov, nehnuteľností na daných pozemkoch a príslušných práv k nim určených.

Program bude slúžiť Katastrálnemu úradu spravovať verejný register pozemkov a nehnuteľností a poskytne lepší prehľaď o právnych úkonoch, ktoré sú spojené so správou. Do tejto činnosti sa zahŕňa evidovanie pozemkov a stavieb; členenie pozemkov; zapisovanie práv k pozemkom a nehnuteľnostiam; evidovanie majiteľov; spracovanie požiadavok o prevode vlastníckeho práva, zahŕňajúc kúpnu zmluvu, darovaciu zmluvu, zámennú zmluvu pozemkov; komunikácia s Úradom geodézie, kartografie a katastra ohľadom stavebnej činnosti; komunikácia s Daňovým úradom ohľadom výpočtu sumy dane za pozemok a nehnuteľnosť; zmena hraníc území; ukladanie pokút za niektoré priestupky na úseku geodézie a katastra.

Program taktiež bude slúžiť občanom. Občan, ktorý nebude registrovaný v danom programe, bude môcť prehľadávať parcely a bude mať prístup k vlastníkov a ich údajom. Registrovaný občan bude môcť komunikovať s úradmi spomenutými vyššie, podávať rôzne žiadosti týkajúce sa správy jeho vlastníctva, vystavovať zmluvy.


# Implementovaná funkcionalita

(pri spusteni programu sa do terminálu vypíšu existujúci použivatelia a ich typy... heslá nie sú podstatné, stačí jedno písmeno zadať)<br/> 
- registrácia nového použivateľa z prvej scény, z "Guest-a" a aj z "Office-u" (+ zabezpečenie zlých inputov od používateľa)<br/> 
- prihlásenie a odhlásenie použivateľov (+ zabezpečenie zlých inputov od používateľa)<br/> 
- v Office scéne sa dá vytvoriť v objekte User inštancia triedy Owner (čiže má priradené ešte navyše nejaké atritubúty a hlavne bude si vedieť ukladať v sebe inštancie z tried Land a RealEstate)<br/> 
- (v Office momentálne nejde vytváranie pozemkov)<br/> 


# Implementácia vecí z hodnotenia

- DEDENIE <br/> 
          - v package Offices - CadastralOffice a GeodesyOffice dedia od Office<br/> 
          - v package Owners - City a Owner dedia od Ownership<br/> 
- POLYMORFIZMUS <br/> 
          - v package Owners - v metóde addLand sa mení typ pozemku podľa typu ownera<br/> 
- AGREGÁCIA <br/> 
          - trieda User agreguje objekt triedy Ownership (a taktiež enum UserType)<br/> 
          - trieda Ownership agreguje array triedy Land a RealEstate<br/> 
          - agregacia je aj v triedach Land, Office, CadastralOffice, City, Owner (tam je vnutorna trieda), ... <br/> 
- ENKAPSULÁCIA <br/> 
          - asi v každom balíku je aspoň 1 trieda, ktorá využíva enkapsuláciu<br/> 
- VISITOR <br/> 
          - v balíku ViewControllers trieda MakeOwnerController metóda btnClicked<br/> 
