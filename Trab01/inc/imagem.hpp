#ifndef IMAGEM_HPP
#define IMAGEM_HPP

#include <iostream>
#include <string>
#include <fstream>

using namespace std;

class Imagem{
	private:
		string tipo;
		unsigned char *codigo;
		string comentario;
		int dimensoes;
		int altura;
		int largura;
		int maxPixel;
		void setParametros(ifstream &arqLeitura); //somente classe Imagem tera acesso ao metodo
	public:
		Imagem();
		~Imagem();
		string getTipo();
		unsigned char *getCodigo();
		string getComentario();
		int getDimensoes();
		int getAltura();
		int getLargura();
		int getMaxPixel();
		void lerImagem(string path);
		void gravarImagem(string path);
};

#endif
