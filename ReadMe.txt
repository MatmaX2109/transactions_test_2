Din cerinta testului am dedus ca un om care face parte dintr-o tranzactie (trimite sau primeste) poate avea un singur IBAN.
    Astfel ca se pot folosi tranzactiile de tipul IBAN -> cu obligativitatea IBAN-ul sa fie completat
    si se pot folosi si tranzactiile de tipul WALLER -> fara IBAN-obligatoriu,
    dar aplicatia va face merge daca va gasi un iban pt cnpul respectiv.

Pentru crearea raportului am ales sa execut un singur query in DB, urmand ca apoi sa formez Raportul. O alta posibilitate
    ar fi fost sa aduc elementele gata formate din DB, dar ar fi fost nevoie de mai multe query-uri executate.

Testele le-am facut doar pentru modulul ValidationApplication si am folosit 2 metode diferite.

========================================================================================================================
URL ValidationAppliction:  http://localhost:9090/sendTransaction
URL PersistenceApplicaion: http://localhost:9080/showReport?cnp=xxxxxxxxxxxxx
========================================================================================================================

Pentru a porni aplicatia:

1) Create KAFKA topic:
        kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic orangeTest

2) Create schema:
url=jdbc:mysql://localhost:3306/orange
username=root
password=test

3) Create tables:
    create table `client` (
           `id` bigint not null auto_increment,
            `cnp` varchar(13) not null,
            `iban` varchar(26),
            `name` varchar(255) not null,
            primary key (`id`)
        )

        create table `transactions` (
               `id` bigint not null auto_increment,
                `amount` decimal(19,2) not null,
                `description` varchar(255) not null,
                `type` varchar(255),
                `payee` bigint,
                `payer` bigint,
                primary key (`id`)
            )

4) Run both modules: ValidationApplicaion & PersistenceApplication