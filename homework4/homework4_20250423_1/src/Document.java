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
        List<Document> docsToLock = new ArrayList<>(relatedDocs);
        docsToLock.add(this);

        docsToLock.sort(Comparator.comparingInt(Document::getId));

        lockAndEdit(docsToLock, 0);
    }

    private void lockAndEdit(List<Document> docs, int index) {
        if (index >= docs.size()) {
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
