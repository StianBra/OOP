/* Lag et program (med en rekursiv funksjon) som forsøker å plassere N sjakkdronninger på et
NxN brett. Dvs. to dronninger kan hverken stå på samme linje (i), kolonne (j) eller diagonal,
ellers "slår/tar" de hverandre */

// INCLUDES:
#include <iostream>
using namespace std;


// GLOBALE VARIABLER:
int boardSize, solutions = 0;


// DIV. FUNKSJONER:
void displaySolution(bool** board) {
	cout << "Solution #" << ++solutions << ":\n\n\t";
	for (int i = 0; i < boardSize; i++) {
		for (int j = 0; j < boardSize; j++) {
			cout << (board[j][i] ? "x\t" : "0\t");
		}
		cout << "\n\t";
	}
	cout << '\n';
}


bool rowIsFree(bool** board, int x) {		// Finner om det er andre dronninger på samme rad (alle y-koordinater for en gitt x-koordinat)
	for (int i = 0; i < boardSize; i++) {
		if (board[x][i]) return false;
	}

	return true;
}


bool columnsAreFree(bool** board, int y) {	// Sjekker om alt nedover en gitt rad er ledig (alle x-koordinater for en gitt y-koordinat)
	for (int i = 0; i < boardSize; i++) {
		if (board[i][y]) return false;
	}

	return true;
}


bool diagonalIsFree(bool** board, int x, int y) {
	int xCopy = x, yCopy = y;

	while (x && y) {								// Trekker fra 1 på både x/y til en/begge = 0 (øverst til venstre)
		x--;
		y--;
	}

	int i;
	x > y ? i = x : i = y;							// Setter i til den største av x/y

	for (i; i < boardSize; i++) {					// Sjekker så om noen av feltene nordvest->sørøst har dronninger, + 1 på skrå sørøst (+x,+y) til grensen på brettet er nådd
		if (board[x++][y++]) return false;
	}

	x = xCopy; y = yCopy;							// Setter x/y til opprinnelige verdier

	while (y && x < boardSize - 1) {				// Trekker fra 1 på x, og legger til 1 på y (beveger seg opp mot høyre) til grensen på brettet er nådd
		x++;
		y--;
	}

	do {
		if (board[x--][y++]) return false;			// Sjekker så om noen av feltene nordøst->sørvest har dronninger, + 1 på skrå sørvest (-x,+y) til grensen på brettet er nådd
	} while (x && y < boardSize - 1);

	return true;
}


void placeQueen(bool** board, int N) {
	if (!N) displaySolution(board);							// Har plassert N dronninger, displayer løsning

	for (int i = (boardSize - N); i < boardSize; i++) {		// Looper gjennom x-koordinatene
		for (int j = 0; j < boardSize; j++) {				// Looper gjennom y-koordinatene for hver x-koordinat
			if (rowIsFree(board, i) && columnsAreFree(board, j) && diagonalIsFree(board, i, j)) {
				board[i][j] = true;							// Plasserer dronningen på i,j koordinater
				placeQueen(board, N - 1);					// Prøver å plassere dronningen i neste rad
				board[i][j] = false;						// Må backtracke, noen dronninger kunne ikke plasseres
			}
		}
	}
}


int main() {
	cout << "Hvor stort brett? (NxN): ";
	cin >> boardSize;

	bool **board = new bool*[boardSize]();
	for (int i = 0; i < boardSize; ++i) {
		board[i] = new bool[boardSize]();
	}

	placeQueen(board, boardSize);
	return 0;
}