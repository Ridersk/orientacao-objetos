#include "ppm.hpp"
#include <iostream>
#include <string>
#include <sstream>
#include <cstring>
using namespace std;

PPM::PPM(){
	inicioMsg = 0;
	tamMsg = 0;
	mensagem = "";
}
PPM::~PPM(){
}
void PPM::setParametros(){
	stringstream comentario;
	this->keyWord = new char[28];
	comentario << getComentario();
	comentario >> this->inicioMsg >>this->tamMsg >>this->keyWord;
}
void PPM::setInicioMsg(int inicioMsg){
	this->inicioMsg = inicioMsg;	
}
void PPM::setTamMsg(int tamMsg){
	this->tamMsg = tamMsg;
}

void PPM::setAlfabeto(){
	char letra;
	bool letra_Existe;
	this->alfabeto_keyWord = new char[27];
	
	for(unsigned int i = 0; i < 27; i++){
		if(i == 0){
			this->alfabeto_keyWord[i]=' ';
		}
		else if(i <= strlen(this->keyWord)){
			
			this->alfabeto_keyWord[i] = this->keyWord[i-1];
		}
	}
	
	//27 o numero de caracteres do alfabeto_keyWord com o espaço
	//i = strlen(keyWord)+1, para nao imprimir alguma letra em cima da keyword dada
	for(int i = strlen(this->keyWord)+1, count_Letra = 0; i < 27 ; count_Letra++){
		letra_Existe = false;
		//gerando alfabeto_KeyWord atraves da tabela ASCII
		letra = 97 + count_Letra;
		
		for(unsigned int j = 0; j < strlen(this->keyWord); j++){
			if(letra == this->keyWord[j]){
				letra_Existe = true;
			}
		}
		if(letra_Existe == false){
			this->alfabeto_keyWord[i] = letra;
			//proximo indice de i só sera incrementado, quando uma letra for associada ao indice
			i++;
		}
		
	}
}

string PPM::decifraMensagem(){
	setParametros();
	setAlfabeto();
	unsigned char *codigo;
	char letra;
	int posicao_letra;
	
	//verificar se o arquivo lido é do tipo ppm
	if(getTipo() != "P3" && getTipo() != "P6"){
		throw(-3);
		return 0;
	}
	//verifica se os parametros de decodificação foram armazenados
	else if(this->inicioMsg == 0 || this->tamMsg == 0){
		throw(-5);//parametros insuficientes
	}
	
	
	//tamanho da variavel 'numCodigo' definido pelo tamanho da mensagem X dimensoes da imagem ppm
	int numCodigo[tamMsg * getDimensoes()];
	codigo = getCodigo();
	
	for(int i = inicioMsg, indice_num = 0, count = 0; i < (inicioMsg + (tamMsg * getDimensoes())); i++, count++){
		//Método para organizar o final dos 3 pixels R, G, B em um vetor.
		//A cada 3 valores lidos muda-se o indice do vetor para armazenar o proximo valor
		if(count > 2){
			count = 0;
			indice_num++;
		}
		if(count == 0){
			numCodigo[indice_num] = 0;
		}
		if(count < 3){
			numCodigo[indice_num] = numCodigo[indice_num] + (codigo[i] % 10);//pega apenas o ultimo digito
		}
		
	}
	
	//Método para decifrar a mensagem
	
	//tamMsg corresponde também ao tamanho do vetor com os ultimos digitos dos pixels
	for(int i=0; i<(tamMsg); i++){
		if (numCodigo[i] == 0){
			letra = 32; //32 é o valor do espaco na tabela ASCII
		}
    		else{
    			letra = numCodigo[i] + 96;//valor referente das letras minusculas na tabela ASCII
    		}
    		for(int j = 0; j < 27; j++){
    			if(letra == alfabeto_keyWord[j]){
    				//posicao no alfabeto_keyWord vai ser comparada com a posicao no alfabeto normal (0 a 26)
    				posicao_letra = j; 
   			}
    		}
    		if(posicao_letra == 0){
    			mensagem += 32; //valor referente ao espaço na tabela ASCII
    		}
    		else{
    			//compara o valor da posicao com alfabeto normal, decifrando a mensagem
    			mensagem += posicao_letra + 64;
   		}
   	}
	return this->mensagem;
}
