// Eksamen H.2007, 4)
// INCLUDES:
#include <iostream>
using namespace std;


// GLOBALE VARIABLER:
const int N = 9;							 //  Arrayens lengde.
int arr[N] = { 1, 2, 3, 4, 5, 6, 7, 8, 9};   //  Global array av int'er.

/* 
A = 0
B = 1
C = 2
D = 3
E = 4
F = 5
G = 6
H = 7
I = 8 
*/


// FUNKSJONER:
void display(int a[]) {               //  Viser arrayens innhold.
	cout << "\n";
	for (int i = 0; i < N; i++)
		cout << "Bokstav: " << char(int('A') + i) << '\t' << a[i] << '\n';
}


bool sjekkVar(int a[]) {
	if (a[7] + a[8] != 8) return false;		// H + I = 8
	if (a[0] - a[1] != 1) return false;		// A - B = 1
	if (a[4] - a[7] != 1) return false;		// E - H = 1
	if (a[2] + a[4] != 14) return false;	// C + E = 14
	if (a[5] + a[0] != 7) return false;		// F + A = 7
	if (a[1] - a[3] != 1) return false;		// B - D = 1
	if (a[6] + a[5] != 11) return false;	// G + F = 11
	if (a[8] + a[2] != 7) return false;		// I + C = 7
	display(arr);
	return true;
}


void bytt(int & a1, int & a2) {       //  Bytter om to array-elementer.
	int aa = a1;
	a1 = a2;
	a2 = aa;
}


void roterVenstre(int a[], int i) {   //  Roterer elementer mot venstre.
	int x, k;
	x = a[i];                            //  Tar vare på første.
	for (k = i + 1; k < N; k++)          //  Roterer:
		a[k - 1] = a[k];
	a[N - 1] = x;                        //  Legger først inn bakerst.
}


void perm(int i) {
	if (i == N - 1) sjekkVar(arr); // Nådd en ny permutasjon: Skriver ut arrayens innhold dersom gyldig løsning.
	
	else {									//  Skal permutere:
		perm(i + 1);                        //  Beholder nåværende nr.'i'.
											//    Permuterer resten.
		for (int t = i + 1; t < N; t++) {
			bytt(arr[i], arr[t]);			//  Bytter nr.'i' etter tur med alle de andre etterfølgende.
			perm(i + 1);					//  For hver av ombyttene: permuterer
		}									//    resten av de N etterfølgende.

		roterVenstre(arr, i);				//  Gjenoppretter opprinnelig array.
	}
}


int main() {
	perm(0);                     // Lager/skriver permutasjon av ALLE sifrene/arrayelementene.
	return 0;
}