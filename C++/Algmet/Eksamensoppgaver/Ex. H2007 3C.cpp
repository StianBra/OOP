// Eksamen H.2007, 3.c)

// INCLUDES:
#include <iostream>
using namespace std;


// STRUCTS/KLASSER:
struct Node {
	int ID;																// Nodens ID/key/nøkkel/navn (et tall).
	Node* left;															// Peker til venstre subtre, evt. z-noden om det er tomt.
	Node* right;														// Peker til høyre subtre, evt. z-noden om det er tomt.
	Node(int id, Node* l, Node* r) { ID = id; left = l; right = r; }
};


// GLOBALE VARIABLER:
Node* z = new Node(0, NULL, NULL);
Node* rot = new Node(1, z, z);
const int MAXNIVA = 5;


// FUNKSJONER:
void byggTre(int lev, Node* p) {
	if (lev < MAXNIVA) {
		lev++;
													
		p->left = new Node(p->ID * 10 + 1, z, z);
		byggTre(lev, p->left);

		if (p->ID % 10 != ((p->ID % 100) / 10)) {	// Lager kun høyrebarn hvis de to siste sifrene i IDen er ulike
			p->right = new Node(p->ID + 1, z, z);	// % 10 = siste siffer, % 100 / 10 = nest-siste siffer
			byggTre(lev, p->right);
		}
	}
}

void display(Node* p) {
	if (p != z) {
		cout << "\tID: " << p->ID << "   (" << p->left->ID
			<< ", " << p->right->ID << ")\n";
		display(p->left);
		display(p->right);
	}
}


int main() {
	byggTre(1, rot);
	display(rot);
	return 0;
}