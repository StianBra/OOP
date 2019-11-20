#include <iostream>
using namespace std;

struct Node {
	char info;
	Node *l, *r;
	bool visited;
	Node() { visited = false; }
};

typedef Node* itemType;
#include "Stack.h"
Stack stack(50);

void displayNode(Node *t) {
	cout << t->info << '\n';
}

void traversePreOrder(Node *t, Node *z) {
	while (!stack.isEmpty()) {				// Looper gjennom hele stacken
		t = stack.pop(); displayNode(t);	// Popper siste skuff i stacken, displayer info
		if (t->r != z) stack.push(t->r);	// Dersom noden har høyre ben pushes den opp først
		if (t->l != z) stack.push(t->l);	// Og dersom noden har venstre ben pushes den opp sist (blir da poppet først)
	}
}

void traversePostOrder(Node *t, Node *z) {
	while (!stack.isEmpty()) {						// Looper mens det er igjen noder i stacken 
		t = stack.pop();							// Popper siste skuff i stacken
		if (t->visited) displayNode(t);

		else {										// Det er flere ben på noden
			t->visited = true;
			if (t->r != z) stack.push(t->r);		// Dersom noden har høyre ben pushes den opp først
			if (t->l != z) stack.push(t->l);		// Og dersom noden har venstre ben pushes den opp sist (blir da poppet først)
		}
	}
}

int main() {
	Node *x, *z, *root;
	char c = 0;

	root = new Node;
	z = new Node;
	z->l = z;
	z->r = z;

	cout << "Skriv inn postfix-uttrykk: ";

	while ((c = cin.get()) != '\n') {
		while (c == ' ') c = cin.get();
		
		x = new Node;
		x->info = c;
		x->l = z; x->r = z;

		if (c == '+' || c == '*') {
			x->r = stack.pop();
			x->l = stack.pop();
		}

		stack.push(x);
	}

	//traversePreOrder(root, z);
	traversePostOrder(root, z);
}