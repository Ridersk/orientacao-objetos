#include <iostream>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include "imagem.hpp"

using namespace std;

Imagem::Imagem(){
	//inicializaçao de atributos para fazer teste dos parametros
	tipo == "";
	comentario == "";
	dimensoes = 0;
	altura = 0;
	largura = 0;
	maxPixel = 0;
}
Imagem::~Imagem(){}

//Metodos acessores
string Imagem::getTipo(){
	return tipo;
}
unsigned char *Imagem::getCodigo(){
	return codigo;
}
string Imagem::getComentario(){
	return comentario;
}
int Imagem::getDimensoes(){
	return dimensoes;
}
int Imagem::getAltura(){
	return altura;
}
int Imagem::getLargura(){
	return largura;
}
int Imagem::getMaxPixel(){
	return maxPixel;
}

void Imagem::setParametros(ifstream &arqLeitura){
	char caractere;
	//ler o tipo da imagem
    	getline(arqLeitura,this->tipo);
    	if (this->tipo == "P2" || this->tipo == "P5"){
    		dimensoes = 1;		
    	}
    	else if (this->tipo == "P3" || this->tipo == "P6"){
    		dimensoes = 3;
    	}
    	
    	if(this->tipo != "P2" && this->tipo != "P5" && this->tipo != "P3" && this->tipo != "P6"){
    		//erro de tipo de imagem
    		throw(-4);
    	}
    	//ler comentario
    	arqLeitura.get(caractere);
    	if(caractere == '#'){
    		getline(arqLeitura, this->comentario);
    	}
    	else{
    		/*caso a linha não contem comentario, retorna o ultimo 
    		caractere lido para o fluxo*/
    		arqLeitura.unget();
    	}
    	//ler altura, largura, e o pixel maximo da imagem
    	arqLeitura >> this->altura >> this->largura >> this->maxPixel;
    	if(this->altura == 0 || this->largura ==0 || this->maxPixel == 0){
    		//erro de tipo de imagem
    		throw(-4);
    	}
    	arqLeitura.get();
}

//Metodos de leitura e escrita
void Imagem::lerImagem(string path){
	char caractere;
	std::ifstream arqLeitura(path);
	
	if(!arqLeitura.is_open()){
 		//retorno de erro
       		throw(-1);
    	}
    	else{
    		//metodo para leitura do cabecalho
    		setParametros(arqLeitura);
    		
    		codigo = new unsigned char [altura*largura*dimensoes];
    		for(int i = 0; i < (this->altura*this->largura*this->dimensoes); i++){
    			arqLeitura.get(caractere);	
    	    		codigo[i] = caractere;
    	    	}
    	    	arqLeitura.close();
    	}
}
void Imagem::gravarImagem(string path){
	ofstream arqEscrita(path);
	if(!arqEscrita.is_open()){
		//retorno de erro
		throw(-2);
	} else{
		//gravar no arquivo o cabecalho sem comentario
		arqEscrita <<this->tipo <<endl <<this->altura <<" " <<this->largura <<endl <<this->maxPixel <<endl;
		for(int i = 0;i < (this->altura*this->largura*this->dimensoes); i++){
			//passando cada caractere para o arquivo, pois há alguns caracteres vazios '\0'
			arqEscrita <<codigo[i];
		}
	}
	arqEscrita.close();
}

