#include <iostream>
#include "Stack.h"

using namespace std;
typedef char itemType;

int main() {
	char ch;
	Stack s;

	cout << "Skriv inn regnestykke paa Infix: ";

	cin.get(ch);

	while (ch != '\n') {
		if (ch >= '0' && ch <= '9') cout << ch << ' ';
		else if (ch == ')') cout << s.pop() << ' ';
		else if (ch != '(') s.push(ch);
		cin.get(ch);
	}
}