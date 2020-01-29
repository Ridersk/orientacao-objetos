#include "pgm.hpp"
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

PGM::PGM(){
	inicioMsg = 0;
	tamMsg = 0;
	numMsg = 0;
	mensagem = "";
}
PGM::~PGM(){
}
void PGM::setParametros(){
	stringstream comentario;
	comentario << getComentario();
	comentario >> this->inicioMsg >>this->tamMsg >>this->numMsg;
}
void PGM::setInicioMsg(int inicioMsg){
	this->inicioMsg = inicioMsg;	
}
void PGM::setTamMsg(int tamMsg){
	this->tamMsg = tamMsg;
}
void PGM::setNumMsg(int numMsg){
	this->numMsg = numMsg;
}

string PGM::decifraMensagem(){
	unsigned char *codigo;
	setParametros();
	
	//verificar se o arquivo lido é do tipo pgm
	if(getTipo() != "P2" && getTipo() != "P5"){
		throw(-3);
		return 0;
	}
	//verifica se os parametros de decodificação foram armazenados
	else if(this->inicioMsg == 0 || this->tamMsg == 0 || this->numMsg == 0){
		throw(-5);//parametros insuficientes
	}
	
	
	

	codigo = new unsigned char [getAltura() * getLargura() * getDimensoes()];
	codigo = getCodigo();
	
	//Decodificação
	for(int i = inicioMsg, contador = 0; i < (inicioMsg + tamMsg); i++, contador++){
		if(codigo[i]>='a' && codigo[i] <= 'z'){		
			if((codigo[i] - numMsg) < 'a'){
				codigo[i] += (26 - numMsg);
			}
			else{
				codigo[i] -= numMsg;
			}
		}
		else if(codigo[i]>='A' && codigo[i] <= 'Z'){
			if((codigo[i] - numMsg) < 'A'){
				codigo[i] += (26 - numMsg);
			}
			else{
				codigo[i] -= numMsg;
			}
		}
		this->mensagem += codigo[i];
	}
	
	return this->mensagem;
}
