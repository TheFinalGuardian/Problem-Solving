import java.io.*;
import java.util.StringTokenizer;

class Censoring {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("censor");
		char[] s = io.next().toCharArray();
		char[] t = io.next().toCharArray();
		
		LinkedList<Character> magazine = new LinkedList<Character>();
		for (char c : s) {
			magazine.add(c);
		}

		LinkedList<Character> censor = new LinkedList<Character>();
		for (char c : t) {
			censor.add(c);
		}

		magazine.reset();
		censor.reset();

		for (int i = 0; i < magazine.size(); i++) {
			if (magazine.get().equals(censor.getFirst()) && magazine.rangeEquals(i, censor)) {
				magazine.delRange(i, i + censor.size());
				i = Math.max(i - censor.size(), 0);
				magazine.move(i);
				censor.reset();
			}
			
			magazine.move();
		}

		magazine.reset();
		for (int i = 0; i < magazine.size(); i++) {
			io.print(magazine.get().get());
			magazine.move();
		}

		io.close();
	}


}

class LinkedList<T> {
	Node<T> first;
	Node<T> last;
	Node<T> pointer;
	int pointerIndex;
	int length;

	public LinkedList() { this.length = 0; }

	public Node<T> getFirst() { return first; }
	public Node<T> getLast() { return last; }
	public Node<T> get() { return pointer; }
	public Node<T> get(int i) {
		move(i);
		return pointer;
	}
	public int currIndex() { return pointerIndex; }
	public int size() { return length; }

	public boolean rangeEquals(int si, LinkedList<T> ll) {
		move(si);
		ll.reset();

		for (int i = si; i < si + ll.size(); i++) {
			if (!pointer.equals(ll.get())) {
				move(si);
				ll.reset();
				return false;
			}
			move();
			ll.move();
		}

		move(si);
		ll.reset();
		return true;
	}
	public void reset() {
		pointerIndex = 0;
		pointer = first;
	}

	public void add(T x) {
		if (first == null) {
			first = last = new Node<T>(x);
			pointerIndex = 0;
			pointer = first;
		} else {
			last.setNext(new Node<T>(x, last));
			last = last.getNext();
		}
		length++;
	}

	public void ins(T x, int i) {
		move(i);
		pointer.setNext(new Node<T>(x, pointer, pointer.getNext()));
	}

	public void delRange(int si, int ei) {
		if (si == 0) {
			for (int i = 0; i < ei; i++) {
				first = first.getNext();
			}
			first.setPrev(null);
		} else if (ei == length) {
			for (int i = 0; i < ei - si; i++) {
				last = last.getPrev();
			}
			last.getNext().setPrev(null);
			last.setNext(null);
		} else {
			move(si - 1);
			Node<T> pointer2 = pointer;
			for (int i = si; i <= ei; i++) {
				pointer2 = pointer2.getNext();
			}
			pointer.getNext().setPrev(null);
			pointer2.getPrev().setNext(null);
			pointer.setNext(pointer2);
			pointer2.setPrev(pointer);
			length -= ei - si;
		}
	}

	public void move(int i) {
		while (i > pointerIndex) { move(); }
		while (i < pointerIndex) { moveBack(); }
	}
	public void move() {
		if (pointerIndex != length - 1) {
			pointer = pointer.getNext();
			pointerIndex++;
		}
	}
	public void moveBack() {
		if (pointerIndex != 0) {
			pointer = pointer.getPrev();
			pointerIndex--;
		}
	}
}

class Node<T> {
	private T val;
	private Node<T> next;
	private Node<T> prev;

	public Node(T val) { this.val = val; }
	public Node(T val, Node<T> prev) { this.val = val; this.prev = prev; }
	public Node(T val, Node<T> prev, Node<T> next) { this.val = val; this.prev = prev; this.next = next; }

	public T get() { return val; } 
	public void set(T val) { this.val = val; }

	public Node<T> getNext() { return next; }
	public void setNext(Node<T> next) { this.next = next; }

	public Node<T> getPrev() { return prev; }
	public void setPrev(Node<T> prev) { this.prev = prev; }

	public boolean equals(Node<T> n) { return val == n.get(); }
}

class Kattio extends PrintWriter {
	private BufferedReader r;
	private StringTokenizer st;
	// standard input
	public Kattio() { this(System.in, System.out); }
	public Kattio(InputStream i, OutputStream o) {
		super(o);
		r = new BufferedReader(new InputStreamReader(i));
	}
	// USACO-style file input
	public Kattio(String problemName) throws IOException {
		super(problemName + ".out");
		r = new BufferedReader(new FileReader(problemName + ".in"));
	}
	// returns null if no more input
	public String next() {
		try {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(r.readLine());
			return st.nextToken();
		} catch (Exception e) { }
		return null;
	}
	public int nextInt() { return Integer.parseInt(next()); }
	public double nextDouble() { return Double.parseDouble(next()); }
	public long nextLong() { return Long.parseLong(next()); }
}