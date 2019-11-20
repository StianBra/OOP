#include <iostream>
using namespace std;


struct Node {
	char info;  Node* l, *r;  bool besokt;
	Node() { besokt = false; }
};


typedef Node* itemType;
itemType z;

class Stack {               //  Fra side 26 i l‘reboka:
private:
	itemType * stack;
	int p;
public:
	Stack(int max = 100) {
		stack = new itemType[max];  p = 0;
	}
	~Stack() {
		delete[] stack;
	}
	void push(itemType v) {
		stack[p++] = v;
	}
	itemType pop() {
		return stack[--p];
	}
	int empty() {
		return (!p);
	}
};



itemType createTree() {    //  Fra side 41 i l‘reboka:
	itemType x;
	char c;
	Stack stack(50);

	z = new Node;  z->l = z;  z->r = z;

	while ((c = cin.get()) != '\n') {
		while (c == ' ')  cin.get(c);
		x = new Node();
		x->info = c;  x->l = z;  x->r = z;
		if (c == '+' || c == '*') {
			x->r = stack.pop();  x->l = stack.pop();
		}
		stack.push(x);
	}
	return stack.pop();
}


void visit(itemType t) {
	cout << t->info << ' ';  t->besokt = false;
}


void visit2(itemType t) {
	cout << t->info << ' ';  t->besokt = true;
}

//  Fra side 45 i l‘reboka:
void preorderTraverse(itemType t) {
	Stack stack(50);

	stack.push(t);
	while (!stack.empty()) {
		t = stack.pop();    visit(t);
		if (t->r != z)  stack.push(t->r);
		if (t->l != z)  stack.push(t->l);
	}
}


void postorderTraverse(itemType t) {
	Stack stack(50);

	stack.push(t);                             //  Legger rota p† stacken.
	while (!stack.empty()) {
		t = stack.pop();                         //  Henter fra stacken.
		if (t->besokt)                           //  Sett/pushet barn tidligere.
			visit(t);                             //  Bes›ker noden.
		else {                                  //  IKKE sett tidligere:
			t->besokt = true;  stack.push(t);     //  Markerer at sett, pusher.
			if (t->r != z)     stack.push(t->r);  //  Har h›yre: push'er denne.
			if (t->l != z)     stack.push(t->l);  //  Har venstre: push'er denne.
		}
	}
}


void preorderTraverseRecursive(itemType t) {
	if (t) {
		visit(t);
		if (t->l != z) preorderTraverseRecursive(t->l);
		if (t->r != z) preorderTraverseRecursive(t->r);
	}
}


void inorderTraverseRecursive(itemType t) {
	if (t) {
		if (t->l != z) inorderTraverseRecursive(t->l);
		visit(t);
		if (t->r != z) inorderTraverseRecursive(t->r);
	}
}


void postorderTraverseRecursive(itemType t) {
	if (t) {
		if (t->l != z) postorderTraverseRecursive(t->l);
		if (t->r != z) postorderTraverseRecursive(t->r);
		visit(t);
	}
}


int calculatePostfix(itemType t) {
	if (t) {
		if (t->info == '*') return (calculatePostfix(t->l) * calculatePostfix(t->r));
		else if (t->info == '+') return (calculatePostfix(t->l) + calculatePostfix(t->r));
		else {
			cout << '\n' << t->info << " = ?\t";
			int value; cin >> value; return value;
		}
	}
}


int main() {
	itemType root;
	cout << "\nPOSTFIX-uttrykk (bokstaver og operatorer, jfr.s.40-41):\n\t";
	root = createTree();
	cout << "\nTreet traversert PREORDER:   ";
	preorderTraverse(root);
	cout << "\nTreet traversert POSTORDER:   ";
	postorderTraverse(root);
	cout << "\nTreet traversert PREORDER rekursivt:   ";
	preorderTraverseRecursive(root);
	cout << "\nTreet traversert POSTORDER rekursivt:   ";
	postorderTraverseRecursive(root);
	cout << "\nTreet traversert INORDER rekursivt:   ";
	inorderTraverseRecursive(root);
	cout << "\nTreet traverseres postorder rekursivt, og bokstaver byttes med tall for postfix utregning.";
	int value = calculatePostfix(root);
	cout << "\nSum av uttrykk: " << value;
	cout << "\n\n";
	return 0;
}

// Testuttrykk: A B C * D + *
// Talluttrykk: 5 3 2 * 8 + *
// Postorder:   5 3 2 * 8 + *
// Preorder:    * 5 + * 3 2 8
// Inorder:     5 * 3 * 2 + 8
// Sum:			5 *(3 * 2 + 8) = 70