/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversion;

import pila.Queue;
import pila.Stack;

/**
 *
 * @author ever
 */
public class Conversion {
public Conversion(){}
public operador crearOperador(char x ){//crea los objetos para evaluar posfija cambio prioridad de parentisis
    operador aux=null;
    switch(x)
       {
           case '^':return new operador (x, 4,3); 
           
            case '*': return new operador (x, 2,2);
            
             case '/': return new operador (x, 2,2);
          
              case '+': return new operador (x, 1,1);
              
               case '-':return new operador (x, 1,1);
            
                case '(':return new operador (x, 5,0);

                 case ')':return new operador (x, 0,0);
                
}
    return aux;
}
public operador crear(char x ){//metodo para crear objetos de tipo operador para metodo de notacion prefija 
    operador aux=null;
    switch(x)
       {
           case '^':return new operador (x, 4,3); 
           
            case '*': return new operador (x, 2,2);
            
             case '/': return new operador (x, 2,2);
          
              case '+': return new operador (x, 1,1);
              
               case '-':return new operador (x, 1,1);
            
                case '(':return new operador (x, 0,0);

                 case ')':return new operador (x, 5,0);
                
}
    return aux;
}

   public String Prefija(String   a ){//regresa la notacion prefija  de la notacion 
     Stack<operador> prefija = new Stack<operador>(); 
      Stack<Character> preOperandos = new Stack<Character>();
        for(int i=a.length()-1; i>=0;i--){// recorre el string derecha a izquierda
           int as =(int) a.charAt(i);//regresa el valor del caracter en codigo ASSCII
           if(as>=48 && as<=57)preOperandos.push(a.charAt(i)); // ingresa si es un operador 
            if(as>=40 && as<=47 && !prefija.isEmpty()  ||!prefija.isEmpty() && a.charAt(i)=='^' ){//ingresa un operando si la pila no esta vacia
             
            if((crear(a.charAt(i))).PExprecion()>(prefija.peek()).PPila())prefija.push(crear(a.charAt(i))); //ingresa si el operando sacado del estrin tiene una prioridad mayor
            else{
                if((crear(a.charAt(i))).Operando()=='('){//entra cuando encuente un parentisis de apertura y vacia la pila hasta que este vacia o encuentre un operando de mayor 
                while(  !prefija.isEmpty() &&  ((crear(a.charAt(i))).PExprecion()<(prefija.peek()).PPila()))
                {
                 operador t = prefija.pop();
                    if(!(t.Operando() ==')'))preOperandos.push((t.Operando()));
                }
                prefija.pop();
                
                }
                
                while(  !prefija.isEmpty() && (prefija.peek().Operando()!=')')  )//vacia la pila mientras no este vacia y el tope sea distinto de un parentisis de cierre 
                {
                 operador t = prefija.pop();
                    if(!(t.Operando() ==')'))preOperandos.push((t.Operando()));
                }
               
                
                
              if(a.charAt(i) !='(')prefija.push(crear(a.charAt(i)));
            }
               
                
                    }
           
        if(as>=40 &&  as<=47 && prefija.isEmpty() && as!=40 || prefija.isEmpty() && a.charAt(i)=='^')prefija.push(crear(a.charAt(i)));//agrega el operando si la pila esta vacia 
        
        }
        while(!prefija.isEmpty()){//al finalizar el recorrido de la expresion vacia la pila y lo agrega a la pila final 
              operador aux= prefija.pop();
              if(aux.Operando() !='('  && aux.Operando() !=')')preOperandos.push(aux.Operando());
              
          }
       return preOperandos.toString();
   }
   public String Posfija(String   a ){//convierte la expresion infija a posfija 
      Stack<operador> posfija = new Stack<operador>(); 
      Queue<Character> preOperandos = new Queue<Character>();
     
       
       for(int i=0; i<a.length();i++){// recorrre el string leido 
           
           int as =(int) a.charAt(i);//regresa el valor del caracter 
           // valida si es un operador o un operando y va vaciando o insertando segun sea el caso 
           if(as>=48 && as<=57)preOperandos.enqueue(a.charAt(i));
            if(as>=40 && as<=47 && !posfija.isEmpty()  ||!posfija.isEmpty() && a.charAt(i)=='^' ){
             
            if((crearOperador(a.charAt(i))).PExprecion()>(posfija.peek()).PPila())posfija.push(crearOperador(a.charAt(i))); 
            else{
                if((crearOperador(a.charAt(i))).Operando()==')'){
                while(  !posfija.isEmpty() &&  ((crearOperador(a.charAt(i))).PExprecion()<(posfija.peek()).PPila()))
                {
                 operador t = posfija.pop();
                    if(!(t.Operando() =='('))preOperandos.enqueue((t.Operando()));
                }
                posfija.pop();
                
                }
                
                while(  !posfija.isEmpty() && (posfija.peek().Operando()!='(')  )
                {
                 operador t = posfija.pop();
                    if(!(t.Operando() =='('))preOperandos.enqueue((t.Operando()));
                }
               
                
                
              if(a.charAt(i) !=')')posfija.push(crearOperador(a.charAt(i)));
            }
               
                
                    }
           
        if(as>=40 &&  as<=47 && posfija.isEmpty() && as!=41 || posfija.isEmpty() && a.charAt(i)=='^')posfija.push(crearOperador(a.charAt(i)));
        
       
       }
        while(!posfija.isEmpty()){
              operador aux= posfija.pop();
              if(aux.Operando() !='('  && aux.Operando() !=')')preOperandos.enqueue(aux.Operando());
              
          }
       return preOperandos.toString();
   }
 
   public String  validar(String a ){//regresa el resultado de una expresion posfija 
        Stack<String> pila = new Stack<String>(); 
        for(int i=0; i<a.length();i++){// recorrre el string leido 
           
           int as =(int) a.charAt(i);
           
           if(as>=40 && as<=47 || a.charAt(i)== '^' ){
                
               int operando2 =Integer.parseInt(pila.pop());
               int operando1= Integer.parseInt(pila.pop());
               
               int resultado ;
               
               switch(a.charAt(i)){
                   
                   
                   case '^':
                       resultado= (int) Math.pow(operando1,operando2);
                     
                   pila.push(resultado+"");
                   
                   break;
                   case '*': resultado= operando1*operando2;
                    
                   pila.push(resultado+"");
                  
                   break;
                   case '/': resultado= operando1/operando2;
                   
                    pila.push(resultado+"");
                   
                   break;
                   case '+': resultado= operando1+operando2;
                  
                    pila.push(resultado+"");
                    
                   break;
                   case '-': resultado= operando1-operando2;
                   
                    pila.push(resultado+"");
                   
                   break;
               }
              
               
               
               
           }
           if(as>=48 && as<=57)pila.push(a.charAt(i)+"");
           
           }
           
        return pila.toString();
   }
   
 
  
    
}
