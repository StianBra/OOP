#include <iostream>
using namespace std;

struct Node {
	char info;
	Node *l, *r;
};

typedef Node* itemType;
#include <stack.h>

void printTree(Node* n) {
	if (n->l) printTree(n->l);
	if (n->r) printTree(n->r);
	cout << n->info << '\n';
}

int main() {
	Node *x, *z;

	char c;
	Stack stack(50);

	z = new Node; // Lager den
	z->l = z; // «tomme» node:
	z->r = z;

	cout << "Skriv inn uttrykk paa infix: ";

	while ((c = cin.get()) != '\n') { // Mer å lese:
		while (c == ' ') c = cin.get(); // Skipper blanke
		x = new Node; // Lager ny node:
		x->info = c;
		x->l = z; x->r = z;

		if (c == '+' || c == '*') { // operator:
			x->r = stack.pop(); // Henter høyre og
			x->l = stack.pop(); // venstre subtre.
		}

		stack.push(x); // Lagrer bygd node.
	}

	printTree(z);
}