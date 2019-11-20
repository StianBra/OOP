#include <iostream>
using namespace std;


struct Disk {
	int diameter;
	Disk(int nextDiameter) { diameter = nextDiameter; }
};


typedef Disk* itemType;
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
		if ()
		stack[p++] = v;
	}

	itemType pop() {
		return stack[--p];
	}

	int empty() {
		return (!p);
	}

	void printStack() {
		for (int i = 0; i < p; i++) {
			cout << "\nBrikke #" << i << " - Diameter: " << stack[i]->diameter << " cm\n";
		}
		cout << "\n";
	}
};


int diskAmount, nextDiameter;
Stack A(diskAmount), B(diskAmount), C(diskAmount);				// Lager en stack for hver av pinnene, med plass til alle brikkene på hver pinne


void displayAllTowers() {
	int diameterToDisplay = nextDiameter + 1;
	Stack ACopy(diskAmount), BCopy(diskAmount), CCopy(diskAmount);
	Disk *diskA = A.pop(), *diskB = B.pop(), *diskC = C.pop();

	for (int i = 1; i <= diskAmount; i++) {
		if (diskA->diameter == diameterToDisplay) {				// Pinnen til venstre har den minste ikke-displayede brikken
			cout << diskA->diameter;
			if (!(i % 3)) cout << '\n';
			else cout << "\t\t";
			ACopy.push(diskA);
			diskA = A.pop();
		}

		else if (diskB->diameter == diameterToDisplay) {		// Pinnen i midten har den minste ikke-displayede brikken
			cout << diskB->diameter;
			if (!(i % 3)) cout << '\n';
			else cout << "\t\t";
			BCopy.push(diskB);
			diskB = B.pop();
		}

		else {													// Pinnen til høyre har den minste ikke-displayede brikken
			cout << diskC->diameter;
			if (!(i % 3)) cout << '\n';
			else cout << "\t\t";
			CCopy.push(diskC);
			diskC = C.pop();
		}

		diameterToDisplay++;
	}

	for (int i = 1; i <= diskAmount; i++) {
		if (!ACopy.empty()) A.push(ACopy.pop());
		if (!BCopy.empty()) B.push(BCopy.pop());
		if (!CCopy.empty()) C.push(CCopy.pop());
	}
}


int main() {
	cout << "Antall brikker: ";
	cin >> diskAmount;
	nextDiameter = diskAmount + 4;								// Minste brikken får diameter på 5cm

	for (int i = 1; i <= diskAmount; i++) {						// Lager alle brikkene, pushes ned på pinne A i synkende rekkefølge basert på diameter
		Disk *currentDisk = new Disk(nextDiameter--);
		A.push(currentDisk);
	}

	displayAllTowers();
}


/* Løsning for diskAmount = 3:
   Brikkene er diameter 5cm, 6cm, 7cm.
   Stack ved start:			7 6 5		0			0
   Flytter 5 til høyre:		7 6			0			5
   Flytter 6 til midten:	7			6			5
   Flytter 5 til midten:	7			6 5			0
   Flytter 7 til høyre:		0			6 5			7
   Flytter 5 til venstre:	5			6			7
   Flytter 6 til høyre:		5			0			7 6
   Flytter 5 til høyre:		0			0			7 6 5 */