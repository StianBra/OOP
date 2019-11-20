/* Algmet oppgave 8:
Av og til kommer man over oppgaver (i aviser, blader o.l.) som går ut på å endre rekkefølgen
på bokstaver, slik at man får et meningsfullt ord, f.eks. stedsnavn eller et yrke. "BERGEN"
kunne ha vært skrevet som "GEERBN", eller "HEISMONTØR" som "TØRMISNEHO".

Denne oppgaven går ut på å lese inn en tekst (max.15 tegn), og generere alle kombinasjoner/
permutasjoner av disse bokstavene.
En tekst på N bokstaver, vil jo som kjent ha N! ulike permutasjoner. For å sile vekk
(avskjære) noen av alternativene så skal programmet ikke skrive ut ord der:

 1) a: to like vokaler kommer etter hverandre.
 b: tre vokaler kommer etter hverandre.
 c: tre like konsonanter kommer etter hverandre.
 d: fire konsonanter kommer etter hverandre
 (vi ser altså helt bort fra sammensatte ord, som f.eks: «angstskrik»).
 e: to like bokstaver (vokaler eller konsonanter) starter et ord.
 2) ett tegn havner i en posisjon der et identisk tegn allerede har vært.
 (F.eks. i ordet "BIRI" vil ombytte av 'I'ene være uinteressant.)
Output fra programmet skal være de permuterte ordene:
 - Nummerert fra 1 og fortløpende oppover.
 - Stans for hver 24.utskrift (og vent på at ett tegn blir skrevet/tastet inn) */

// INCLUDES:
#include <iostream>
using namespace std;


// GLOBALE VARIABLER:
const int STRLEN = 15;
int permutationNo = 0, permutationSuccess = 0;
bool brukteBokstaver[27][STRLEN + 1];


// DIV. FUNKSJONER:
bool bokstavErVokal(char tegn) {
	switch (tolower(tegn)) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u': return true;
		default: return false;
	} 
}


bool toLikeVokaler(char* tekst) {		// Sjekker tilfelle A: to like vokaler kommer etter hverandre
	int i = 1, j = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (tekst[i] == tekst[j++] && bokstavErVokal(tekst[i])) return true;
	}

	return false;
}


bool treVokaler(char* tekst) {			// Sjekker tilfelle B: tre vokaler kommer etter hverandre
	int i = 2, j = 1, k = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (bokstavErVokal(tekst[i]) && bokstavErVokal(tekst[j++]) && bokstavErVokal(tekst[k++])) return true;
	}

	return false;
}


bool treLikeKonsonanter(char* tekst) {	// Sjekker tilfelle C: tre like konsonanter kommer etter hverandre
	int i = 2, j = 1, k = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (tekst[i] == tekst[j] == tekst[k] && !bokstavErVokal(tekst[i]) && !bokstavErVokal(tekst[j++]) && !bokstavErVokal(tekst[k++])) return true;
	}

	return false;
}


bool fireKonsonanter(char* tekst) {		// Sjekker tilfelle D: fire konsonanter kommer etter hverandre
	int i = 3, j = 2, k = 1, l = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (!bokstavErVokal(tekst[i]) && !bokstavErVokal(tekst[j++]) && !bokstavErVokal(tekst[k++]) && !bokstavErVokal(tekst[l++])) return true;
	}

	return false;
}


bool toLikeStart(char* tekst) {			// Sjekker tilfelle E: to like bokstaver (vokaler eller konsonanter) starter et ord
	return (tekst[0] == tekst[1]);
}


bool bruktTidligere(char* tekst) {		// Sjekker om permutasjonen er brukt tidligere
	int len = strlen(tekst);
	bool brukt = true;

	for (int i = 0; i < len; i++) {
		if (!brukteBokstaver[toupper(tekst[i]) - int('A')][i]) brukt = false;
	}

	return brukt;
}


bool sjekkGyldigPermutasjon(char* tekst) {
	return (!toLikeVokaler(tekst) && !treVokaler(tekst) && !treLikeKonsonanter(tekst) && !fireKonsonanter(tekst) && !toLikeStart(tekst) && !bruktTidligere(tekst));
}


void swap(char* a, char* b) {
	char* temp;
	temp = a;
	a = b;
	b = temp;
}


int fakultet(int nr) {
	int num = 1;

	if (nr > 1) {
		num *= nr--;
		num *= fakultet(nr);
	}

	return num;
}


char* permuter(char* tekst, int skuff) {
	int originalSkuff = skuff, len = strlen(tekst);

	if (permutationNo++ < fakultet(len)) {									// Mindre enn N! permutasjoner fullført
		while (skuff < len - 1) {
			swap(tekst[originalSkuff], tekst[++skuff]);						// Bytter opprinnelig skuff med alle påfølgende skuffer

			if (sjekkGyldigPermutasjon(tekst)) {							// Permutasjonen er gyldig (sjekkes av 100 andre funksjoner)
				for (int i = 0; i < len; i++) {								// Lagrer hvilken permutasjon som er "brukt opp"
					brukteBokstaver[toupper(tekst[i]) - int('A')][i] = true;
				}

				cout << "\n#" << ++permutationSuccess << ": " << tekst;
				permutationNo++;
				if (!(permutationSuccess % 24)) cin.get();					// Stopper for hver 24. linje
			}

		}

		permuter(tekst, originalSkuff++);									// Permuterer på nytt, med utgangspunkt i neste skuff
	}

	return tekst;
}


int main() {
	char tekst[STRLEN + 1];
	cout << "Skriv inn tekst for permutering (max. 15 tegn): ";
	cin.getline(tekst, STRLEN);
	cout << "Permutering av " << tekst << ":";
	permuter(tekst, 0);
	cout << '\n';
	return 0;
}