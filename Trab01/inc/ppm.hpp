#ifndef PPM_HPP
#define PPM_HPP

#include <iostream>
#include <string>
#include "imagem.hpp"

using namespace std;

class PPM : public Imagem{
	private:
		int inicioMsg;
		int tamMsg;
		char *keyWord;
		string mensagem;
		char *alfabeto_keyWord;
	public:
		PPM();
		~PPM();
		void setParametros();
		void setInicioMsg(int inicioMsg);
		void setTamMsg(int tamMsg);
		string decifraMensagem();
		void setAlfabeto();
};

#endif
