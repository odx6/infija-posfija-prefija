/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversion;

/**
 *
 * @author ever
 */
public class operador {//objeto que contiene el caracter la prioridad en la expresion y prioridad en la pila 
    public char operando;
    public  int Pexprecion ;
    public int  PrioridadPila;
    public operador(char o, int p , int q){//constructor 
    
    operando=o;
    Pexprecion=p;
    PrioridadPila=q;
    
    }
    // metodos get para regresar los valores 
    public char Operando(){return operando; }
    public int PExprecion(){ return Pexprecion;}//prioridad en la pila
    public int PPila(){return PrioridadPila;}//prioridad en la pila 
    
}
