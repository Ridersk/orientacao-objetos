#ifndef PGM_HPP
#define PGM_HPP

#include <iostream>
#include <string>
#include "imagem.hpp"

using namespace std;

class PGM : public Imagem{
	private:
		int inicioMsg;
		int tamMsg;
		int numMsg;
		string mensagem;
	public:
		PGM();
		~PGM();
		void setParametros();
		void setInicioMsg(int inicioMsg);
		void setTamMsg(int tamMsg);
		void setNumMsg(int numMsg);
		string decifraMensagem();
};

#endif
