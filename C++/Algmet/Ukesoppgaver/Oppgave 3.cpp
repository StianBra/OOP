#include <iostream>
using namespace std;


struct Node
  {  char info;  Node* l, * r;  bool besokt;
     Node()  { besokt = false; }   };


typedef Node* itemType;

itemType z;

class Stack  {               //  Fra side 26 i l‘reboka:
  private:
    itemType* stack;
    int p;
  public:
    Stack(int max = 100)
      {  stack = new itemType[max];  p = 0;  }
    ~Stack()
      {  delete [] stack;       }
    void push(itemType v)
      {  stack[p++] = v;     }
    itemType pop()
      {  return stack[--p];  }
    int empty()
      { return (!p);         }
};



itemType createTree()  {    //  Fra side 41 i l‘reboka:
  itemType x;
  char c;
  Stack stack(50);

  z = new Node;  z->l = z;  z->r = z;

  while ((c = cin.get()) != '\n')  {
    while (c == ' ')  cin.get(c);
    x = new Node();
    x->info = c;  x->l = z;  x->r = z;
    if (c == '+'  ||  c == '*')  {
       x->r = stack.pop();  x->l = stack.pop();
    }
    stack.push(x);
  }
  return stack.pop();
}


void visit(itemType t)  {
 cout << t->info << ' ';  t->besokt = false;
}


void visit2(itemType t)  {
 cout << t->info << ' ';  t->besokt = true;
}

                             //  Fra side 45 i l‘reboka:
void preorderTraverse(itemType t)  {
  Stack stack(50);

  stack.push(t);
  while (!stack.empty())  {
    t = stack.pop();    visit(t);
    if (t->r != z)  stack.push(t->r);
    if (t->l != z)  stack.push(t->l);
  }
}


void postorderTraverse(itemType t)  {
  Stack stack(50);

  stack.push(t);                             //  Legger rota p† stacken.
  while (!stack.empty())  {
    t = stack.pop();                         //  Henter fra stacken.
    if (t->besokt)                           //  Sett/pushet barn tidligere.
       visit(t);                             //  Bes›ker noden.
    else  {                                  //  IKKE sett tidligere:
       t->besokt = true;  stack.push(t);     //  Markerer at sett, pusher.
       if (t->r != z)     stack.push(t->r);  //  Har h›yre: push'er denne.
       if (t->l != z)     stack.push(t->l);  //  Har venstre: push'er denne.
    }
  }
}

                //   Laget av Thomas Mellemseter (12HBPUA):
void postorderTraverse2(itemType t)  {
  Stack stack(50);

  z->besokt = true;                          //  Forutsetning for † virke.
  stack.push(t);                             //  Legger rota p† stacken.
  while (!stack.empty())  {
    t = stack.pop();                         //  Henter fra stacken.
    if (t->l->besokt  &&  t->r->besokt) {    //  Begge barn er bes›kt (ogs† z!)
      visit2(t);                             //  Bes›ker noden.
    } else {                                 //  Barna IKKE ferdig bes›kt:
      stack.push(t);                         //  Pusher noden selv.
      if (t->r != z) stack.push(t->r);       //  Har h›yre: push'er denne.
      if (t->l != z) stack.push(t->l);       //  Har venstre: push'er denne.
    }
  }             //  Liten ulempe med denne m†ten er at alle nodenes 
}               //    'besokt' etterp† er satt til 'true'.
                //    Dette skjer ikke i funksjonen RETT ovenfor.
                //    Derfor vil 2.gangs kj›ring i 'main' bare skrive rota.


void postorderTraverse3(itemType t)  {
  Stack stack(50);
  itemType s = NULL;       //  Peker til siste utskrevne node.

  stack.push(t);           //  Legger rota p† stacken.
  while (!stack.empty())  {
    t = stack.pop();                      //  Henter fra stacken.
    if ( (t->l == z  &&  t->r == z)  ||   //  Om noden er bladnode  ELLER
         (s == t->l  &&  t->r == z)  ||   //    bes›kt venstre (ingen h›yre)
          s == t->r )                     //    bes›kt (ogs†) h›yre ferdig:
       {  visit(t);  s = t;  }            //  Bes›ker ogs† noden selv.
    else  {
       stack.push(t);                     //  Push'er seg selv.
       if (t->r != z)  stack.push(t->r);  //  Har h›yre: push'er denne.
       if (t->l != z)  stack.push(t->l);  //  Har venstre: push'er denne.
    }
  }
}


void postorderTraverse4(itemType t)  {
  Stack stack(50);
  itemType s = NULL;       //  Peker til siste utskrevne node.

  stack.push(t);           //  Legger rota p† stacken.
  while(!stack.empty()) {
    t = stack.pop();       //  Henter fra stacken.
               //  G†r s† lenge det er noder til venstre og
               //     venstre/h›yre ikke er skrevet ut:
    while (t->l != z  &&  t->l != s  &&  t->r != s)  {
      stack.push(t);       //  Push'er seg selv.
               //  Push'er h›yre, hvis den ikke er bladnode:
      if(t->r != z) stack.push(t->r);
      t = t->l;		   //  Blar seg ned til venstre.
    }
    visit(t);              //  Skriver ut/bes›ker noden.
    s = t;                 //  Oppdaterer siste utskrevne.
  }
}


int numberOfInternalNodes(itemType t) {	// Finner antall (interne) noder i et binært tre.
	int number = 0;
	
	if (t != z) {
		number++;
		if (t->l != z) number += numberOfInternalNodes(t->l);
		if (t->r != z) number += numberOfInternalNodes(t->r);
	}

	return number;
}


int numberOfExternalNodes(itemType t) { // Finner antall (eksterne) noder i et binært tre.
	int number = 0;

	if (t == z) number++;

	else {
		number += numberOfExternalNodes(t->l);
		number += numberOfExternalNodes(t->r);
	}

	return number;
}


int numberOfFullNodes(itemType t) {	// Finner antall fulle noder (ingen z-barn) i et binært tre.
	int number = 0;

	if (t->l != z && t->r != z) number++;
	
	if (t->l != z) number += numberOfFullNodes(t->l);
	if (t->r != z) number += numberOfFullNodes(t->r);

	return number;
}


int maxHeight = 0,
currentHeight = 0;


void newMaxHeight(itemType t) {
	if (currentHeight > maxHeight) maxHeight = currentHeight;
}


int heightOfTree(itemType t) {
	currentHeight++;
	
	if (t != z) {
		heightOfTree(t->l);
		heightOfTree(t->r);
		newMaxHeight(t);
	}

	currentHeight--;
	return maxHeight;
}


int main()  {
  itemType root;
  cout << "\nPOSTFIX-uttrykk (bokstaver og operatorer, jfr.s.40-41):\n\t";
  root = createTree();
  cout << "\nTreet traversert PREORDER:\t";
  preorderTraverse(root);
  cout << "\nTreet traversert POSTORDER:\t";
  postorderTraverse(root);
  cout << "\nAntall interne noder i treet:\t" << numberOfInternalNodes(root) << " noder";
  cout << "\nAntall eksterne noder i treet:\t" << numberOfExternalNodes(root) << " noder";
  cout << "\nAntall fulle noder i treet:\t" << numberOfFullNodes(root) << " noder";
  cout << "\nHoyde i treet:\t\t\t" << heightOfTree(root) << " noder hoyt \n";
  return 0;
}

// Testuttrykk: 1 7 + 3 * 9 +