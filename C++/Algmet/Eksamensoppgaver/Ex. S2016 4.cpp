// Eksamen S.2016, 4)
// INCLUDES:
#include <iostream>
using namespace std;


// GLOBALE VARIABLER:
const int N = 20;					 //  Arrayens lengde.
int mengde[N] = { 50, 32, 74, 29, 61, 42, 53, 29, 78, 39, 51, 18, 27, 64, 25, 42, 48, 37, 44, 48 };	//  Global array av int'er.
int sist[N];

// FUNKSJONER:
void finnSiste() {
	int i, j;
	for (i = 0; i < N; i++) {
		int aktuellMengde = mengde[i];
		j = i;

		while (j > 0 && !(sist[i])) {
			if (mengde[--j] >= mengde[i]) sist[i] = i - j;
		}

		if (!sist[i]) sist[i] = -1;
	}
}


void display() {
	cout << "Mengde: ";
	for (int i = 0; i < N; i++) {
		cout << mengde[i] << ' ';
	}

	cout << "Siste: ";
	for (int i = 0; i < N; i++) {
		cout << sist[i] << ' ';
	}
}


int main() {
	finnSiste();
	display();
	return 0;
}