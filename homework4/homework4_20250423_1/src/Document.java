import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Document {
    private final int id;
    private final String name;
    private final List<Document> relatedDocs = new ArrayList<>();

    public Document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void addRelatedDocument(Document doc) {
        relatedDocs.add(doc);
    }

    public void edit() {
        // Собираем все документы, которые нужно заблокировать: сам + связанные
        List<Document> docsToLock = new ArrayList<>(relatedDocs);
        docsToLock.add(this);

        // Сортируем по ID (или по hashCode, если id нет)
        docsToLock.sort(Comparator.comparingInt(Document::getId));

        // Блокируем документы в отсортированном порядке, вложено
        lockAndEdit(docsToLock, 0);
    }

    private void lockAndEdit(List<Document> docs, int index) {
        if (index >= docs.size()) {
            // Когда все блокировки захвачены, редактируем
            System.out.println(Thread.currentThread().getName() + " редактирует " + name);
            for (Document doc : relatedDocs) {
                System.out.println(Thread.currentThread().getName() + " редактирует связанный документ " + doc.name);
            }
            return;
        }

        Document current = docs.get(index);
        synchronized (current) {
            lockAndEdit(docs, index + 1);
        }
    }

    public String getName() {
        return name;
    }

    public List<Document> getRelatedDocs() {
        return relatedDocs;
    }
}
