public class Main {
    public static void main(String[] args) {
        Document doc1 = new Document(1, "Doc A");
        Document doc2 = new Document(2, "Doc B");
        Document doc3 = new Document(3, "Doc C");

        // Устанавливаем связи
        doc1.addRelatedDocument(doc2);
        doc2.addRelatedDocument(doc3);
        doc3.addRelatedDocument(doc1);

        // Запускаем несколько потоков
        Thread t1 = new Thread(() -> doc1.edit(), "Thread-1");
        Thread t2 = new Thread(() -> doc2.edit(), "Thread-2");
        Thread t3 = new Thread(() -> doc3.edit(), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все документы отредактированы без дедлоков.");
    }
}
