#include <iostream>
using namespace std;


const int MAXLEN = 15;                  //  Arrayens lengde.
char arr[MAXLEN + 1];                   //  Global array av char'er.
int permutasjonNr = 0;
bool brukteBokstaver[27][MAXLEN + 1];


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


bool toLikeVokaler(char tekst[]) {		// Sjekker tilfelle A: to like vokaler kommer etter hverandre
	int i = 1, j = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (tekst[i] == tekst[j++] && bokstavErVokal(tekst[i])) return true;
	}

	return false;
}


bool treVokaler(char tekst[]) {			// Sjekker tilfelle B: tre vokaler kommer etter hverandre
	int i = 2, j = 1, k = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (bokstavErVokal(tekst[i]) && bokstavErVokal(tekst[j]) && bokstavErVokal(tekst[k])) return true;
		j++, k++;
	}

	return false;
}


bool treLikeKonsonanter(char tekst[]) {	// Sjekker tilfelle C: tre like konsonanter kommer etter hverandre
	int i = 2, j = 1, k = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (tekst[i] == tekst[j] == tekst[k] && !bokstavErVokal(tekst[i]) && !bokstavErVokal(tekst[j++]) && !bokstavErVokal(tekst[k++])) return true;
	}

	return false;
}


bool fireKonsonanter(char tekst[]) {		// Sjekker tilfelle D: fire konsonanter kommer etter hverandre
	int i = 3, j = 2, k = 1, l = 0;
	int len = strlen(tekst);

	for (i; i < len; i++) {
		if (!bokstavErVokal(tekst[i]) && !bokstavErVokal(tekst[j]) && !bokstavErVokal(tekst[k]) && !bokstavErVokal(tekst[l])) return true;
		j++, k++, l++;
	}

	return false;
}


bool toLikeStart(char tekst[]) {			// Sjekker tilfelle E: to like bokstaver (vokaler eller konsonanter) starter et ord
	return (tekst[0] == tekst[1]);
}


bool bruktTidligere(char tekst[]) {		// Sjekker om permutasjonen er brukt tidligere
	int len = strlen(tekst);
	bool brukt[MAXLEN] = { false }, bruktPermutasjon = true;

	for (int i = 0; i < len; i++) {
		if (brukteBokstaver[tekst[i] - int('A')][i]) brukt[i] = true;
	}

	for (int i = 0; i < len; i++) {
		if (!brukt[i]) bruktPermutasjon = false;
	}

	return bruktPermutasjon;
}


bool sjekkGyldigPermutasjon(char tekst[]) {
	return (!toLikeVokaler(tekst) && !treVokaler(tekst) && !treLikeKonsonanter(tekst) && !fireKonsonanter(tekst) && !toLikeStart(tekst) && !bruktTidligere(tekst));
}


void display(char a[]) {               //  Viser arrayens innhold.
		cout << "\n#" << ++permutasjonNr << ": ";
		for (int i = 0; i < strlen(a); i++) {
			cout << a[i] << ' ';
	}
}


void bytt(char & a1, char & a2) {       //  Bytter om to array-elementer.
	char aa = a1;
	a1 = a2;
	a2 = aa;
}


void roterVenstre(char a[], char i) {   //  Roterer elementer mot venstre.
	char x, k;
	x = a[i];								//  Tar vare p† f›rste.
	for (k = i + 1; k <= strlen(a); k++)    //  Roterer:
		a[k - 1] = a[k];
	a[strlen(a)] = x;                       //  Legger f›rst inn bakerst.
}

// TODO: Endre "allerede-brukt" algoritmen, sjekk om de "neste" 2-3 tegna = i-tegnet istedenfor å lagre unna i array
void perm(int i, char a[]) {
	int t;
	if (i == strlen(a) - 1) {												//  Nådd en ny permutasjon
		if (sjekkGyldigPermutasjon(a)) {									//  Gyldig permutasjon
			display(a);														//  Skriver ut arrayen
			for (int i = 0; i < strlen(a); i++) {							//  Lagrer hvilken permutasjon som er "brukt opp"
				brukteBokstaver[a[i] - int('A')][i] = true;
			}
		}
	}

	else {												 //  Skal permutere:
		perm(i + 1, a);								     //  Beholder n†v‘rende nr.'i'.
														 //    Permuterer resten.
		for (t = i + 1; t < strlen(a); t++) {
			bytt(a[i], a[t]);						     //  Bytter nr.'i' etter tur med alle
														 //    de andre etterf›lgende.
			perm(i + 1, a);							     //  For hver av ombyttene: permuterer
		}											     //    resten av de N etterf›lgende.
		roterVenstre(a, i);								 //  Gjenoppretter opprinnelig array.
	}
}


int main() {
	char tekst[MAXLEN + 1];
	cout << "Skriv inn tekst for permutering (max. 15 tegn): ";
	cin.getline(tekst, MAXLEN);

	for (int i = 0; i < strlen(tekst); i++) {
		tekst[i] = toupper(tekst[i]);
	}

	perm(0, tekst);                      //  Lager/skriver permutasjon av ALLE sifrene/arrayelementene.
	cout << '\n';
	return 0;
}