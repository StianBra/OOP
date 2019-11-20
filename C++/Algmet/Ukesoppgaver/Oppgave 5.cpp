#include <iostream>
#include <time.h>
#include <math.h>
using namespace std;


struct Node {
	int info;  Node* l, *r;  bool besokt;
	Node() { besokt = false; }
};


typedef Node* itemType;
itemType z;
int nodeCount = 0, maxNodes = 0, maxHeight = 0;


void visit(itemType t) {
	cout << t->info << ' ';  t->besokt = false;
}


void visit2(itemType t) {
	cout << t->info << ' ';  t->besokt = true;
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


bool binaryTreeRNG(int chance) {
	int rng = rand() % 100 + 1;							// Random tall fra 1-100
	if (chance >= rng) return true;						// Tallet er fra 1-chance (chance %)
	return false;										// Tallet er > chance
}


int currentHeight = 0;


itemType nodeGeneration(itemType t, int leftChance, int rightChance) {
	currentHeight++;

	if (binaryTreeRNG(leftChance) && nodeCount < maxNodes && currentHeight < maxHeight) {
		t->l = new Node; t->l->l = z; t->l->r = z;
		t->l->info = ++nodeCount;
		t->l = nodeGeneration(t->l, leftChance, rightChance);
	}

	if (binaryTreeRNG(rightChance) && nodeCount < maxNodes && currentHeight < maxHeight) {
		t->r = new Node; t->r->l = z; t->r->r = z;
		t->r->info = ++nodeCount;
		t->r = nodeGeneration(t->r, leftChance, rightChance);
	}

	currentHeight--;
	return t;
}


itemType generateBinaryTree() {
	itemType t = new Node;
	z = new Node;  z->l = z;  z->r = z;

	t->info = ++nodeCount; t->l = z; t->r = z;
	int leftChance = 0, rightChance = 0;

	cout << "Size of binary tree (levels): ";
	cin >> maxHeight;

	while (leftChance < 10 || leftChance > 100) {
		cout << "\nLikelihood of left-subtrees (10% - 100%): ";
		cin >> leftChance;
	}

	while (rightChance < 10 || rightChance > 100) {
		cout << "\nLikelihood of right-subtrees (10% - 100%): ";
		cin >> rightChance;
	}

	//for (int i = (maxHeight); i >= 0; i--) {
	//	maxNodes += (1 << i);
	//}

	maxNodes = pow(2, maxHeight);

	cout << "Max. amount of nodes: " << maxNodes << '\n';
	cout << "\nGenerating binary search tree with max. height " << maxHeight
		<< "\nLeft-chance = " << leftChance 
		<< "%, Right-chance = " << rightChance << "%\n";

	return nodeGeneration(t, leftChance, rightChance);
}


int main() {
	srand(time(nullptr));								// Seed for RNG-funksjonen
	itemType root = generateBinaryTree();
	cout << "\nTreet traversert PREORDER rekursivt:   ";
	preorderTraverseRecursive(root);
	cout << "\nTreet traversert POSTORDER rekursivt:   ";
	postorderTraverseRecursive(root);
	cout << "\nTreet traversert INORDER rekursivt:   ";
	inorderTraverseRecursive(root);
	cout << "\n\n";
	return 0;
}