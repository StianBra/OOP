typedef int itemType;
#include <iostream>
#include "Stack.h"

using namespace std;

int main() {
	char ch;
	Stack s;

	cout << "Skriv inn regnestykke paa Postfix: ";

	cin.get(ch);

	while (ch != '\n') {
		if (ch >= '0' && ch <= '9') s.push(int(ch - '0'));
		else if (ch == '+') s.push((int(s.pop() - '0')) + int(s.pop() - '0'));
		else if (ch == '*') s.push((int(s.pop() - '0')) * int(s.pop() - '0'));
		cin.get(ch);

		cout << "Forelopig stack: "; s.printStack();
	}

	s.printStack();
}