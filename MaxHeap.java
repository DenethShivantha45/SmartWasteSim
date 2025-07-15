public class MaxHeap {
    private BinAlert[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new BinAlert[capacity];
    }

    private int parent(int index) { return (index - 1) / 2; }
    private int leftChild(int index) { return 2 * index + 1; }
    private int rightChild(int index) { return 2 * index + 2; }

    private boolean compareAlerts(BinAlert a, BinAlert b) {
        if (a.fillLevel != b.fillLevel) {
            return a.fillLevel > b.fillLevel;
        }
        return a.alertTime < b.alertTime; // Earlier alerts have priority if fill levels are equal
    }

    private void swap(int i, int j) {
        BinAlert temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(BinAlert alert) {
        if (size >= capacity) return;
        heap[size] = alert;
        int current = size;
        size++;

        while (current > 0 && compareAlerts(heap[current], heap[parent(current)])) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public BinAlert extractMax() {
        if (size == 0) return null;
        BinAlert max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return max;
    }

    private void heapify(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && compareAlerts(heap[left], heap[largest])) {
            largest = left;
        }
        if (right < size && compareAlerts(heap[right], heap[largest])) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }
}
