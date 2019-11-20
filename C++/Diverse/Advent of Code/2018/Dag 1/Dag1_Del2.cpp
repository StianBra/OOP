// Advent of Code Julekalender dag 1: https://adventofcode.com/2018/day/1
#include <iostream>
#include <fstream>
using namespace std;


// CONST:
const int LEN = 82;


// GLOBALE VARIABLER:
int A = 65, B = 66, C = 67, D = 68, E = 69, F = 70, G = 71, H = 72, I = 73, J = 74, K = 75, L = 76, M = 77, N = 78, O = 79, P = 80, Q = 81, R = 82, S = 83, T = 84, U = 85, V = 86;
int arr[LEN] = { A, B, C, D, E, F, G, C, H, C, I, H, F, J, C, H, C, D, A, G, G, A, C, I, H, F, C, K, H, L, F, C, M, A, F, N, A, O, P, C, Q, B, C, E, R, D, C, M, R, S, F, T, C, A, B, C, E, F, C, I, H, F, I, C, A, S, R, G, U, C, E, H, N, C, V, O, H, F, G, I, N, P };


// FUNKSJONER:
void display(int a[]) {               //  Viser arrayens innhold.
	cout << "\n";
	for (int i = 0; i < LEN; i++)
		cout << char(a[i]);
}


void bytt(int a1, int a2) {       //  Bytter om to array-elementer.
	int aa = a1;
	a1 = a2;
	a2 = aa;
}


void roterVenstre(int a[], int i) {   //  Roterer elementer mot venstre.
	int x, k;
	x = a[i];                            //  Tar vare p† f›rste.
	for (k = i + 1; k < LEN; k++)          //  Roterer:
		a[k - 1] = a[k];
	a[LEN - 1] = x;                          //  Legger f›rst inn bakerst.
}


void perm(int i) {
	int t;
	if (i == LEN - 1)                        //  N†dd en ny permutasjon:
		display(arr);                     //  Skriver ut arrayens innhold.
	else {                               //  Skal permutere:
		perm(i + 1);                        //  Beholder n†v‘rende nr.'i'.
											//    Permuterer resten.
		for (t = i + 1; t < LEN; t++) {
			bytt(arr[i], arr[t]);         //  Bytter nr.'i' etter tur med alle
										  //    de andre etterf›lgende.
			perm(i + 1);                    //  For hver av ombyttene: permuterer
		}                                 //    resten av de N etterf›lgende.
		roterVenstre(arr, i);             //  Gjenoppretter opprinnelig array.
	}
}


int main() {
	perm(0);                             //  Lager/skriver permutasjon av ALLE
										 //    sifrene/arrayelementene.
	cout << '\n';
	display(arr);                        //  Skriver array etter at ferdig (for
	cout << "\n\n";                      //   † se om orginal er gjenopprettet).
	return 0;
}

/* 	for (int i = 1; i <= 28; i++) {
		cout << "\nInkrement " << i << '\n';
		for (int j = 0; j < MAXSIZE; j++) {
			cout << char(arr[j] + i);
		}
	}
*/